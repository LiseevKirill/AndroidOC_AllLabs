package com.example.lab3

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.lab3.ui.theme.Lab3Theme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete

class MainActivity : ComponentActivity() {

    private lateinit var addContactLauncher: ManagedActivityResultLauncher<Intent, Instrumentation.ActivityResult>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Lab3Theme {
                var contacts by remember { mutableStateOf(listOf<Contact>()) }
                val context = this

                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartActivityForResult()
                ) { result ->
                    if (result.resultCode == Activity.RESULT_OK) {
                        result.data?.let {
                            val name = it.getStringExtra("name") ?: return@let
                            val email = it.getStringExtra("email") ?: return@let
                            val phone = it.getStringExtra("phone") ?: return@let
                            val photo = it.getParcelableExtra<Bitmap>("photo") ?: return@let
                            contacts = contacts + Contact(name, email, phone, photo)
                        }
                    }
                }

                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(onClick = {
                            val intent = Intent(context, AddContactActivity::class.java)
                            launcher.launch(intent)
                        }) {
                            Text("+")
                        }
                    }
                ) { padding ->
                    ContactList(
                        contacts = contacts,
                        onDelete = { contact -> contacts = contacts - contact },
                        modifier = Modifier.padding(padding)
                    )
                }
            }
        }
    }
}

@Composable
fun ContactList(
    contacts: List<Contact>,
    onDelete: (Contact) -> Unit,
    modifier: Modifier = Modifier
) {
    if (contacts.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Список контактів порожній")
        }
    } else {
        LazyColumn(modifier = modifier.padding(16.dp)) {
            items(contacts) { contact ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            bitmap = contact.photo.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .size(64.dp)
                                .padding(end = 16.dp)
                        )
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Ім’я: ${contact.name}")
                            Text("Email: ${contact.email}")
                            Text("Телефон: ${contact.phone}")
                        }
                        IconButton(onClick = { onDelete(contact) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }
            }
        }
    }
}
