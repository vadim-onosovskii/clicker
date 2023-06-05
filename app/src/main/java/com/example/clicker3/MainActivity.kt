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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ButtonClick = findViewById<Button>(R.id.button_click)
        val Menu = findViewById<TextView>(R.id.menu)
        val CurrentAmountText = findViewById<TextView>(R.id.current_amount)
        val Reset = findViewById<TextView>(R.id.reset)
        val ButtonGet = findViewById<Button>(R.id.button_get)
        val AmountText = findViewById<EditText>(R.id.amount)
        loadData()
        saveData()

        ButtonClick.setOnClickListener {
            variables.cnt++
            saveData()
            CurrentAmountText.text = variables.cnt.toString()
        }
        Menu.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            timer.cancel()
        }
        ButtonGet.setOnClickListener {
            if (AmountText.text.isDigitsOnly()) {
                variables.cnt = AmountText.text.toString().toInt()
                saveData()
            }
        }
        Reset.setOnClickListener {
            variables.reset()
            saveData()
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
            MainScope().launch {
                val TextView1 = findViewById<TextView>(R.id.current_amount)
                val IncomeSec = findViewById<TextView>(R.id.incomesec)
                TextView1.text = variables.cnt.toString()
                IncomeSec.text = variables.moneypersec.toString()
            }
        }
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
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
        variables.cnt = sharedPreferences.getInt("COUNT_KEY", 0)
        variables.lem_stand_price = sharedPreferences.getInt("LemStand_KEY", 0)
        variables.startUp = sharedPreferences.getInt("StartUp_KEY", 0)
        variables.advAgency = sharedPreferences.getInt("AdvAgency_KEY", 0)
        variables.twitter = sharedPreferences.getInt("Twitter_KEY", 0)
        variables.google = sharedPreferences.getInt("Google_KEY", 0)
        variables.tourism = sharedPreferences.getInt("Tourism_KEY", 0)
        variables.moneypersec = sharedPreferences.getInt("MoneyPerSec_KEY", 0)
    }
}

