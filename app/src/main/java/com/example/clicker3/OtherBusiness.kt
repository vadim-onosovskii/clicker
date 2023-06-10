package com.example.clicker3

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

class OtherBusiness : AppCompatActivity() {
    private val timer = Timer()
    private val invest_timer = Timer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.other_business)
        val BackLink = findViewById<TextView>(R.id.back_link1)
        val bar = findViewById<TextView>(R.id.buy_bar)
        val club = findViewById<TextView>(R.id.buy_club)
        val counterfuit = findViewById<TextView>(R.id.buy_counterfuit)
        val casino = findViewById<TextView>(R.id.buy_casino)
        val empire = findViewById<TextView>(R.id.buy_empire)
        val LoseButton = findViewById<Button>(R.id.lose_button)
        val myDialog = Dialog(this)
        bar.text = variables.underground_bar_price.toString()
        club.text = variables.night_club_price.toString()
        counterfuit.text = variables.counterfuit_goods_price.toString()
        casino.text = variables.casino_price.toString()
        empire.text = variables.boardwalk_empire_price.toString()
        LoseButton.setOnClickListener{
            myDialog.setContentView(R.layout.popup_fail)
            myDialog.show()
            //myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        BackLink.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            timer.cancel()
            invest_timer.cancel()
        }
        bar.setOnClickListener {
            val res : variables.Companion.result = variables.buybar()
            if (res == variables.Companion.result.SUCCESS) {
                bar.text = variables.underground_bar_price.toString()
                saveData()
            }
            else if (res == variables.Companion.result.UNLUCKY){
                myDialog.setContentView(R.layout.popup_fail)
                myDialog.show()
            }
        }
        club.setOnClickListener {
            val res : variables.Companion.result = variables.buyclub()
            if (res == variables.Companion.result.SUCCESS) {
                club.text = variables.night_club_price.toString()
                saveData()
            }
            else if (res == variables.Companion.result.UNLUCKY){
                myDialog.setContentView(R.layout.popup_fail)
                myDialog.show()
            }
        }
        counterfuit.setOnClickListener {
            val res : variables.Companion.result = variables.buycounterfuit()
            if (res == variables.Companion.result.SUCCESS) {
                counterfuit.text = variables.counterfuit_goods_price.toString()
                saveData()
            }
            else if (res == variables.Companion.result.UNLUCKY){
                myDialog.setContentView(R.layout.popup_fail)
                myDialog.show()
            }
        }
        casino.setOnClickListener {
            val res : variables.Companion.result = variables.buycasino()
            if (res == variables.Companion.result.SUCCESS) {
                casino.text = variables.casino_price.toString()
                saveData()
            }
            else if (res == variables.Companion.result.UNLUCKY){
                myDialog.setContentView(R.layout.popup_fail)
                myDialog.show()
            }
        }
        empire.setOnClickListener {
            val res : variables.Companion.result = variables.buyempire()
            if (res == variables.Companion.result.SUCCESS) {
                empire.text = variables.boardwalk_empire_price.toString()
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
    private inner class InvestTimer : TimerTask() {
        override fun run() {
            if(variables.bitcoin_timer>0) {
                --variables.bitcoin_timer
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