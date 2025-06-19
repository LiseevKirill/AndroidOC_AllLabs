package com.example.lab5

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Task2Activity : AppCompatActivity() {

    private lateinit var tvPowerStatus: TextView
    private lateinit var tvAirplaneModeStatus: TextView

    private val powerReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == Intent.ACTION_POWER_CONNECTED) {
                Toast.makeText(context, "Зарядний пристрій підключено", Toast.LENGTH_SHORT).show()
                tvPowerStatus.text = "Статус зарядки: Підключено"
            }
        }
    }

    private val airplaneModeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
                val isAirplaneOn = intent.getBooleanExtra("state", false)
                val status = if (isAirplaneOn) "Увімкнено" else "Вимкнено"
                tvAirplaneModeStatus.text = "Режим польоту: $status"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task2)

        tvPowerStatus = findViewById(R.id.tvPowerStatus)
        tvAirplaneModeStatus = findViewById(R.id.tvAirplaneModeStatus)
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(powerReceiver, IntentFilter(Intent.ACTION_POWER_CONNECTED))
        registerReceiver(airplaneModeReceiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(powerReceiver)
        unregisterReceiver(airplaneModeReceiver)
    }
}
