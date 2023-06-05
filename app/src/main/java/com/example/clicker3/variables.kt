package com.example.clicker3

import android.content.Context
import android.widget.TextView

class variables {
    companion object {
        var cnt = 0
        var moneypersec = 0
        var add = 1.2
        var lem_stand_price = 100
        var startUp = 1000
        var advAgency = 12000
        var twitter = 150000
        var google = 15000000
        var tourism = 30000000
        var timerrate : Long = 1000
        var is_timerrate_too_low = false
        fun reset(){
            cnt = 0
            lem_stand_price = 100
            startUp = 1000
            advAgency = 12000
            twitter = 150000
            google = 1500000
            tourism = 30000000
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
                lem_stand_price = (lem_stand_price * add).toInt();
                moneypersec += 1
                get_timer_rate()
                return true;
            }
            return false;
        }

        fun buyStartUp(): Boolean {
            if (cnt >= startUp) {
                cnt -= startUp
                startUp = (startUp * add).toInt();
                moneypersec += 5
                get_timer_rate()
                return true;
            }
            return false;
        }

        fun buyAdvAgency(): Boolean {
            if (cnt >= advAgency) {
                cnt -= advAgency
                advAgency = (advAgency * add).toInt();
                moneypersec += 27
                get_timer_rate()
                return true;
            }
            return false;
        }

        fun buyTwitter(): Boolean {
            if (cnt >= twitter) {
                cnt -= twitter
                twitter = (twitter * add).toInt();
                moneypersec += 109
                get_timer_rate()
                return true;
            }
            return false;
        }

        fun buyGoogle(): Boolean {
            if (cnt >= google) {
                cnt -= google
                google = (google * add).toInt();
                moneypersec += 600
                get_timer_rate()
                return true;
            }
            return false;
        }

        fun buyTourism(): Boolean {
            if (cnt >= tourism) {
                cnt -= tourism
                tourism = (tourism * add).toInt();
                moneypersec += 8000
                get_timer_rate()
                return true;
            }
            return false;
        }
    }
}