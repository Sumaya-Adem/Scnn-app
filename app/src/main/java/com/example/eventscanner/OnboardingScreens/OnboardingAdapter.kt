package com.example.eventscanner.OnboardingScreens

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingAdapter (fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 4 // Number of onboarding screens

    override fun createFragment(position: Int): Fragment {
        return OnboardingFragment.newInstance(position)
    }
}