package com.example.eventscanner.SignInScreens

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import android.widget.ProgressBar

import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.eventscanner.R

class VerificationFragment : Fragment() {

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_verification, container, false)

        val otpEditText1: EditText = view.findViewById(R.id.otpEditText1)
        val otpEditText2: EditText = view.findViewById(R.id.otpEditText2)
        val otpEditText3: EditText = view.findViewById(R.id.otpEditText3)
        val otpEditText4: EditText = view.findViewById(R.id.otpEditText4)
       val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
       val backIcon:ImageView = view.findViewById(R.id.back_icon_btn)

        backIcon.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        // Automatically show keyboard when screen loads
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        // Ensure keyboard opens when clicking OTP field
        otpEditText1.requestFocus()

        val editTexts = arrayOf(otpEditText1, otpEditText2, otpEditText3, otpEditText4)


        for (i in editTexts.indices) {
            editTexts[i].apply {
                inputType = android.text.InputType.TYPE_CLASS_NUMBER // Show numeric keyboard
                setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) setBackgroundResource(R.drawable.edit_text_bg_focused)
                    else setBackgroundResource(R.drawable.edit_text_bg)
                }

                addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        if (s?.length == 1 && i < editTexts.size - 1) {
                            editTexts[i + 1].requestFocus()
                        }
                        if (otpEditText1.text.length == 1 &&
                            otpEditText2.text.length == 1 &&
                            otpEditText3.text.length == 1 &&
                            otpEditText4.text.length == 1) {

                            progressBar.visibility = View.VISIBLE

                            Handler(Looper.getMainLooper()).postDelayed({
                                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                                transaction.replace(R.id.fragmentContainer, CreatePasswordFragment())
                                transaction.addToBackStack(null)  // Allows back navigation
                                transaction.commit()
                            }, 5000)
                        }
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                })
            }
        }
        backIcon.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        return view
    }

}