package com.example.clicker3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

open class MainActivity : AppCompatActivity() {
    private val timer = Timer()
    private val invest_timer = Timer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ButtonClick = findViewById<Button>(R.id.button_click)
        val Menu = findViewById<TextView>(R.id.menu)
        val IllegalBusiness = findViewById<TextView>(R.id.illegal_business)
        val Investments = findViewById<TextView>(R.id.investments)
        val CurrentAmountText = findViewById<TextView>(R.id.current_amount)
        val Reset = findViewById<TextView>(R.id.reset)
        val ButtonGet = findViewById<Button>(R.id.button_get)
        val AmountText = findViewById<EditText>(R.id.amount)
        val ClickIncome = findViewById<TextView>(R.id.incomeclick)
        loadData()
        saveData()

        ButtonClick.setOnClickListener {
            variables.cnt += variables.moneyperclick
            saveData()
            CurrentAmountText.text = variables.cnt.toString()
        }
        Menu.setOnClickListener {
            val intent = Intent(this, LegalBusiness::class.java)
            startActivity(intent)
            timer.cancel()
            invest_timer.cancel()
        }
        Investments.setOnClickListener {
            val intent = Intent(this, Investment::class.java)
            startActivity(intent)
            timer.cancel()
            invest_timer.cancel()
        }
        IllegalBusiness.setOnClickListener {
            val intent = Intent(this, OtherBusiness::class.java)
            startActivity(intent)
            timer.cancel()
            invest_timer.cancel()
        }
        ButtonGet.setOnClickListener {
            if (AmountText.text.isDigitsOnly()) {
                variables.cnt = AmountText.text.toString().toLong()
                saveData()
            }
        }
        Reset.setOnClickListener {
            variables.reset()
            saveData()
        }
        timer.scheduleAtFixedRate(TimeTask(), 0, variables.timerrate)
        invest_timer.scheduleAtFixedRate(InvestTimer(), 0, 1000)
    }
    private inner class InvestTimer : TimerTask() {
        override fun run() {
            if(variables.bitcoin_timer>0) {
                --variables.bitcoin_timer
            }
            if(variables.shares_timer>0) {
                --variables.shares_timer
            }
            if(variables.gold_timer>0) {
                --variables.shares_timer
            }
            if(variables.NFT_timer>0) {
                --variables.NFT_timer
            }
        }
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
            MainScope().launch {
                val TextView1 = findViewById<TextView>(R.id.current_amount)
                val IncomeSec = findViewById<TextView>(R.id.incomesec)
                val ClickIncome = findViewById<TextView>(R.id.incomeclick)
                TextView1.text = variables.cnt.toString()
                IncomeSec.text = variables.moneypersec.toString()
                ClickIncome.text = variables.moneyperclick.toString()
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


    private fun loadData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        variables.cnt = sharedPreferences.getLong("Count_KEY", 0L)
        variables.lem_stand_price = sharedPreferences.getLong("LemStand_KEY", 100L)
        variables.startUp_price = sharedPreferences.getLong("StartUp_KEY", 1000L)
        variables.advAgency_price = sharedPreferences.getLong("AdvAgency_KEY", 12000L)
        variables.twitter_price = sharedPreferences.getLong("Twitter_KEY", 150000L)
        variables.google_price = sharedPreferences.getLong("Google_KEY", 1500000L)
        variables.tourism_price = sharedPreferences.getLong("Tourism_KEY", 30000000L)
        variables.underground_bar_price = sharedPreferences.getLong("Bar_KEY", 1000L)
        variables.night_club_price = sharedPreferences.getLong("Club_KEY", 10000L)
        variables.counterfuit_goods_price = sharedPreferences.getLong("Counterfuit_KEY", 100000L)
        variables.casino_price = sharedPreferences.getLong("Casino_KEY", 1000000L)
        variables.boardwalk_empire_price = sharedPreferences.getLong("Empire_KEY", 100000000L)
        variables.timerrate = sharedPreferences.getLong("Timerrate_KEY", 10000L)
        variables.is_timerrate_too_low = sharedPreferences.getBoolean("Is_Timerrate_Too_Low_KEY", false)
        variables.moneypersec = sharedPreferences.getLong("MoneyPerSec_KEY", 0L)
        variables.moneyperclick = sharedPreferences.getLong("MoneyPerClick_KEY", 1L)
    }
}

