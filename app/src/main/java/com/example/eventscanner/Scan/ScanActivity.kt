package com.example.eventscanner.Scan

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.eventscanner.R
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView


class ScanActivity : AppCompatActivity() {
    private val REQUEST_CAMERA_PERMISSION = 101
    private var lastScanTime = 0L

    private lateinit var barcodeView: DecoratedBarcodeView
    private lateinit var btnCancel: ImageView
    private lateinit var btnFlash: ImageView
    private lateinit var btnScanning: ImageView
    private lateinit var btnGallery: ImageView
    private var isFlashOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)


        barcodeView = findViewById(R.id.barcode_scanner)
        btnCancel = findViewById(R.id.cancel_scan)
        btnFlash = findViewById(R.id.btnFlash)
        btnScanning = findViewById(R.id.scanning)
        btnGallery = findViewById(R.id.gallery)

        barcodeView.statusView.visibility = View.GONE  // Hide the status text completely
        barcodeView.viewFinder.isVisible = false
        if (!checkCameraPermission()) {
            requestCameraPermission()
        } else {
            setupBarcodeScanner()
        }

        btnCancel.setOnClickListener {
            finish()
        }

    // Setup buttons
    btnCancel.setOnClickListener { finish() }
//    btnFlash.setOnClickListener { toggleFlash() }
    btnGallery.setOnClickListener { openGallery() }
    setupBarcodeScanner()
    }


    private fun setupBarcodeScanner() {
        barcodeView.decodeContinuous(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult) {
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastScanTime > 1000) { // Prevent rapid scans
                    lastScanTime = currentTime
                    barcodeView.pause()

                    // Return result to calling activity (if started for result)
                    val resultIntent = Intent().apply {
                        putExtra("SCAN_RESULT", result.text)
                    }
                    setResult(RESULT_OK, resultIntent)
                    finish()
                }
                // Prevent multiple scans
//                barcodeView.pause()
                // Handle successful scan
                Toast.makeText(this@ScanActivity, "Scanned: ${result.text}", Toast.LENGTH_SHORT).show()
                // You might want to finish the activity or return the result here
            }

            override fun possibleResultPoints(resultPoints: List<ResultPoint>) {
                // Optional: Handle possible result points (visual feedback)
            }
        })
    }
    private fun checkCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            REQUEST_CAMERA_PERMISSION
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupBarcodeScanner() // Permission granted
            } else {
                Toast.makeText(this, "Camera permission denied!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

//    private fun toggleFlash() {
//        if (barcodeView.isTorchOn) {
//            barcodeView.setTorchOff()
//            btnFlash.setImageResource(R.drawable.flash_off)
//        } else {
//            barcodeView.setTorchOn()
//            btnFlash.setImageResource(R.drawable.flahs_on)
//        }
//    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
            type = "image/*"
        }

        try {
            startActivityForResult(
                Intent.createChooser(intent, "Select an image"),
                GALLERY_REQUEST_CODE
            )
        } catch (e: Exception) {
            Toast.makeText(this, "No gallery app found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        barcodeView.resume()
    }

    override fun onPause() {
        super.onPause()
        barcodeView.pause()
    }

    companion object {
        private const val GALLERY_REQUEST_CODE = 1001
    }

}