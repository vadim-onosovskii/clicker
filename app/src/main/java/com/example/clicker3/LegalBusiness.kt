package com.example.clicker3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.Timer
import java.util.TimerTask

class LegalBusiness : AppCompatActivity() {
    private val timer = Timer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.legal_business)
        val BackLink = findViewById<TextView>(R.id.back_link)
        val lemStand = findViewById<TextView>(R.id.buylem)
        val startUp = findViewById<TextView>(R.id.buyStartUp)
        val advAgency = findViewById<TextView>(R.id.buyAdvAgency)
        val twitter = findViewById<TextView>(R.id.buyTwitter)
        val google = findViewById<TextView>(R.id.buyGoogle)
        val tourism = findViewById<TextView>(R.id.buyTourism)
        lemStand.text = variables.lem_stand_price.toString()
        startUp.text = variables.startUp_price.toString()
        advAgency.text = variables.advAgency_price.toString()
        twitter.text = variables.twitter_price.toString()
        google.text = variables.google_price.toString()
        tourism.text = variables.tourism_price.toString()
        BackLink.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            timer.cancel()
        }
        lemStand.setOnClickListener {
            if (variables.buylem() == variables.Companion.result.SUCCESS) {
                lemStand.text = variables.lem_stand_price.toString()
                saveData()
            }
        }
        startUp.setOnClickListener {
            if (variables.buyStartUp() == variables.Companion.result.SUCCESS) {
                startUp.text = variables.startUp_price.toString()
                saveData()
            }
        }
        advAgency.setOnClickListener {
            if (variables.buyAdvAgency() == variables.Companion.result.SUCCESS) {
                advAgency.text = variables.advAgency_price.toString()
                saveData()
            }
        }
        twitter.setOnClickListener {
            if (variables.buyTwitter() == variables.Companion.result.SUCCESS) {
                twitter.text = variables.twitter_price.toString()
                saveData()
            }
        }
        google.setOnClickListener {
            if (variables.buyGoogle() == variables.Companion.result.SUCCESS) {
                google.text = variables.google_price.toString()
                saveData()
            }
        }
        tourism.setOnClickListener {
            if (variables.buyTourism() == variables.Companion.result.SUCCESS) {
                tourism.text = variables.tourism_price.toString()
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
            saveData()
        }
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putInt("Count_KEY", variables.cnt)
            putInt("LemStand_KEY", variables.lem_stand_price)
            putInt("StartUp_KEY", variables.startUp_price)
            putInt("AdvAgency_KEY", variables.advAgency_price)
            putInt("Twitter_KEY", variables.twitter_price)
            putInt("Google_KEY", variables.google_price)
            putInt("Tourism_KEY", variables.tourism_price)
            putInt("Bar_KEY", variables.underground_bar_price)
            putInt("Club_KEY", variables.night_club_price)
            putInt("Counterfuit_KEY", variables.counterfuit_goods_price)
            putInt("Casino_KEY", variables.casino_price)
            putInt("Empire_KEY", variables.boardwalk_empire_price)
            putLong("Timerrate_KEY", variables.timerrate)
            putBoolean("Is_Timerrate_Too_Low_KEY", variables.is_timerrate_too_low)
            putInt("MoneyPerSec_KEY", variables.moneypersec)
            putInt("MoneyPerClick_KEY", variables.moneyperclick)
        }.apply()
    }
}