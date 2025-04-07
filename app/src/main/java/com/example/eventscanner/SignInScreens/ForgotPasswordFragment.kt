package com.example.eventscanner.SignInScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import com.example.eventscanner.R
import com.google.android.material.textfield.TextInputEditText

class ForgotPasswordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_forgot_password, container, false)

        val backIcon:ImageView=view.findViewById(R.id.back_icon_btn)
        val sendButton = view.findViewById<AppCompatButton>(R.id.send_btn)
        val emailSignIn = view.findViewById<TextInputEditText>(R.id.email_forgot)
        emailSignIn.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                emailSignIn.setBackgroundResource(R.drawable.edit_text_bg_focused)
                emailSignIn.hint = "" // Hide hint text

                    sendButton.setBackgroundResource(R.drawable.btn)

            } else {
                emailSignIn.setBackgroundResource(R.drawable.edit_text_bg)
                emailSignIn.hint = "Enter email address" // Restore hint text
            }
        }

        sendButton.setOnClickListener {
            val verificationFragment = VerificationFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, verificationFragment) // Ensure this is your container ID
                .addToBackStack(null)
                .commit()
        }
        backIcon.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        return view
    }



}