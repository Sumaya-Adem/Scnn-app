package com.example.eventscanner.SignInScreens

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.eventscanner.R

class CameraAccessDialog(
    context: Context,
    private val onAllow: () -> Unit,
    private val onBack: () -> Unit
) : Dialog(context)
 {
init {
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    setContentView(R.layout.camere_dialog)

    val allowButton = findViewById<AppCompatButton>(R.id.allow)
    val backButton = findViewById<AppCompatButton>(R.id.back)

    allowButton.setOnClickListener {
        onAllow()
        dismiss()
    }

    backButton.setOnClickListener {
        onBack()
        dismiss()
    }

    window?.setBackgroundDrawableResource(android.R.color.transparent)
}


}