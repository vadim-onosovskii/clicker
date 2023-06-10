package com.example.clicker3

import android.content.Context
import android.widget.TextView

class variables {
    companion object {
        enum class result {
            SUCCESS, UNLUCKY, NOT_ENOUGH
        }

        var cnt = 0L
        var moneypersec = 0.toLong()
        var moneyperclick = 1.toLong()
        var add = 1.2
        var lem_stand_price = 100.toLong()
        var startUp_price = 1000.toLong()
        var advAgency_price = 12000.toLong()
        var twitter_price = 150000.toLong()
        var google_price = 15000000.toLong()
        var tourism_price = 30000000.toLong()
        var timerrate: Long = 1000
        var is_timerrate_too_low = false

        var underground_bar_price = 1000.toLong()
        var night_club_price = 10000.toLong()
        var counterfuit_goods_price = 100000.toLong()
        var casino_price = 1000000.toLong()
        var boardwalk_empire_price = 100000000.toLong()

        var bitcoin_price = 5000.toLong()
        var bitcoin_timer = 0

        var shares_price = 750000.toLong()
        var shares_timer = 0

        var gold_price = 900000000.toLong()
        var gold_timer = 0

        var NFT_price = 750000000000.toLong()
        var NFT_timer = 0

        var rand_unlucky = 700

        fun reset() {
            cnt = 0.toLong()
            moneyperclick = 1.toLong()
            lem_stand_price = 100.toLong()
            startUp_price = 1000.toLong()
            advAgency_price = 12000.toLong()
            twitter_price = 150000.toLong()
            google_price = 1500000.toLong()
            tourism_price = 30000000.toLong()
            moneypersec = 0.toLong()
            timerrate = 1000
            is_timerrate_too_low = false

            underground_bar_price = 1000.toLong()
            night_club_price = 10000.toLong()
            counterfuit_goods_price = 100000.toLong()
            casino_price = 1000000.toLong()
            boardwalk_empire_price = 100000000.toLong()

        }

        fun get_timer_rate() {
            timerrate = (1000 / moneypersec)
            if (timerrate < 100) {
                timerrate = 100
                is_timerrate_too_low = true
            }
        }

        //BUY FUNCTIONS
        fun buylem(): result {
            val res: result?
            if (cnt >= lem_stand_price) {
                cnt -= lem_stand_price
                lem_stand_price = (lem_stand_price * add).toLong()
                moneypersec += 1
                get_timer_rate()
                res = result.SUCCESS
            } else res = result.NOT_ENOUGH
            return res
        }

        fun buyStartUp(): result {
            val res: result?
            if (cnt >= startUp_price) {
                cnt -= startUp_price
                startUp_price = (startUp_price * add).toLong()
                moneypersec += 5
                get_timer_rate()
                res = result.SUCCESS
            } else res = result.NOT_ENOUGH
            return res
        }

        fun buyAdvAgency(): result {
            val res: result?
            if (cnt >= advAgency_price) {
                cnt -= advAgency_price
                advAgency_price = (advAgency_price * add).toLong()
                moneypersec += 27
                get_timer_rate()
                res = result.SUCCESS
            } else res = result.NOT_ENOUGH
            return res
        }

        fun buyTwitter(): result {
            val res: result?
            if (cnt >= twitter_price) {
                cnt -= twitter_price
                twitter_price = (twitter_price * add).toLong()
                moneypersec += 109
                get_timer_rate()
                res = result.SUCCESS
            } else res = result.NOT_ENOUGH
            return res
        }

        fun buyGoogle(): result {
            val res: result?
            if (cnt >= google_price) {
                cnt -= google_price
                google_price = (google_price * add).toLong()
                moneypersec += 600
                get_timer_rate()
                res = result.SUCCESS
            } else res = result.NOT_ENOUGH
            return res
        }

        fun buyTourism(): result {
            val res: result?
            if (cnt >= tourism_price) {
                cnt -= tourism_price
                tourism_price = (tourism_price * add).toLong()
                moneypersec += 8000
                get_timer_rate()
                res = result.SUCCESS
            } else res = result.NOT_ENOUGH
            return res
        }

        //OTHER BUISNESS
        fun buybar(): result {
            val res: result?
            if (cnt >= underground_bar_price) {
                val rand = (1..1000).random()
                if (rand <= 200) {
                    cnt -= 5 * underground_bar_price
                    res = result.UNLUCKY
                } else {
                    res = result.SUCCESS
                }
                cnt -= underground_bar_price
                underground_bar_price = (underground_bar_price * add).toLong()
                moneypersec += 5
                moneyperclick += 15
                get_timer_rate()
            } else {
                res = result.NOT_ENOUGH
            }
            return res
        }

        fun buyclub(): result {
            val res: result?
            if (cnt >= night_club_price) {
                val rand = (1..1000).random()
                if (rand <= 200) {
                    cnt -= 5 * night_club_price
                    res = result.UNLUCKY
                } else {
                    res = result.SUCCESS
                }
                cnt -= night_club_price
                night_club_price = (night_club_price * add).toLong()
                moneypersec += 27
                moneyperclick += 81
                get_timer_rate()
            } else res = result.NOT_ENOUGH
            return res
        }

        fun buycounterfuit(): result {
            val res: result?
            if (cnt >= counterfuit_goods_price) {
                val rand = (1..1000).random()
                if (rand <= 200) {
                    cnt -= 5 * counterfuit_goods_price
                    res = result.UNLUCKY
                } else {
                    res = result.SUCCESS
                }
                cnt -= counterfuit_goods_price
                counterfuit_goods_price = (counterfuit_goods_price * add).toLong()
                moneypersec += 109
                moneyperclick += 327
                get_timer_rate()
            } else res = result.NOT_ENOUGH
            return res
        }

        fun buycasino(): result {
            val res: result?
            if (cnt >= casino_price) {
                val rand = (1..1000).random()
                if (rand <= 200) {
                    cnt -= 5 * casino_price
                    res = result.UNLUCKY
                } else {
                    res = result.SUCCESS
                }
                cnt -= casino_price
                casino_price = (casino_price * add).toLong()
                moneypersec += 600
                moneyperclick += 1800
                get_timer_rate()
            } else res = result.NOT_ENOUGH
            return res
        }

        fun buyempire(): result {
            val res: result?
            if (cnt >= boardwalk_empire_price) {
                val rand = (1..1000).random()
                if (rand <= 200) {
                    cnt -= 5 * boardwalk_empire_price
                    res = result.UNLUCKY
                } else {
                    res = result.SUCCESS
                }
                cnt -= boardwalk_empire_price
                boardwalk_empire_price = (boardwalk_empire_price * add).toLong();
                moneypersec += 8000
                moneyperclick += 24000
                get_timer_rate()
            } else res = result.NOT_ENOUGH
            return res
        }

        fun buybitcoin(): result {
            val res: result?
            if (cnt >= bitcoin_price && bitcoin_timer == 0) {
                val rand = (1..1000).random()
                if (rand <= 300 + rand_unlucky) {
                    cnt += 5 * bitcoin_price
                    res = result.SUCCESS
                    rand_unlucky = 0
                } else {
                    cnt -= bitcoin_price
                    res = result.UNLUCKY
                    rand_unlucky += 100
                }
            } else res = result.NOT_ENOUGH
            return res
        }
        fun buyshares(): result {
            val res: result?
            if (cnt >= shares_price && shares_timer == 0) {
                val rand = (1..1000).random()
                if (rand <= 300 + rand_unlucky) {
                    cnt += 5 * shares_price
                    res = result.SUCCESS
                    rand_unlucky = 0
                } else {
                    cnt -= shares_price
                    res = result.UNLUCKY
                    rand_unlucky += 100
                }
            } else res = result.NOT_ENOUGH
            return res
        }

        fun buygold(): result {
            val res: result?
            if (cnt >= gold_price && gold_timer == 0) {
                val rand = (1..1000).random()
                if (rand <= 300 + rand_unlucky) {
                    cnt += 5 * gold_price
                    res = result.SUCCESS
                    rand_unlucky = 0
                } else {
                    cnt -= gold_price
                    res = result.UNLUCKY
                    rand_unlucky += 100
                }
            } else res = result.NOT_ENOUGH
            return res
        }

        fun buynft(): result {
            val res: result?
            if (cnt >= NFT_price && NFT_timer == 0) {
                val rand = (1..1000).random()
                if (rand <= 300 + rand_unlucky) {
                    cnt += 5 * NFT_price
                    res = result.SUCCESS
                    rand_unlucky = 0
                } else {
                    cnt -= NFT_price
                    res = result.UNLUCKY
                    rand_unlucky += 100
                }
            } else res = result.NOT_ENOUGH
            return res
        }
    }
}