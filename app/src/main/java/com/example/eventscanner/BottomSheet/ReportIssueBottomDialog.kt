package com.example.eventscanner.BottomSheet


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.example.eventscanner.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ReportIssueBottomDialog:BottomSheetDialogFragment() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var scanIssue: RadioButton
    private lateinit var planner: RadioButton
    private lateinit var feadback: RadioButton
    private lateinit var others: RadioButton
    private lateinit var closeDialog: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.report_dialog, container, false)

        radioGroup = view.findViewById(R.id.radioGroup)
        scanIssue = view.findViewById(R.id.scan_issue)
        planner = view.findViewById(R.id.about_planner)
        feadback = view.findViewById(R.id.feadback)
        others = view.findViewById(R.id.other)
        closeDialog = view.findViewById(R.id.closeDialog)

        // Set click listener for close button
        closeDialog.setOnClickListener {
            dismiss()  // Close the dialog
        }

        // Optional: Add a listener to the RadioGroup to handle selection
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.scan_issue -> {
                    Toast.makeText(context, "Scan Issue Selected", Toast.LENGTH_SHORT).show()
                }
                R.id.about_planner -> {
                    Toast.makeText(context, "About Planner Selected", Toast.LENGTH_SHORT).show()
                }
                R.id.feadback -> {
                    Toast.makeText(context, "Feedback Selected", Toast.LENGTH_SHORT).show()
                }
                R.id.other -> {
                    Toast.makeText(context, "Other Selected", Toast.LENGTH_SHORT).show()
                }

            }
//            dismiss()
        }
    return view
    }
//    override fun onDetach() {
//        super.onDetach()
//        listener = null  // Prevent memory leaks
//    }
}