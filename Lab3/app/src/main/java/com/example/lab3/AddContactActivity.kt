package com.example.lab3

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddContactActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private var contactPhoto: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        val nameInput = findViewById<EditText>(R.id.editName)
        val emailInput = findViewById<EditText>(R.id.editEmail)
        val phoneInput = findViewById<EditText>(R.id.editPhone)
        val takePhotoButton = findViewById<Button>(R.id.btnTakePhoto)
        val addButton = findViewById<Button>(R.id.btnAdd)
        imageView = findViewById(R.id.imagePreview)

        takePhotoButton.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 100)
        }

        addButton.setOnClickListener {
            val name = nameInput.text.toString()
            val email = emailInput.text.toString()
            val phone = phoneInput.text.toString()
            val photo = contactPhoto

            if (name.isNotBlank() && email.isNotBlank() && phone.isNotBlank() && photo != null) {
                val resultIntent = Intent().apply {
                    putExtra("name", name)
                    putExtra("email", email)
                    putExtra("phone", phone)
                    putExtra("photo", photo)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields and take a photo", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            contactPhoto = data?.extras?.get("data") as? Bitmap
            imageView.setImageBitmap(contactPhoto)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
