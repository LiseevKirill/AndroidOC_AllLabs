package com.example.lab5

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class Task1Activity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val N = 4 * 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_task1)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 4)

        ViewCompat.setOnApplyWindowInsetsListener(recyclerView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val items = List(N) {
            ColorItem(
                color = Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
                number = Random.nextInt(1, 100)
            )
        }

        recyclerView.adapter = ColorAdapter(this, items)
    }
}
