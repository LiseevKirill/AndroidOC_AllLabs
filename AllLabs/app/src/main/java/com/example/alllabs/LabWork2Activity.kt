package com.example.alllabs

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class LabWork2Activity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private var photoUri: Uri? = null
    private lateinit var currentPhotoPath: String

    private val CAMERA_PERMISSION_CODE = 1001

    private val takePictureLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            imageView.setImageURI(photoUri)
        } else {
            Toast.makeText(this, "Не вдалося зробити фото", Toast.LENGTH_SHORT).show()
        }
    }

    private val sendEmailLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab_work2)

        imageView = findViewById(R.id.imageViewSelfie)
        val takeSelfieBtn = findViewById<Button>(R.id.btnTakeSelfie)
        val sendSelfieBtn = findViewById<Button>(R.id.btnSendSelfie)

        takeSelfieBtn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CAMERA),
                    CAMERA_PERMISSION_CODE
                )
            } else {
                dispatchTakePicture()
            }
        }

        sendSelfieBtn.setOnClickListener {
            photoUri?.let {
                val emailIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "image/*"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("hodovychenko@op.edu.ua"))
                    putExtra(Intent.EXTRA_SUBJECT, "ANDROID Прізвище Ім’я")
                    putExtra(Intent.EXTRA_TEXT, "Проєкт тут: https://github.com/yourusername/yourproject")
                    putExtra(Intent.EXTRA_STREAM, it)
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
                sendEmailLauncher.launch(Intent.createChooser(emailIntent, "Відіслати селфі через..."))
            } ?: Toast.makeText(this, "Зробіть селфі перед відправкою", Toast.LENGTH_SHORT).show()
        }
    }

    private fun dispatchTakePicture() {
        val photoFile: File? = try {
            createImageFile()
        } catch (ex: IOException) {
            Toast.makeText(this, "Помилка при створенні файлу для фото", Toast.LENGTH_SHORT).show()
            null
        }

        photoFile?.also {
            photoUri = FileProvider.getUriForFile(
                this,
                "${applicationContext.packageName}.fileprovider",
                it
            )
            takePictureLauncher.launch(photoUri!!)  // Тут вже photoUri не null
        }
    }


    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File = cacheDir
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePicture()
            } else {
                Toast.makeText(this, "Дозвіл на камеру відхилено", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
