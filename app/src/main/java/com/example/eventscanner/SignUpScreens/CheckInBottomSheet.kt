package com.example.eventscanner.SignUpScreens


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.eventscanner.HomeScreen.BookingIdFragment
import com.example.eventscanner.R
import com.example.eventscanner.Scan.ScanActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.ZoomSuggestionOptions
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.zxing.integration.android.IntentIntegrator


class CheckInBottomSheet: BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.scan_dialog, container, false)

        val closeDialog = view.findViewById<ImageView>(R.id.closeDialog)
        val btnScanQRCode = view.findViewById<TextView>(R.id.btnscanqrcode)
        val btnBookingID = view.findViewById<TextView>(R.id.btnbooking)

        closeDialog.setOnClickListener { dismiss() }

        // Button to trigger QR Code scan
        btnScanQRCode.setOnClickListener {

            val intent = Intent(requireContext(), ScanActivity::class.java)
            startActivity(intent)
            dismiss()
//            startQRCodeScan()

        }

        // Button to handle booking ID input (for future functionality)
        btnBookingID.setOnClickListener {
            Toast.makeText(requireContext(), "Enter Booking ID Clicked", Toast.LENGTH_SHORT).show()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containeeer, BookingIdFragment())  // R.id.fragment_container is the container where your fragments are displayed
            transaction.addToBackStack(null)  // Optionally, add this transaction to the back stack
            transaction.commit()
            dismiss()


        }

        return view
    }

}