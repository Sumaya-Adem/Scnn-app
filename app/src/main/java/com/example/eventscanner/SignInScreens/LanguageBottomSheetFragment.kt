package com.example.eventscanner.SignInScreens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.example.eventscanner.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LanguageBottomSheetFragment:BottomSheetDialogFragment() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioEnglish: RadioButton
    private lateinit var radioArabic: RadioButton
    private lateinit var radioSwahili: RadioButton
    private lateinit var closeDialog: ImageView
    private lateinit var tvEnglish: TextView  // Assuming you have a TextView to display selected language

    // Pass the language TextView reference to this fragment
    private var languageTextViewReference: TextView? = null

    // Use this method to pass the reference to the language TextView
    fun setLanguageTextViewReference(textView: TextView) {
        this.languageTextViewReference = textView
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_language, container, false)

        // Initialize views
        radioGroup = view.findViewById(R.id.radioGroupLanguage)
        radioEnglish = view.findViewById(R.id.radioEnglish)
        radioArabic = view.findViewById(R.id.radioArabic)
        radioSwahili = view.findViewById(R.id.radioSwahili)
        closeDialog = view.findViewById(R.id.closeDialog)

        // Handle the close button
        closeDialog.setOnClickListener {
            dismiss() // Dismiss the bottom sheet
        }

        // Handle the radio group selection
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedLanguage = when (checkedId) {
                R.id.radioEnglish -> "English"
                R.id.radioArabic -> "عربي"
                R.id.radioSwahili -> "Swahili"
                else -> ""
            }

            if (selectedLanguage.isNotEmpty()) {
                // Check if the reference to language TextView is not null
                languageTextViewReference?.text = selectedLanguage
                dismiss()  // Close the bottom sheet dialog after selection
            }
        }

        return view
    }
}