package com.example.eventscanner.BottomSheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatRatingBar
import com.example.eventscanner.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RateBottomSheet:BottomSheetDialogFragment() {

    private lateinit var ratingBar: AppCompatRatingBar
    private lateinit var btnNotNow: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.rating_bottom_sheet, container, false)

        ratingBar = view.findViewById(R.id.ratingBar)
        btnNotNow =view.findViewById(R.id.btnNotNow)

        btnNotNow.setOnClickListener {
            val rating = ratingBar.rating // Get the rating value
            Toast.makeText(context, "You rated: $rating stars", Toast.LENGTH_SHORT).show()

            // Handle the submit action (dismiss the BottomSheet, save data, etc.)
            dismiss()
        }

        // You can also listen for rating changes (if needed)
        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            // You can handle real-time updates if needed
            Log.d("Rating", "User rated: $rating stars")
        }


        return view
    }
}