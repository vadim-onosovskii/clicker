package com.example.clicker3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.Timer
import java.util.TimerTask

class MainActivity2 : AppCompatActivity() {
    private val timer = Timer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val BackLink = findViewById<TextView>(R.id.back_link)
        val lemStand = findViewById<TextView>(R.id.buylem)
        val startUp = findViewById<TextView>(R.id.buyStartUp)
        val advAgency = findViewById<TextView>(R.id.buyAdvAgency)
        val twitter = findViewById<TextView>(R.id.buyTwitter)
        val google = findViewById<TextView>(R.id.buyGoogle)
        val tourism = findViewById<TextView>(R.id.buyTourism)
        lemStand.text = variables.lem_stand_price.toString()
        startUp.text = variables.startUp.toString()
        advAgency.text = variables.advAgency.toString()
        twitter.text = variables.twitter.toString()
        google.text = variables.google.toString()
        tourism.text = variables.tourism.toString()
        BackLink.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            timer.cancel()
        }
        lemStand.setOnClickListener {
            if (variables.buylem()) {
                lemStand.text = variables.lem_stand_price.toString()
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
        timer.scheduleAtFixedRate(TimeTask(), 0, variables.timerrate)
    }

    private inner class TimeTask : TimerTask() {
        override fun run() {
            if(variables.is_timerrate_too_low) {
                variables.cnt += variables.moneypersec / 10
            }
            else{
                if(variables.moneypersec > 0) variables.cnt++
            }
            saveData();
        }
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        val editor = sharedPreferences.edit();
        editor.apply {
            putInt("COUNT_KEY", variables.cnt)
            putInt("LemStand_KEY", variables.lem_stand_price)
            putInt("StartUp_KEY", variables.startUp);
            putInt("AdvAgency_KEY", variables.advAgency);
            putInt("Twitter_KEY", variables.twitter);
            putInt("Google_KEY", variables.google);
            putInt("Tourism_KEY", variables.tourism);
            putInt("MoneyPerSec_KEY", variables.moneypersec)
        }.apply()
    }
}