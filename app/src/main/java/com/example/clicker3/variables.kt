package com.example.clicker3

import android.content.Context
import android.widget.TextView

class variables {
    companion object {
        var cnt = 0
        var moneypersec = 0
        var add = 1.2
        var lem_stand_price = 100
        var startUp_price = 1000
        var advAgency_price = 12000
        var twitter_price = 150000
        var google_price = 15000000
        var tourism_price = 30000000
        var timerrate : Long = 1000
        var is_timerrate_too_low = false
        fun reset(){
            cnt = 0
            lem_stand_price = 100
            startUp_price = 1000
            advAgency_price = 12000
            twitter_price = 150000
            google_price = 1500000
            tourism_price = 30000000
            moneypersec = 0
            timerrate = 1000
            is_timerrate_too_low = false
        }
        fun get_timer_rate(){
            timerrate =  (1000 / moneypersec).toLong()
            if(timerrate < 100){
                timerrate = 100
                is_timerrate_too_low = true
            }
        }
        fun buylem(): Boolean {
            if (cnt >= lem_stand_price) {
                cnt -= lem_stand_price
                lem_stand_price = (lem_stand_price * add).toInt()
                moneypersec += 1
                get_timer_rate()
                return true
            }
            return false
        }

        fun buyStartUp(): Boolean {
            if (cnt >= startUp_price) {
                cnt -= startUp_price
                startUp_price = (startUp_price * add).toInt()
                moneypersec += 5
                get_timer_rate()
                return true
            }
            return false
        }

        fun buyAdvAgency(): Boolean {
            if (cnt >= advAgency_price) {
                cnt -= advAgency_price
                advAgency_price = (advAgency_price * add).toInt()
                moneypersec += 27
                get_timer_rate()
                return true
            }
            return false
        }

        fun buyTwitter(): Boolean {
            if (cnt >= twitter_price) {
                cnt -= twitter_price
                twitter_price = (twitter_price * add).toInt();
                moneypersec += 109
                get_timer_rate()
                return true
            }
            return false
        }

        fun buyGoogle(): Boolean {
            if (cnt >= google_price) {
                cnt -= google_price
                google_price = (google_price * add).toInt()
                moneypersec += 600
                get_timer_rate()
                return true
            }
            return false
        }

        fun buyTourism(): Boolean {
            if (cnt >= tourism_price) {
                cnt -= tourism_price
                tourism_price = (tourism_price * add).toInt();
                moneypersec += 8000
                get_timer_rate()
                return true
            }
            return false
        }
    }
}