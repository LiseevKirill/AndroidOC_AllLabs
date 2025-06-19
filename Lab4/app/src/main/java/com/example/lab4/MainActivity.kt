package com.example.lab4

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main)
        } else {
            setContentView(R.layout.portrait_content)
        }

        // Знаходимо кнопку і ставимо обробник
        val buttonTask2 = findViewById<Button>(R.id.buttonTask2)
        buttonTask2?.setOnClickListener {
            startActivity(Intent(this, Task2Activity::class.java))
        }
    }
}
