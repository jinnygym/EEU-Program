package com.example.realeeu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mNext = findViewById<Button>(R.id.button)
        mNext.setOnClickListener{
            startActivity(Intent(this@MainActivity, NewAcitivity::class.java))
    }
} }
