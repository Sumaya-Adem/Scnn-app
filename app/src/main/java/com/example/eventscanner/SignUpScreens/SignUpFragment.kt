package com.example.eventscanner.SignUpScreens

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.eventscanner.R
import com.example.eventscanner.SignInScreens.ForgotPasswordFragment
import com.example.eventscanner.SignInScreens.LanguageBottomSheetFragment
import com.example.eventscanner.SignInScreens.SignInFragment
import com.example.eventscanner.SignInScreens.VerificationFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUpFragment : Fragment() {
    private var isPasswordVisible = false
    private lateinit var signupNumber: TextInputEditText
    private lateinit var signupName: TextInputEditText
    private lateinit var signupEmail: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var forgotPassword: TextView
    private lateinit var signUp: Button
    private lateinit var tvEnglish: TextView
    private lateinit var tosignIn:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_sign_up, container, false)

        signupNumber = view.findViewById(R.id.signup_number)
        signupName = view.findViewById(R.id.signup_name)
        password = view.findViewById(R.id.password)
        passwordLayout = view.findViewById(R.id.passwordLayout)
        signupEmail = view.findViewById(R.id.email_sign_up)
        signUp = view.findViewById(R.id.signup_btn)
        tosignIn = view.findViewById(R.id.to_sign_in)


        password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        tvEnglish = view.findViewById(R.id.tvEnglish)

        // Set onClickListener for tvEnglish
        tvEnglish.setOnClickListener {
            // Your logic here, e.g., show a BottomSheetDialogFragment or change something
            Toast.makeText(context, "English clicked", Toast.LENGTH_SHORT).show()

            // Example: Open a BottomSheetDialogFragment (Language bottom sheet) if needed
            val languageBottomSheet = LanguageBottomSheetFragment()
            languageBottomSheet.show(parentFragmentManager, "languageBottomSheet")
        }

        signupEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                signupEmail.setBackgroundResource(R.drawable.edit_text_bg_focused)
                signupEmail.hint = "" // Hide hint text
            } else {
                signupEmail.setBackgroundResource(R.drawable.edit_text_bg)
                signupEmail.hint = "Enter email address" // Restore hint text
            }
        }
        signupNumber.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                signupNumber.setBackgroundResource(R.drawable.edit_text_bg_focused)
                signupNumber.hint = "" // Hide hint text
            } else {
                signupNumber.setBackgroundResource(R.drawable.edit_text_bg)
                signupNumber.hint = "Enter number" // Restore hint text
            }
        }
        signupName.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                signupName.setBackgroundResource(R.drawable.edit_text_bg_focused)
                signupName.hint = "" // Hide hint text
            } else {
                signupName.setBackgroundResource(R.drawable.edit_text_bg)
                signupName.hint = "Enter full name" // Restore hint text
            }
        }
        password.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                password.setBackgroundResource(R.drawable.edit_text_bg_focused)
                password.hint = "" // Hide hint text
            } else {
                password.setBackgroundResource(R.drawable.edit_text_bg)
                password.hint = "Password"
            }
        }
        passwordLayout.setEndIconOnClickListener {
            togglePasswordVisibility()
        }
        val forgotPasswordLink: TextView = view.findViewById(R.id.my_tv_forgot)
        forgotPasswordLink.setOnClickListener {
            navigateToForgotPasswordFragment()
        }
        signUp.setOnClickListener {
            val emailVerifyFragment = EmailVerifyFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, emailVerifyFragment)
                .addToBackStack(null)
                .commit()
        }

        tosignIn.setOnClickListener {
            val signInFragment = SignInFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, signInFragment)
                .addToBackStack(null)
                .commit()
        }

        return  view
    }
    private fun togglePasswordVisibility() {
        if (password.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            // Hide password
            password.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            passwordLayout.setEndIconDrawable(R.drawable.view_off_slash) // Set the "view off" icon
        } else {
            // Show password
            password.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            passwordLayout.setEndIconDrawable(R.drawable.view) // Set the "view" icon
        }
        password.setSelection(password.text?.length ?: 0)

        isPasswordVisible = !isPasswordVisible

    }
    private fun navigateToForgotPasswordFragment() {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, ForgotPasswordFragment())  // Make sure R.id.fragment_container is the container in your activity's layout
        transaction.addToBackStack(null)  // Add the transaction to the back stack to enable back navigation
        transaction.commit()
    }


}