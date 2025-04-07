package com.example.eventscanner.HomeScreen

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import com.example.eventscanner.HistoryFragment
import com.example.eventscanner.ProfileFragment
import com.example.eventscanner.R
import com.example.eventscanner.SignInScreens.SignInFragment
import com.google.android.material.textfield.TextInputEditText

class BookingIdFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.booking_id, container, false)
        val backIcon: ImageView = view.findViewById(R.id.back_icon_btn)
        val verifyButton: AppCompatButton = view.findViewById(R.id.verify_btn)
        val bookingIdInput: TextInputEditText = view.findViewById(R.id.enter_id)

        bookingIdInput.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // When the field gets focus, change its background and hide the hint
                bookingIdInput.setBackgroundResource(R.drawable.edit_text_bg_focused)
                bookingIdInput.hint = "" // Hide hint text
                verifyButton.setBackgroundResource(R.drawable.btn) // Enable the verify button
            } else {
                // When the field loses focus, revert to the original background and show the hint
                bookingIdInput.setBackgroundResource(R.drawable.edit_text_bg)
                bookingIdInput.hint = "Enter booking ID" // Restore hint text
            }
        }

        verifyButton.setOnClickListener {
            showVerifyDialog()
        }

        backIcon.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed() // Navigate back
        }
        return view
    }

    private fun showVerifyDialog() {
        // Inflate the custom layout for the dialog
        val dialogView = layoutInflater.inflate(R.layout.ticket_verify, null)

        // Create the dialog
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        // Set up the Button from the inflated layout
        val gotItToHistory: Button = dialogView.findViewById(R.id.got_it_to_history)

        // Handle the button click
        gotItToHistory.setOnClickListener {
            val dialog = builder.create()


            // Navigate to the HistoryFragment
            navigateToHistoryFragment()
            dialog.dismiss() // Dismiss the dialog when the button is clicked
        }

        // Show the dialog
        builder.create().show()


    }

    private fun navigateToHistoryFragment() {
        // Replace the current fragment with SignInFragment
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.containeeer, HistoryFragment())  // R.id.fragment_container is the container where your fragments are displayed
        transaction.addToBackStack(null)  // Optionally, add this transaction to the back stack
        transaction.commit()

    }

}