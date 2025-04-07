package com.example.eventscanner.OnboardingScreens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.eventscanner.MainActivity
import com.example.eventscanner.R
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class OnboardingActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        viewPager.adapter = OnboardingAdapter(this)
        val wormDotsIndicator = findViewById<WormDotsIndicator>(R.id.dotsIndicator)
        val adapter = OnboardingAdapter(this) // Your ViewPager2 adapter

        viewPager.adapter = adapter
        wormDotsIndicator.attachTo(viewPager)


    }
}