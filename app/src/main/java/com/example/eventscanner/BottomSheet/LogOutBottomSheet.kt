package com.example.eventscanner.BottomSheet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.commit
import com.example.eventscanner.R
import com.example.eventscanner.SignInScreens.SignInFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LogOutBottomSheet:BottomSheetDialogFragment() {
    private lateinit var yesLogoutButton: Button
    private lateinit var cancelButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.log_out_dialog, container, false)

        yesLogoutButton = view.findViewById(R.id.yes_logout)
        cancelButton = view.findViewById(R.id.cancel)

        // Set button click listeners
        yesLogoutButton.setOnClickListener {
            // Perform logout action here

//            Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()
//            dismiss() // Dismiss the BottomSheet
            val sharedPref = requireContext().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
            sharedPref.edit().clear().apply()

            // 2. Navigate directly to SignInFragment
            parentFragmentManager.commit {
                replace(R.id.fragmentContainer, SignInFragment())
                setReorderingAllowed(true)
            }

            // 3. Dismiss the dialog/bottom sheet
            dismiss()
        }

        cancelButton.setOnClickListener {
            dismiss() // Dismiss the BottomSheet
        }
        return view

    }

}