package com.example.eventscanner.OnboardingScreens

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.eventscanner.MainActivity
import com.example.eventscanner.R
import com.example.eventscanner.SignInScreens.SignInFragment
import com.example.eventscanner.SignInScreens.VerificationFragment


class OnboardingFragment : Fragment() {
    companion object {
        private const val ARG_POSITION = "position"

        fun newInstance(position: Int): OnboardingFragment {
            val fragment = OnboardingFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_POSITION, position)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt(ARG_POSITION) ?: 0
        val imageView1 = view.findViewById<ImageView>(R.id.imageView1)
        val imageView2 = view.findViewById<ImageView>(R.id.imageView2)
        val textTitle = view.findViewById<TextView>(R.id.textTitle)
        val textDesc = view.findViewById<TextView>(R.id.textDesc)
        val nextButton =view.findViewById<Button>(R.id.nest_btn)
        val downView = view.findViewById<View>(R.id.downview)
        val topView = view.findViewById<View>(R.id.topview)
        val Skip:TextView=view.findViewById(R.id.skip)


        Skip.setOnClickListener {
            val tosigninFragment = SignInFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, tosigninFragment) // Ensure this is your container ID
                .addToBackStack(null)
                .commit()
        }
        // Show Two Images for First Two Screens
        when (position) {
            0 -> {
                textTitle.text = "Create an unforgettable \n event experience"
                textDesc.text = "Organizers can use the app to manage \n events, managing attendees"
                imageView1.setImageResource(R.drawable.onboarding_image1)
                imageView2.setImageResource(R.drawable.onboarding_image2)
            }
            1 -> {
                textTitle.text = "Create an unforgettable \n event experience"
                textDesc.text = "Organizers can use the app to manage \n events, managing attendees"
                imageView1.setImageResource(R.drawable.onboarding_image3)
                imageView2.setImageResource(R.drawable.onboarding_image4)
            }
            2 -> {
                textTitle.text = "We ensures your events is a \n complete success"
                textDesc.text = "We send push notifications to attendees to \n keep them updated on upcoming events"
                imageView1.setImageResource(R.drawable.onboarding_image5)
                imageView2.visibility = View.GONE // Hide second image for this screen
            }
            3 -> {
                textTitle.text = "Let us take care of all your \n event details"
                textDesc.text = "We enable organizers to sell event tickets \n direct through our app"
                imageView1.setImageResource(R.drawable.onboarding_image6)
                imageView2.visibility = View.GONE // Hide second image for this screen

                nextButton.text = "Get Started"
            }
        }
        when (position) {
            0, 1 -> {
                downView.visibility = View.VISIBLE
                topView.visibility = View.VISIBLE
            }
            2, 3 -> {
                downView.visibility = View.GONE
                topView.visibility = View.GONE
            }
        }

        // Handle button click to navigate pages
        nextButton.setOnClickListener {
            val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
            if (position < 3) {
                viewPager?.currentItem = position + 1
            } else {
                val prefs: SharedPreferences = requireActivity().getSharedPreferences("AppPrefs", 0)
                prefs.edit().putBoolean("onboarding_done", true).apply()

                // Move to MainActivity where SignInFragment will be loaded
                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }
    }




}