package com.example.eventscanner.SignUpScreens

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import com.example.eventscanner.R
import com.example.eventscanner.SignInScreens.CameraAccessDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UploadPhotoBottomSheet:BottomSheetDialogFragment() {
    private lateinit var closeDialog: ImageView

    interface OnPhotoSelectedListener {
        fun onPhotoSelected(bitmap: Bitmap)
        fun onPhotoSelected(uri: Uri)
    }

    private var photoListener: OnPhotoSelectedListener? = null
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            openCamera()
        } else {
            Toast.makeText(
                requireContext(),
                "Camera permission required to take photos",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        photoListener = parentFragment as? OnPhotoSelectedListener
    }

    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val photo = result.data?.extras?.get("data") as? Bitmap
                photo?.let {
                    photoListener?.onPhotoSelected(it)
                    dismiss()
                }
            }
        }

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                photoListener?.onPhotoSelected(it)
                dismiss()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_upload_photo, container, false)

        val btnTakePhoto: LinearLayout = view.findViewById(R.id.btnTakePhoto)
        val btnUploadGallery: LinearLayout = view.findViewById(R.id.btnUploadGallery)

//        Handle the close button
        closeDialog = view.findViewById(R.id.closeDialog)
        closeDialog.setOnClickListener {
            dismiss() // Dismiss the bottom sheet
        }
        btnTakePhoto.setOnClickListener {
            checkCameraPermission()
        }

        btnUploadGallery.setOnClickListener {
            openGallery()
        }

        return view
    }

    private fun checkCameraPermission() {
//        when {
//            ContextCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.CAMERA
//            ) == PackageManager.PERMISSION_GRANTED -> {
//                openCamera()
//            }
//            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
//                showCameraPermissionRationale()
//            }
//            else -> {
//                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
//            }
//        }

    // Always show your custom dialog first
    showCameraAccessDialog()

    }
    private fun showCameraAccessDialog() {
        CameraAccessDialog(
            requireContext(),
            onAllow = {
                dismiss()
                // User clicked "Allow" - now check actual permission
                proceedWithCameraAccess()
            },
            onBack = {
                // User clicked "Back"
                Toast.makeText(context, "Camera access is required", Toast.LENGTH_SHORT).show()
            }
        ).show()
//        apply {
//            setCancelable(false) // Prevent dismissing by tapping outside
//
//        }
    }

    private fun proceedWithCameraAccess() {
        when {
            // Permission already granted
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                openCamera()
            }

            // Need to request permission
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

//    private fun showCameraPermissionRationale() {
//        CameraAccessDialog(
//            requireContext(),
//            onAllow = {
//                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
//            },
//            onBack = {
//                // Handle back action if needed
//            }
//        ).show()
//
//    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            cameraLauncher.launch(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Camera not available", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openGallery() {
        galleryLauncher.launch("image/*")
    }
}



