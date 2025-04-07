package com.example.eventscanner.HomeScreen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.eventscanner.ProfileFragment
import com.example.eventscanner.R
import com.example.eventscanner.SignInScreens.VerificationFragment
import com.example.eventscanner.SignUpScreens.UploadPhotoBottomSheet
import com.google.android.material.textfield.TextInputEditText

class EditProfileFragment : Fragment() {
    private lateinit var newProfile : ImageView
    private lateinit var editNumber: TextInputEditText
    private lateinit var editName: TextInputEditText
    private lateinit var editEmail: TextInputEditText
    private lateinit var Update: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_edit_profile, container, false)

        (activity as? HomeActivity)?.setBottomNavigationVisibility(false)

        val backIcon:ImageView=view.findViewById(R.id.back_icon_btn)

        editEmail =  view.findViewById(R.id.edit_email)
        editName = view.findViewById(R.id.edit_name)
        editNumber = view.findViewById(R.id.edit_number)
        newProfile = view.findViewById(R.id.new_profile)
        Update=view.findViewById(R.id.update_btn)

        backIcon.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        Update.setOnClickListener {
//                // Return to profile fragment
            parentFragmentManager.popBackStack()


//            val updatedName = setupName.text.toString()
//            val updatedEmail = setupEmail.text.toString()
//
//            // Validate the input (optional)
//            if (updatedName.isNotEmpty() && updatedEmail.isNotEmpty()) {
//                // Save the updated data
//                saveUpdatedProfile(updatedName, updatedEmail)
//            } else {
//                // Show an error message if the input is empty
//                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
//            }

        }
        newProfile.setOnClickListener {
            val bottomSheet = UploadPhotoBottomSheet()
            bottomSheet.show(requireActivity().supportFragmentManager, "UploadPhotoBottomSheet")
        }

        editNumber.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editNumber.setBackgroundResource(R.drawable.edit_text_bg_focused)
                editNumber.hint = "" // Hide hint text
            } else {
                editNumber.setBackgroundResource(R.drawable.edit_text_bg)
                editNumber.hint = "Enter Number" // Restore hint text
            }
        }
        editEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editEmail.setBackgroundResource(R.drawable.edit_text_bg_focused)
                editEmail.hint = "" // Hide hint text
            } else {
                editEmail.setBackgroundResource(R.drawable.edit_text_bg)
                editEmail.hint = "Enter email address" // Restore hint text
            }
        }
        editName.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editName.setBackgroundResource(R.drawable.edit_text_bg_focused)
                editName.hint = "" // Hide hint text
            } else {
                editName.setBackgroundResource(R.drawable.edit_text_bg)
                editName.hint = "Enter full name " // Restore hint text
            }
        }


    return view
    }

//    private fun saveUpdatedProfile(name: String, email: String) {
//        // Save the data to SharedPreferences
//        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        editor.putString("savedName", name)
//        editor.putString("savedEmail", email)
//        editor.apply()
//
//        // Provide feedback to the user
//        Toast.makeText(requireContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show()
//    }


}