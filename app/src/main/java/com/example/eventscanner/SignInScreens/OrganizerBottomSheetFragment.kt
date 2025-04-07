package com.example.eventscanner.SignInScreens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.eventscanner.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText

class OrganizerBottomSheetFragment: BottomSheetDialogFragment() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var radioOrganizer: RadioButton
    private lateinit var radioScanner: RadioButton
    private lateinit var closeDialog: ImageView
    private lateinit var organizerEditText: TextInputEditText

    // Pass the organizer EditText reference to this fragment for interaction
    private var organizerEditTextReference: TextInputEditText? = null

    // Use this method to pass the reference to the organizer EditText
    fun setOrganizerEditTextReference(editText: TextInputEditText) {
        this.organizerEditTextReference = editText
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_organizer, container, false)

        // Initialize views
        radioGroup = view.findViewById(R.id.radioGroupOrganizer)
        radioOrganizer = view.findViewById(R.id.radioOrganizer)
        radioScanner = view.findViewById(R.id.radioScanner)
        closeDialog = view.findViewById(R.id.closeDialog)


        // Handle the close button
        closeDialog.setOnClickListener {
            dismiss() // Dismiss the bottom sheet
        }

        // Handle the radio group selection
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedText = when (checkedId) {
                R.id.radioOrganizer -> "Organizer"
                R.id.radioScanner -> "Scanner"
                else -> ""
            }

            if (selectedText.isNotEmpty()) {
                // Check if the reference to organizer EditText is not null
                organizerEditTextReference?.setText(selectedText)
                dismiss()
            }
        }

        return view
    }

}