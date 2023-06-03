package com.example.clicker3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val Button1 = findViewById<Button>(R.id.button)
        val TextView1 = findViewById<TextView>(R.id.textView2)
        val Menu = findViewById<TextView>(R.id.menu)
        TextView1.text = variables.cnt.toString()
        Button1.setOnClickListener{
            variables.cnt++
            TextView1.text = variables.cnt.toString()
        }
        Menu.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}