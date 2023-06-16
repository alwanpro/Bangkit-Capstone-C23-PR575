package com.example.nutriku.screen.scan

import com.example.myjetpackapplication.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.Manifest
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.NavUtils
import androidx.core.content.ContextCompat
import com.example.myjetpackapplication.databinding.ActivitySelectBinding
import com.example.nutriku.MainActivity
import com.example.nutriku.Utils.rotateBitmapBase
import com.example.nutriku.Utils.uriToFile
import java.io.File
import java.io.FileOutputStream


class SelectActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySelectBinding
    private var getMyFile: File? = null


    companion object {
        const val CAMERA_X_RESULT = 200
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10

        const val TAG = "TFLite - ODT"
        const val REQUEST_IMAGE_CAPTURE: Int = 1
        private const val MAX_FONT_SIZE = 96F
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

//        supportActionBar?.title = "Pindai Makanan"
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FF10383A")))
        supportActionBar?.hide()

        binding.camera.setOnClickListener {
            startCameraX()
        }
        binding.gallery.setOnClickListener{
            startGallery()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun startCameraX() {
        val intent = Intent(this, ScanActivity::class.java)
        launcherIntentCameraX.launch(intent)

    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean
            getMyFile = myFile
            val result = rotateBitmapBase(
                BitmapFactory.decodeFile(myFile.path),
                isBackCamera
            )

            // Save the Bitmap to a file
            val file = File(cacheDir, "image.jpg")
            val fileOutputStream = FileOutputStream(file)
            result.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
            fileOutputStream.close()

            val intent = Intent(this, ScanResultActivity::class.java)
            intent.putExtra("imagePath", file.absolutePath)
            startActivity(intent)

        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedImg, this@SelectActivity)
            getMyFile = myFile

            val bitmap = uriToBitmap(contentResolver, selectedImg)
            if (bitmap != null) {
                // Save the Bitmap to a file
                val file = File(cacheDir, "image.jpg")
                val fileOutputStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
                fileOutputStream.close()

                val intent = Intent(this, ScanResultActivity::class.java)
                intent.putExtra("imagePath2", file.absolutePath)
                startActivity(intent)
            }
        }
    }

    private fun uriToBitmap(contentResolver: ContentResolver, uri: Uri): Bitmap? {
        return try {
            // Open an input stream from the URI
            val inputStream = contentResolver.openInputStream(uri)

            // Decode the input stream into a Bitmap
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}