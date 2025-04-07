package com.example.eventscanner.BottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.eventscanner.HistoryFragment
import com.example.eventscanner.R
import com.example.eventscanner.SignInScreens.SignInFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TicketVerifiedBottom:BottomSheetDialogFragment() {
    private lateinit var ToHistory :Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.ticket_verify, container, false)


        ToHistory=view.findViewById(R.id.got_it_to_history)

        ToHistory.setOnClickListener {

            navigateToHistoryFragment()
        }
        return view
    }
    private fun navigateToHistoryFragment() {
        // Replace the current fragment with SignInFragment
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.containeeer, HistoryFragment())  // R.id.fragment_container is the container where your fragments are displayed
        transaction.addToBackStack(null)  // Optionally, add this transaction to the back stack
        transaction.commit()
    }

}