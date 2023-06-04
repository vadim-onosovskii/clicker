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
        val lemStand = findViewById<TextView>(R.id.buy_lem)
        lemStand.text = variables.lem_stand.toString()
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
            putInt("COUNT_KEY", variables.cnt);
            putInt("LemStand_KEY", variables.lem_stand);
            putInt("MoneyPerSec_KEY", variables.moneypersec)
        }.apply()
    }
}