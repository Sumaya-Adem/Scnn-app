package com.example.eventscanner.SignInScreens

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.example.eventscanner.HomeFragment
import com.example.eventscanner.HomeScreen.HomeActivity
import com.example.eventscanner.R
import com.example.eventscanner.SignUpScreens.SignUpFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignInFragment : Fragment() {
    private lateinit var emailSignIn: TextInputEditText
    private lateinit var passwordSignIn: TextInputEditText
    private lateinit var organizerEditText: TextInputEditText
    private var isPasswordVisible = false
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var tvEnglish: TextView
    private lateinit var toSignUp:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        emailSignIn = view.findViewById(R.id.email_sign_in)
        passwordSignIn = view.findViewById(R.id.password)
        passwordLayout = view.findViewById(R.id.passwordLayout)
        organizerEditText = view.findViewById(R.id.organizer)
        toSignUp =  view.findViewById(R.id.to_sign_up)
        val signIn: Button = view.findViewById(R.id.sign_btn)
        // Inside your Fragment or Activity




        passwordSignIn.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        organizerEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                organizerEditText.hint = "" // Hide hint when focused
            }
        }
        tvEnglish = view.findViewById(R.id.tvEnglish)

        // Set onClickListener for tvEnglish
        tvEnglish.setOnClickListener {
            // Your logic here, e.g., show a BottomSheetDialogFragment or change something
            Toast.makeText(context, "English clicked", Toast.LENGTH_SHORT).show()

            // Example: Open a BottomSheetDialogFragment (Language bottom sheet) if needed
            val languageBottomSheet = LanguageBottomSheetFragment()
            languageBottomSheet.show(parentFragmentManager, "languageBottomSheet")
        }

        emailSignIn.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                emailSignIn.setBackgroundResource(R.drawable.edit_text_bg_focused)
                emailSignIn.hint = "" // Hide hint text
            } else {
                emailSignIn.setBackgroundResource(R.drawable.edit_text_bg)
                emailSignIn.hint = "Enter email address" // Restore hint text
            }
        }
        passwordSignIn.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                passwordSignIn.setBackgroundResource(R.drawable.edit_text_bg_focused)
                passwordSignIn.hint = "" // Hide hint text
            } else {
                passwordSignIn.setBackgroundResource(R.drawable.edit_text_bg)
                passwordSignIn.hint = "Password"
            }
        }
        passwordLayout.setEndIconOnClickListener {
            togglePasswordVisibility()
        }
        toSignUp.setOnClickListener {
            val signUpFragment = SignUpFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, signUpFragment)
                .addToBackStack(null)
                .commit()
        }
        signIn.setOnClickListener {
            val intent = Intent(requireContext(), HomeActivity::class.java)

            startActivity(intent)
        }

        return view
    }
    private fun togglePasswordVisibility() {
        if (passwordSignIn.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            // Hide password
            passwordSignIn.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            passwordLayout.setEndIconDrawable(R.drawable.view_off_slash) // Set the "view off" icon
        } else {
            // Show password
            passwordSignIn.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            passwordLayout.setEndIconDrawable(R.drawable.view) // Set the "view" icon
        }
        passwordSignIn.setSelection(passwordSignIn.text?.length ?: 0)

        isPasswordVisible = !isPasswordVisible
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)


            val organizerField = view.findViewById<TextInputEditText>(R.id.organizer)
            organizerField.setOnClickListener {
                showOrganizerDialog()
            }
            val forgotPasswordLink: TextView = view.findViewById(R.id.my_tv_forgot)
            forgotPasswordLink.setOnClickListener {
                navigateToForgotPasswordFragment()
            }
        }

    private fun showOrganizerDialog() {
        val organizerBottomSheetFragment = OrganizerBottomSheetFragment()

        // Pass the reference of organizer EditText to the BottomSheetFragment
        organizerBottomSheetFragment.setOrganizerEditTextReference(organizerEditText)

        // Show the BottomSheetDialogFragment
        organizerBottomSheetFragment.show(requireActivity().supportFragmentManager, organizerBottomSheetFragment.tag)
    }
    private fun navigateToForgotPasswordFragment() {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, ForgotPasswordFragment())  // Make sure R.id.fragment_container is the container in your activity's layout
        transaction.addToBackStack(null)  // Add the transaction to the back stack to enable back navigation
        transaction.commit()
    }
}