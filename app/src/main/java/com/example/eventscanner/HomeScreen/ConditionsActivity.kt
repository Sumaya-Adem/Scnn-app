package com.example.eventscanner.HomeScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.eventscanner.R

class ConditionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conditions)

        val backIcon:ImageView= findViewById(R.id.back_icon_btn)
        backIcon.setOnClickListener {
           onBackPressed() // Navigate back
        }
    }
}