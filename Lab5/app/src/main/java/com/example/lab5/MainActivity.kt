package com.example.lab5

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTask1 = findViewById<Button>(R.id.buttonTask1)
        val btnTask2 = findViewById<Button>(R.id.buttonTask2)

        btnTask1.setOnClickListener {
            startActivity(Intent(this, Task1Activity::class.java))
        }
        btnTask2.setOnClickListener {
            startActivity(Intent(this, Task2Activity::class.java))
        }
    }
}
