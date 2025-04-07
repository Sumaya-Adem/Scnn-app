package com.example.eventscanner.SignUpScreens

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.eventscanner.HomeFragment
import com.example.eventscanner.HomeScreen.HomeActivity
import com.example.eventscanner.R
import com.example.eventscanner.SignInScreens.OrganizerBottomSheetFragment
import com.example.eventscanner.SignInScreens.SignInFragment
import com.google.android.material.textfield.TextInputEditText


class SetUpAccountFragment : Fragment(),UploadPhotoBottomSheet.OnPhotoSelectedListener {
    private lateinit var toolbarTitle: TextView
    private lateinit var newProfile :ImageView
    private lateinit var imgProfile: ImageView
    private lateinit var setupName: TextInputEditText
    private lateinit var setupEmail: TextInputEditText
    private lateinit var organizerEditText: TextInputEditText
    private lateinit var Continue:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_set_up_account, container, false)
        imgProfile = view.findViewById(R.id.user_profile_photo)
        val backIcon:ImageView=view.findViewById(R.id.back_icon_btn)

        setupEmail =  view.findViewById(R.id.email_set_up)
        setupName = view.findViewById(R.id.setup_name)
        newProfile = view.findViewById(R.id.new_profile)
        organizerEditText = view.findViewById(R.id.organizer)
        Continue = view.findViewById(R.id.continue_btn)



        organizerEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                organizerEditText.hint = "" // Hide hint when focused
            }
        }
        Continue.setOnClickListener {
            showSetUpDialog()
        }
        newProfile.setOnClickListener {
            showPhotoBottomSheet()
//            val bottomSheet = UploadPhotoBottomSheet()
//            bottomSheet.show(requireActivity().supportFragmentManager, "UploadPhotoBottomSheet")
        }

        setupEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                setupEmail.setBackgroundResource(R.drawable.edit_text_bg_focused)
                setupEmail.hint = "" // Hide hint text
            } else {
                setupEmail.setBackgroundResource(R.drawable.edit_text_bg)
                setupEmail.hint = "Enter email address" // Restore hint text
            }
        }
        setupName.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                setupName.setBackgroundResource(R.drawable.edit_text_bg_focused)
                setupName.hint = "" // Hide hint text
            } else {
                setupName.setBackgroundResource(R.drawable.edit_text_bg)
                setupName.hint = "Enter full name " // Restore hint text
            }
        }

        backIcon.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }


        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val organizerField = view.findViewById<TextInputEditText>(R.id.organizer)
        organizerField.setOnClickListener {
            showOrganizerDialog()
        }
    }

    private fun showOrganizerDialog() {
        val organizerBottomSheetFragment = OrganizerBottomSheetFragment()

        // Pass the reference of organizer EditText to the BottomSheetFragment
        organizerBottomSheetFragment.setOrganizerEditTextReference(organizerEditText)

        // Show the BottomSheetDialogFragment
        organizerBottomSheetFragment.show(requireActivity().supportFragmentManager, organizerBottomSheetFragment.tag)
    }
    private fun showSetUpDialog() {
        // Inflate the custom layout for the dialog

        // Create the dialog
        val dialogView = layoutInflater.inflate(R.layout.got_it_dialog, null)

// Create the dialog builder
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

// Set up the Button from the inflated layout
        val GotItButton: Button = dialogView.findViewById(R.id.btnBackToHome)

// Create the dialog before setting the listener
        val dialog = builder.create()

// Set the button click listener
        GotItButton.setOnClickListener {
            // Create an intent to navigate to HomeActivity
            val intent = Intent(requireContext(), HomeActivity::class.java)

            // Optional: If you want to finish the current activity (so that the user cannot return to it via the back button):
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            dialog.dismiss()
        }
        dialog.show()
    }
    private fun navigateToHomeFragment() {
        // Replace the current fragment with SignInFragment
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, HomeFragment())  // R.id.fragment_container is the container where your fragments are displayed
        transaction.addToBackStack(null)  // Optionally, add this transaction to the back stack
        transaction.commit()
    }
    private fun showPhotoBottomSheet() {
        val bottomSheet = UploadPhotoBottomSheet()
        bottomSheet.show(parentFragmentManager, "UploadPhotoBottomSheet")
    }

    // Handle camera photo result
    override fun onPhotoSelected(bitmap: Bitmap) {
        imgProfile.setImageBitmap(bitmap)
        // You might want to save this bitmap to storage or upload to server
    }

    // Handle gallery photo result
    override fun onPhotoSelected(uri: Uri) {
        imgProfile.setImageURI(uri)
        // You might want to save this URI or upload to server
    }


}