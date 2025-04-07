package com.example.eventscanner

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.eventscanner.BottomSheet.DeleteBottomSheet
import com.example.eventscanner.BottomSheet.RateBottomSheet
import com.example.eventscanner.HomeScreen.ConditionsActivity
import com.example.eventscanner.HomeScreen.ContactUsActivity
import com.example.eventscanner.HomeScreen.EditProfileFragment
import com.example.eventscanner.HomeScreen.HomeActivity
import com.example.eventscanner.HomeScreen.PrivacyActivity
import com.example.eventscanner.SignInScreens.LanguageBottomSheetFragment


class ProfileFragment : Fragment() {


    private lateinit var editProfile: LinearLayout
    private lateinit var userNameText: TextView
    private lateinit var userEmailText: TextView
    private lateinit var Englishtext:TextView
    private lateinit var Englishclick:ImageView
    private lateinit var languEng:LinearLayout
    private lateinit var ContactUs:LinearLayout
    private lateinit var Policy:LinearLayout
    private lateinit var TermsConditions:LinearLayout
    private lateinit var RateUs:LinearLayout
    private lateinit var Delete:LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val backIcon:ImageView=view.findViewById(R.id.back_icon_btn)
        languEng= view.findViewById(R.id.lneng)
        ContactUs=view.findViewById(R.id.conatctus)
        RateUs=view.findViewById(R.id.rate_us)
        Delete=view.findViewById(R.id.delete)
        Policy=view.findViewById(R.id.policy)
        TermsConditions=view.findViewById(R.id.terms_conditions
        )
        Englishclick = view.findViewById(R.id.english_click)
        Englishtext = view.findViewById(R.id.tvEnglish)

        backIcon.setOnClickListener {
             val intent = Intent(requireContext(), HomeActivity::class.java)
             intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
             startActivity(intent)
             requireActivity().finish()

//            startActivity().onBackPressedDispatcher.onBackPressed()
        }
        languEng.setOnClickListener {
            // Your logic here, e.g., show a BottomSheetDialogFragment or change something
//            Toast.makeText(context, "English clicked", Toast.LENGTH_SHORT).show()

            // Example: Open a BottomSheetDialogFragment (Language bottom sheet) if needed
            val languageBottomSheet = LanguageBottomSheetFragment()
            languageBottomSheet.show(parentFragmentManager, "languageBottomSheet")
        }
        ContactUs.setOnClickListener {
            val intent = Intent(requireContext(), ContactUsActivity::class.java)
            startActivity(intent)
        }
        Policy.setOnClickListener {
            val intent = Intent(requireContext(), PrivacyActivity::class.java)
            startActivity(intent)
        }
        TermsConditions.setOnClickListener {
            val intent = Intent(requireContext(), ConditionsActivity::class.java)
            startActivity(intent)
        }
        RateUs.setOnClickListener {
            // Example: Open a BottomSheetDialogFragment (Language bottom sheet) if needed
            val rateUsBottomSheet = RateBottomSheet()
            rateUsBottomSheet.show(parentFragmentManager, "rateUsBottomSheet")
        }
        Delete.setOnClickListener {
            // Example: Open a BottomSheetDialogFragment (Language bottom sheet) if needed
            val deleteBottomSheet = DeleteBottomSheet()
            deleteBottomSheet.show(parentFragmentManager, "deleteAccountBottomSheet")
        }


        // Initialize the edit profile button
        editProfile = view.findViewById(R.id.edit_profile)

        // Set a click listener on the edit profile button
        editProfile.setOnClickListener {

            Toast.makeText(context, "Edit profile clicked", Toast.LENGTH_SHORT).show()

            val editProfileFragment = EditProfileFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.containeeer, editProfileFragment)
                .addToBackStack(null)
                .commit()
            Log.d("FragmentTransaction", "Attempting to replace fragment in container: R.id.containeeer")

        }

        return view
    }



}