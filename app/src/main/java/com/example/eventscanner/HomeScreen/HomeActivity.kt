package com.example.eventscanner.HomeScreen


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.eventscanner.HistoryFragment
import com.example.eventscanner.HomeFragment
import com.example.eventscanner.ProfileFragment
import com.example.eventscanner.R
import com.example.eventscanner.Scan.ScanActivity
import com.example.eventscanner.SignUpScreens.CheckInBottomSheet
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {
    private lateinit var bottomNav:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
//        Log.d("HomeActivity", "Activity Started")

        bottomNav = findViewById(R.id.bottom_navigation)

        val fab:FloatingActionButton=findViewById(R.id.floating_btn)



        fab.setOnClickListener {
            val bottomSheet =CheckInBottomSheet()
            bottomSheet.show(supportFragmentManager, "ScanCoreBottomSheet")
        }

        // Load the default fragment (HomeFragment)
        loadFragment(HomeFragment())

        // Set up the Bottom Navigation View item selected listener
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.profile -> {
                    loadFragment(ProfileFragment()) // Load HomeFragment
                    return@setOnItemSelectedListener true
                }
                R.id.history -> {
                    loadFragment(HistoryFragment()) // Load HistoryFragment
                    return@setOnItemSelectedListener true
                }
                else -> false
            }
        }
    }

    fun setBottomNavigationVisibility(visible: Boolean) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation) // Replace with your ID
        bottomNav.visibility = if (visible) View.VISIBLE else View.GONE
        val fab:FloatingActionButton=findViewById(R.id.floating_btn)
        fab.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.containeeer, fragment)
        transaction.addToBackStack(null) // to manage back navigation
        transaction.commit()
    }
}