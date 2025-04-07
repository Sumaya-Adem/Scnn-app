package com.example.eventscanner

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.eventscanner.BottomSheet.LogOutBottomSheet
import com.example.eventscanner.SignInScreens.VerificationFragment


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)



        val btnLogout = view.findViewById<View>(R.id.btnlogout)
        val Profile:ImageView=view.findViewById(R.id.profileImage)

        btnLogout.setOnClickListener {
           val logoutBottomSheet = LogOutBottomSheet()
            logoutBottomSheet.show(parentFragmentManager, "logoutBottomSheet")
        }
        Profile.setOnClickListener {
            val profileFragment =ProfileFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.containeeer, profileFragment) // Ensure this is your container ID
                .addToBackStack(null)
                .commit()
        }


        return view
    }

}