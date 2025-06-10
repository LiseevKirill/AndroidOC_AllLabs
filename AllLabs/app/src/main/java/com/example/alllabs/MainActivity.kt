package com.example.alllabs

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alllabs.ui.theme.AllLabsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AllLabsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LabMenu(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LabMenu(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp), // outer padding
        verticalArrangement = Arrangement.Center, // center vertically
        horizontalAlignment = Alignment.CenterHorizontally // center horizontally
    ) {
        Button(
            onClick = { context.startActivity(Intent(context, LabWork2Activity::class.java)) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Laboratory Work 2")
        }

        Button(
            onClick = { context.startActivity(Intent(context, LabWork3Activity::class.java)) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Laboratory Work 3")
        }

        Button(
            onClick = { context.startActivity(Intent(context, LabWork4Activity::class.java)) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Laboratory Work 4")
        }

        Button(
            onClick = { context.startActivity(Intent(context, LabWork5Activity::class.java)) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Laboratory Work 5")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LabMenuPreview() {
    AllLabsTheme {
        LabMenu()
    }
}
