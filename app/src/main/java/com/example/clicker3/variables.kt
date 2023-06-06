package com.example.clicker3

import android.content.Context
import android.widget.TextView

class variables {
    companion object {
        var cnt = 0
        var moneypersec = 0
        var moneyperclick = 1
        var add = 1.2
        var lem_stand_price = 100
        var startUp_price = 1000
        var advAgency_price = 12000
        var twitter_price = 150000
        var google_price = 15000000
        var tourism_price = 30000000
        var timerrate : Long = 1000
        var is_timerrate_too_low = false

        var underground_bar_price = 1000
        var night_club_price = 10000
        var counterfuit_goods_price = 100000
        var casino_price = 1000000
        var boardwalk_empire_price = 100000000

        fun reset(){
            cnt = 0
            moneyperclick = 1
            lem_stand_price = 100
            startUp_price = 1000
            advAgency_price = 12000
            twitter_price = 150000
            google_price = 1500000
            tourism_price = 30000000
            moneypersec = 0
            timerrate = 1000
            is_timerrate_too_low = false

            underground_bar_price = 1000
            night_club_price = 10000
            counterfuit_goods_price = 100000
            casino_price = 1000000
            boardwalk_empire_price = 100000000

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

        fun buybar(): Boolean {
            if (cnt >= underground_bar_price) {
                cnt -= underground_bar_price
                underground_bar_price = (underground_bar_price * add).toInt();
                moneypersec += 5
                moneyperclick += 15
                get_timer_rate()
                return true
            }
            return false
        }

        fun buyclub(): Boolean {
            if (cnt >= night_club_price) {
                cnt -= night_club_price
                night_club_price = (night_club_price * add).toInt();
                moneypersec += 27
                moneyperclick += 81
                get_timer_rate()
                return true
            }
            return false
        }

        fun buycounterfuit(): Boolean {
            if (cnt >= counterfuit_goods_price) {
                cnt -= counterfuit_goods_price
                counterfuit_goods_price = (counterfuit_goods_price * add).toInt();
                moneypersec += 109
                moneyperclick += 327
                get_timer_rate()
                return true
            }
            return false
        }

        fun buycasino(): Boolean {
            if (cnt >= casino_price) {
                cnt -= casino_price
                casino_price = (casino_price * add).toInt();
                moneypersec += 600
                moneyperclick += 1800
                get_timer_rate()
                return true
            }
            return false
        }

        fun buyempire(): Boolean {
            if (cnt >= boardwalk_empire_price) {
                cnt -= boardwalk_empire_price
                boardwalk_empire_price = (boardwalk_empire_price * add).toInt();
                moneypersec += 8000
                moneyperclick += 24000
                get_timer_rate()
                return true
            }
            return false
        }
    }
}