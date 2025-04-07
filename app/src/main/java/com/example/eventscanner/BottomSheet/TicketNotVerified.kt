package com.example.eventscanner.BottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.eventscanner.HistoryFragment
import com.example.eventscanner.HomeFragment
import com.example.eventscanner.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TicketNotVerified: BottomSheetDialogFragment() {
    private lateinit var ToHome : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.ticket_not_verify, container, false)


        ToHome=view.findViewById(R.id.btnBackToHome)

        ToHome.setOnClickListener {

            navigateToHomeFragment()
        }
        return view
    }
    private fun navigateToHomeFragment() {
        // Replace the current fragment with SignInFragment
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.containeeer, HomeFragment())  // R.id.fragment_container is the container where your fragments are displayed
        transaction.addToBackStack(null)  // Optionally, add this transaction to the back stack
        transaction.commit()
    }

}