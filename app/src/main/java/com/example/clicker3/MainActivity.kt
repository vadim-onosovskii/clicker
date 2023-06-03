package com.example.clicker3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {
    private val timer = Timer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val Button1 = findViewById<Button>(R.id.button)
        val Menu = findViewById<TextView>(R.id.menu)
        val TextView1 = findViewById<TextView>(R.id.textView2)
        loadData()
        saveData()
        Button1.setOnClickListener {
            variables.cnt++
            saveData()
            TextView1.text = variables.cnt.toString()
        }
        Menu.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
        timer.scheduleAtFixedRate(TimeTask(), 0, 500);
    }
    private inner class TimeTask: TimerTask() {
        override fun run() {
            ++variables.cnt;
            saveData();
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