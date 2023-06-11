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
        val shares = findViewById<TextView>(R.id.buyshares)
        val sharestime = findViewById<TextView>(R.id.sharestime)
        val gold = findViewById<TextView>(R.id.buygold)
        val goldtime = findViewById<TextView>(R.id.goldtime)
        val NFT = findViewById<TextView>(R.id.buyNFT)
        val NFTtime = findViewById<TextView>(R.id.NFTtime)
        val myDialog = Dialog(this)
        if(variables.bitcoin_timer != 0L) bitcointime.text = variables.bitcoin_timer.toString()
        else bitcointime.text = ""
        if(variables.shares_timer != 0L) sharestime.text = variables.shares_timer.toString()
        else sharestime.text = ""
        if(variables.gold_timer != 0L) goldtime.text = variables.gold_timer.toString()
        else goldtime.text = ""
        if(variables.NFT_timer != 0L) NFTtime.text = variables.NFT_timer.toString()
        else NFTtime.text = ""
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
        shares.setOnClickListener {
            val res : variables.Companion.result = variables.buyshares()
            if (res == variables.Companion.result.SUCCESS) {
                variables.shares_timer = 60
                saveData()
            }
            else if (res == variables.Companion.result.UNLUCKY){
                myDialog.setContentView(R.layout.popup_fail)
                myDialog.show()
            }
        }
        gold.setOnClickListener {
            val res : variables.Companion.result = variables.buygold()
            if (res == variables.Companion.result.SUCCESS) {
                variables.gold_timer = 60
                saveData()
            }
            else if (res == variables.Companion.result.UNLUCKY){
                myDialog.setContentView(R.layout.popup_fail)
                myDialog.show()
            }
        }
        NFT.setOnClickListener {
            val res : variables.Companion.result = variables.buynft()
            if (res == variables.Companion.result.SUCCESS) {
                variables.NFT_timer = 60
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
            MainScope().launch {
                val bitcointime = findViewById<TextView>(R.id.btctime)
                if(variables.bitcoin_timer != 0L) bitcointime.text = variables.bitcoin_timer.toString()
                else bitcointime.text = ""
                val sharestime = findViewById<TextView>(R.id.sharestime)
                if(variables.shares_timer != 0L) sharestime.text = variables.shares_timer.toString()
                else sharestime.text = ""
                val goldtime = findViewById<TextView>(R.id.goldtime)
                if(variables.gold_timer != 0L) goldtime.text = variables.gold_timer.toString()
                else goldtime.text = ""
                val NFTtime = findViewById<TextView>(R.id.NFTtime)
                if(variables.NFT_timer != 0L) NFTtime.text = variables.NFT_timer.toString()
                else NFTtime.text = ""
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