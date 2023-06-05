package com.example.clicker3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

open class MainActivity : AppCompatActivity() {
    private val timer = Timer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val Button1 = findViewById<Button>(R.id.button)
        val Menu = findViewById<TextView>(R.id.menu)
        val TextView1 = findViewById<TextView>(R.id.textView2)
        val Reset = findViewById<TextView>(R.id.reset);
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
            timer.cancel()
        }
        Reset.setOnClickListener {
            variables.cnt = 1000
            variables.lem_stand_price = 100
            variables.startUp = 1000
            variables.advAgency = 12000
            variables.twitter = 150000
            variables.google = 1500000
            variables.tourism = 30000000
            variables.moneypersec = 0
            saveData()
        }
        timer.scheduleAtFixedRate(TimeTask(), 0, 1000)
    }

    private inner class TimeTask : TimerTask() {
        override fun run() {
            variables.cnt += variables.moneypersec;
            saveData();
            MainScope().launch {
                val TextView1 = findViewById<TextView>(R.id.textView2)
                val IncomeSec = findViewById<TextView>(R.id.incomesec)
                TextView1.text = variables.cnt.toString()
                IncomeSec.text = variables.moneypersec.toString();
            }
        }
    }
    private fun saveData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        val editor = sharedPreferences.edit();
        editor.apply {
            putInt("COUNT_KEY", variables.cnt)
            putInt("LemStand_KEY", variables.lem_stand_price)
            putInt("StartUp_KEY", variables.startUp)
            putInt("AdvAgency_KEY", variables.advAgency)
            putInt("Twitter_KEY", variables.twitter)
            putInt("Google_KEY", variables.google)
            putInt("Tourism_KEY", variables.tourism)
            putInt("MoneyPerSec_KEY", variables.moneypersec)
        }.apply()
    }


    private fun loadData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedCount = sharedPreferences.getInt("COUNT_KEY", 0)
        val savedLemStand = sharedPreferences.getInt("LemStand_KEY", 0)
        val savedStartUp = sharedPreferences.getInt("StartUp_KEY", 0)
        val savedAdvAgency = sharedPreferences.getInt("AdvAgency_KEY", 0)
        val savedTwitter = sharedPreferences.getInt("Twitter_KEY", 0)
        val savedGoogle = sharedPreferences.getInt("Google_KEY", 0)
        val savedTourism = sharedPreferences.getInt("Tourism_KEY", 0)
        val savedMoneyPerSec = sharedPreferences.getInt("MoneyPerSec_KEY", 0)
        variables.cnt = savedCount;
        variables.lem_stand_price = savedLemStand
        variables.startUp = savedStartUp;
        variables.advAgency = savedAdvAgency;
        variables.twitter = savedTwitter;
        variables.google = savedGoogle;
        variables.tourism = savedTourism;
        variables.moneypersec = savedMoneyPerSec
    }
}

