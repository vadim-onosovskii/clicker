package com.example.clicker3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.Timer
import java.util.TimerTask

class OtherBusiness : AppCompatActivity() {
    private val timer = Timer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.other_business)
        val BackLink = findViewById<TextView>(R.id.back_link1)
        val bar = findViewById<TextView>(R.id.buy_bar)
        val club = findViewById<TextView>(R.id.buy_club)
        val counterfuit = findViewById<TextView>(R.id.buy_counterfuit)
        val casino = findViewById<TextView>(R.id.buy_casino)
        val empire = findViewById<TextView>(R.id.buy_empire)
        bar.text = variables.underground_bar_price.toString()
        club.text = variables.night_club_price.toString()
        counterfuit.text = variables.counterfuit_goods_price.toString()
        casino.text = variables.casino_price.toString()
        empire.text = variables.boardwalk_empire_price.toString()
        BackLink.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            timer.cancel()
        }
        bar.setOnClickListener {
            if (variables.buybar()) {
                bar.text = variables.underground_bar_price.toString()
                saveData()
            }
        }
        club.setOnClickListener {
            if (variables.buyclub()) {
                club.text = variables.night_club_price.toString()
                saveData()
            }
        }
        counterfuit.setOnClickListener {
            if (variables.buycounterfuit()) {
                counterfuit.text = variables.counterfuit_goods_price.toString()
                saveData()
            }
        }
        casino.setOnClickListener {
            if (variables.buycasino()) {
                casino.text = variables.casino_price.toString()
                saveData()
            }
        }
        empire.setOnClickListener {
            if (variables.buyempire()) {
                empire.text = variables.boardwalk_empire_price.toString()
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