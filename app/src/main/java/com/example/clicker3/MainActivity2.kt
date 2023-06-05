package com.example.clicker3

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

class MainActivity2 : AppCompatActivity() {
    private val timer = Timer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val back_Button = findViewById<TextView>(R.id.back_link)
        val lemStand = findViewById<TextView>(R.id.buylem)
        val startUp = findViewById<TextView>(R.id.buyStartUp)
        val advAgency = findViewById<TextView>(R.id.buyAdvAgency)
        val twitter = findViewById<TextView>(R.id.buyTwitter)
        val google = findViewById<TextView>(R.id.buyGoogle)
        val tourism = findViewById<TextView>(R.id.buyTourism)
        //lemStand.text = variables.lem_stand.toString()
        back_Button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            timer.cancel()
        }
        lemStand.setOnClickListener {
            if (variables.buylem()) {
                lemStand.text = variables.lem_stand.toString()
                saveData()
            }
        }
        startUp.setOnClickListener {
            if (variables.buyStartUp()) {
                startUp.text = variables.startUp.toString()
                saveData()
            }
        }
        advAgency.setOnClickListener {
            if (variables.buyAdvAgency()) {
                advAgency.text = variables.advAgency.toString()
                saveData()
            }
        }
        twitter.setOnClickListener {
            if (variables.buyTwitter()) {
                twitter.text = variables.twitter.toString()
                saveData()
            }
        }
        google.setOnClickListener {
            if (variables.buyGoogle()) {
                google.text = variables.google.toString()
                saveData()
            }
        }
        tourism.setOnClickListener {
            if (variables.buyTourism()) {
                tourism.text = variables.tourism.toString()
                saveData()
            }
        }
        timer.scheduleAtFixedRate(TimeTask(), 0, 1000)
    }

    private inner class TimeTask : TimerTask() {
        override fun run() {
            variables.cnt += variables.moneypersec;
            saveData();
        }
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        val editor = sharedPreferences.edit();
        editor.apply {
            putFloat("COUNT_KEY", variables.cnt)
            putInt("LemStand_KEY", variables.lem_stand)
            putInt("StartUp_KEY", variables.startUp);
            putInt("AdvAgency_KEY", variables.advAgency);
            putInt("Twitter_KEY", variables.twitter);
            putInt("Google_KEY", variables.google);
            putInt("Tourism_KEY", variables.tourism);
            putFloat("MoneyPerSec_KEY", variables.moneypersec)
        }.apply()
    }
}