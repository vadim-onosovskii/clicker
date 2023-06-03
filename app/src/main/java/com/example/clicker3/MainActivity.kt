package com.example.clicker3

import android.content.Context
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
        loadData()
        saveData()
        TextView1.text = variables.cnt.toString()
        Button1.setOnClickListener{
            variables.cnt++
            saveData()
            TextView1.text = variables.cnt.toString()
        }
        Menu.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
    private fun saveData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        val editor = sharedPreferences.edit();
        editor.apply {
            putInt("COUNT_KEY", variables.cnt);
        }.apply()
    }
    private fun loadData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        val savedCount = sharedPreferences.getInt("COUNT_KEY", 0);
        variables.cnt = savedCount;
    }
}