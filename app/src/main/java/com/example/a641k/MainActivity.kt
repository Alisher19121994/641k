package com.example.a641k

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.nio.file.Files.createFile

class MainActivity : AppCompatActivity() {

    lateinit var editTexts: EditText
    lateinit var save_button: Button
    lateinit var delete_button: Button

    private var isPersistent = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {

        editTexts = findViewById(R.id.et_btn)
        save_button = findViewById(R.id.save_file_btn)
        delete_button = findViewById(R.id.delete_file_btn)


        save_button.setOnClickListener {
            val text = editTexts.text.toString().trim()
            createFile(text)
        }

        delete_button.setOnClickListener {
            val text = editTexts.text.toString().trim()
            deleteThisFile(text)
        }
    }

    private fun createFile(editText: String) {
        val fileName = "$editText.txt"
        try {
            val fileOutputStream: FileOutputStream = if (isPersistent) {
                openFileOutput(fileName, MODE_PRIVATE)
            } else {
                val file = File(cacheDir, fileName)
                FileOutputStream(file)
            }
            fileOutputStream.write(editText.toByteArray(Charset.forName("UTF-8")))
            fileOutputStream.close()

            Toast.makeText(this, "File created", Toast.LENGTH_LONG).show()

        } catch (e: IOException) {
            Toast.makeText(this, "File not created", Toast.LENGTH_LONG).show()

        }
    }

    private fun deleteThisFile(editText: String) {
        val fileName = "$editText.txt"
        val file = File(filesDir, fileName)
        if (file.exists()) {
            file.delete()
            Toast.makeText(this, "deleted", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Not deleted", Toast.LENGTH_LONG).show()
        }

    }
}