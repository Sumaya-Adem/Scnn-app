package com.example.eventscanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eventscanner.OnboardingScreens.OnboardingFragment
import com.example.eventscanner.SignInScreens.SignInFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences("AppPrefs", 0)
        val onboardingDone = prefs.getBoolean("onboarding_done", false)

        if (onboardingDone) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, SignInFragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, OnboardingFragment.newInstance(0))
                .commit()
        }
    }
}
