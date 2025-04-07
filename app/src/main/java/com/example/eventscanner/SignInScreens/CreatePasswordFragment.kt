package com.example.eventscanner.SignInScreens

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.eventscanner.R
import com.google.android.material.textfield.TextInputLayout


class CreatePasswordFragment : Fragment() {
    private lateinit var password1: EditText
    private lateinit var password2: EditText
    private lateinit var passwordLayout1: TextInputLayout
    private lateinit var passwordLayout2: TextInputLayout
    private var isPasswordVisible1 = false
    private var isPasswordVisible2 = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_create_password, container, false)
        // Find the button in the fragment layout
        val changePasswordButton: Button = view.findViewById(R.id.reset_btn)
        val backIcon :ImageView = view.findViewById(R.id.back_icon_btn)

        // Set an OnClickListener to show the dialog when the button is clicked
        changePasswordButton.setOnClickListener {
            showPasswordChangedDialog()
        }
        password1= view.findViewById(R.id.password1)
         password2= view.findViewById(R.id.password2)
       passwordLayout1 = view.findViewById(R.id.passwordLayout1)
        passwordLayout2 = view.findViewById(R.id.passwordLayout2)
        password1.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                password1.setBackgroundResource(R.drawable.edit_text_bg_focused)
                password1.hint = ""
            } else {
                password1.setBackgroundResource(R.drawable.edit_text_bg)
                password1.hint = "Enter password"
            }
        }

        // Focus change listener for passwordLayout2
        password2.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                password2.setBackgroundResource(R.drawable.edit_text_bg_focused)
                password2.hint = ""
            } else {
                password2.setBackgroundResource(R.drawable.edit_text_bg)
                password2.hint = "Confirm password"
            }
        }

        // Toggle password visibility for passwordLayout1
        passwordLayout1.setEndIconOnClickListener {
            togglePasswordVisibility(password1, passwordLayout1, isPasswordVisible1)
            isPasswordVisible1 = !isPasswordVisible1
        }

        // Toggle password visibility for passwordLayout2
        passwordLayout2.setEndIconOnClickListener {
            togglePasswordVisibility(password2, passwordLayout2, isPasswordVisible2)
            isPasswordVisible2 = !isPasswordVisible2
        }
        backIcon.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }



        return view

    }

    private fun showPasswordChangedDialog() {
        // Inflate the custom layout for the dialog
        val dialogView = layoutInflater.inflate(R.layout.congrats_dialog, null)

        // Create the dialog
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)
        val dialog = builder.create()

        // Set up the Button from the inflated layout
        val signInButton: Button = dialogView.findViewById(R.id.btnBackToSignIn)

        // Handle the button click
        signInButton.setOnClickListener {
            // Navigate to the Sign In Fragment
            navigateToSignInFragment()
//            val dialog = builder.create()

            // Dismiss the dialog
            dialog.dismiss()
        }
        // Dismiss the dialog
        dialog.show()

    }

    private fun navigateToSignInFragment() {
        // Replace the current fragment with SignInFragment
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, SignInFragment())  // R.id.fragment_container is the container where your fragments are displayed
        transaction.addToBackStack(null)  // Optionally, add this transaction to the back stack
        transaction.commit()
    }
    private fun togglePasswordVisibility(passwordField: EditText, passwordLayout: TextInputLayout, isVisible: Boolean) {
        if (isVisible) {
            // Hide password
            passwordField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            passwordLayout.setEndIconDrawable(R.drawable.view_off_slash) // Set "view off" icon
        } else {
            // Show password
            passwordField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            passwordLayout.setEndIconDrawable(R.drawable.view) // Set "view" icon
        }
        passwordField.setSelection(passwordField.text?.length ?: 0)
    }


}