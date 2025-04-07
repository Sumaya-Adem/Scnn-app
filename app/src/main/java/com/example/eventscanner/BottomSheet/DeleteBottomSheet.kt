package com.example.eventscanner.BottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.eventscanner.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DeleteBottomSheet: BottomSheetDialogFragment() {

    private lateinit var yesDeleteButton: Button
    private lateinit var cancelButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.delete_acc_dialog, container, false)
        yesDeleteButton = view.findViewById(R.id.yes_delete)
        cancelButton = view.findViewById(R.id.cancel)

        // Set button click listeners
        yesDeleteButton.setOnClickListener {
            Toast.makeText(requireContext(), "Account deleted", Toast.LENGTH_SHORT).show()
            dismiss() // Dismiss the BottomSheet
        }

        cancelButton.setOnClickListener {
            dismiss() // Dismiss the BottomSheet
        }

        return view
    }

}