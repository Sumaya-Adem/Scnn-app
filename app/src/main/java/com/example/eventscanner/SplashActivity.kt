package com.example.eventscanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.example.eventscanner.OnboardingScreens.OnboardingActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Handler(Looper.getMainLooper()).postDelayed({
            // Start the next activity (Onboarding or Main Activity)
            val intent = Intent(this, OnboardingActivity::class.java) // or MainActivity
            startActivity(intent)

            // Optionally finish the Splash Activity so it cannot be accessed by pressing back
            finish()
        }, 2000)
    }
}
