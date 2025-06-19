package com.example.lab5

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class BootCompletedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {

            Log.d("BootCompletedReceiver", "Пристрій повністю завантажено")
           
            Toast.makeText(context, "Пристрій повністю завантажено", Toast.LENGTH_LONG).show()
        }
    }
}
