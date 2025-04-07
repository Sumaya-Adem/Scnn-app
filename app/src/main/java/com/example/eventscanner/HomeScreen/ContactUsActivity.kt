package com.example.eventscanner.HomeScreen

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.eventscanner.BottomSheet.ReportIssueBottomDialog
import com.example.eventscanner.R
import com.google.android.material.textfield.TextInputEditText

class ContactUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)

        // Initialize views
        val textFeedback: TextInputEditText = findViewById(R.id.feedback_text)
        val titleInput: TextInputEditText = findViewById(R.id.title_input)
        val callPlannerIcon: ImageView = findViewById(R.id.call_planner)
        val telegramIcon: ImageView = findViewById(R.id.talegram)
        val facebookIcon: ImageView = findViewById(R.id.facebook)
        val backIcon:ImageView= findViewById(R.id.back_icon_btn)
        backIcon.setOnClickListener {
            onBackPressed() // Navigate back
        }

        // Set click listeners
        callPlannerIcon.setOnClickListener {
            // Handle call planner icon click
            openDialerWithNumber()
        }

        telegramIcon.setOnClickListener {
            // Handle Telegram icon click
            openTelegram()
        }

        facebookIcon.setOnClickListener {
            // Handle Facebook icon click
            openFacebook()
        }
        // Set click listener for titleInput
        titleInput.setOnClickListener {
            titleInput.hint = null
            // Show a Toast message
            Toast.makeText(this, "title clicked", Toast.LENGTH_SHORT).show()

            // Open a BottomSheetDialogFragment
            val reportBottomSheet = ReportIssueBottomDialog()
            reportBottomSheet.show(supportFragmentManager, "reportIssueBottomSheet")
        }

        // Set focus change listener for textFeedback
        textFeedback.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                textFeedback.setBackgroundResource(R.drawable.edit_text_bg_focused)
                textFeedback.hint = "" // Hide hint text
            } else {
                textFeedback.setBackgroundResource(R.drawable.edit_text_bg)
                textFeedback.hint = "Password" // Restore hint text
            }
        }
    }

    private fun openTelegram() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/your_telegram_link"))
        startActivity(intent)
    }
    private fun openFacebook() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/your_facebook_page"))
        startActivity(intent)
    }
    private fun openDialerWithNumber() {
        val phoneNumber = "123456789" // Replace with the actual phone number
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        startActivity(intent)
    }

}