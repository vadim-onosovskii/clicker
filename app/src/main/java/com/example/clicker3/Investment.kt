package com.example.clicker3

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

class Investment : AppCompatActivity() {
    private val timer = Timer()
    private val invest_timer = Timer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.investment)
        val BackLink = findViewById<TextView>(R.id.back_link2)
        val bitcoin = findViewById<TextView>(R.id.buybitcoin)
        val bitcointime = findViewById<TextView>(R.id.btctime)
        val myDialog = Dialog(this)
        if(variables.bitcoin_timer != 0) bitcointime.text = variables.bitcoin_timer.toString()
        else bitcointime.text = ""
        BackLink.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            timer.cancel()
            invest_timer.cancel()
        }
        bitcoin.setOnClickListener {
            val res : variables.Companion.result = variables.buybitcoin()
            if (res == variables.Companion.result.SUCCESS) {
                variables.bitcoin_timer = 60
                saveData()
            }
            else if (res == variables.Companion.result.UNLUCKY){
                myDialog.setContentView(R.layout.popup_fail)
                myDialog.show()
            }
        }
        timer.scheduleAtFixedRate(TimeTask(), 0, variables.timerrate)
        invest_timer.scheduleAtFixedRate(InvestTimer(), 0, 1000)
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
    private inner class InvestTimer : TimerTask() {
        override fun run() {
            if(variables.bitcoin_timer>0) {
                --variables.bitcoin_timer
                MainScope().launch {
                    val bitcointime = findViewById<TextView>(R.id.btctime)
                    if(variables.bitcoin_timer != 0) bitcointime.text = variables.bitcoin_timer.toString()
                    else bitcointime.text = ""
                }
            }
        }
    }
    private fun saveData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putLong("Count_KEY", variables.cnt)
            putLong("LemStand_KEY", variables.lem_stand_price)
            putLong("StartUp_KEY", variables.startUp_price)
            putLong("AdvAgency_KEY", variables.advAgency_price)
            putLong("Twitter_KEY", variables.twitter_price)
            putLong("Google_KEY", variables.google_price)
            putLong("Tourism_KEY", variables.tourism_price)
            putLong("Bar_KEY", variables.underground_bar_price)
            putLong("Club_KEY", variables.night_club_price)
            putLong("Counterfuit_KEY", variables.counterfuit_goods_price)
            putLong("Casino_KEY", variables.casino_price)
            putLong("Empire_KEY", variables.boardwalk_empire_price)
            putLong("Timerrate_KEY", variables.timerrate)
            putBoolean("Is_Timerrate_Too_Low_KEY", variables.is_timerrate_too_low)
            putLong("MoneyPerSec_KEY", variables.moneypersec)
            putLong("MoneyPerClick_KEY", variables.moneyperclick)
        }.apply()
    }
}