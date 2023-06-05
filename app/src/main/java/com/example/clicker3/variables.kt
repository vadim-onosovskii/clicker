package com.example.clicker3

import android.content.Context
import android.widget.TextView

class variables {
    companion object {
        public var cnt = 0
        public var moneypersec = 0
        public var add = 1.2
        public var lem_stand_price = 100
        public var startUp = 1000
        public var advAgency = 12000
        public var twitter = 150000
        public var google = 15000000
        public var tourism = 30000000
        fun buylem(): Boolean {
            if (cnt >= lem_stand_price) {
                cnt -= lem_stand_price
                lem_stand_price = (lem_stand_price * add).toInt();
                moneypersec += 1
                return true;
            }
            return false;
        }

        fun buyStartUp(): Boolean {
            if (cnt >= startUp) {
                cnt -= startUp
                startUp = (startUp * add).toInt();
                moneypersec += 5
                return true;
            }
            return false;
        }

        fun buyAdvAgency(): Boolean {
            if (cnt >= advAgency) {
                cnt -= advAgency
                advAgency = (advAgency * add).toInt();
                moneypersec += 27
                return true;
            }
            return false;
        }

        fun buyTwitter(): Boolean {
            if (cnt >= twitter) {
                cnt -= twitter
                twitter = (twitter * add).toInt();
                moneypersec += 109
                return true;
            }
            return false;
        }

        fun buyGoogle(): Boolean {
            if (cnt >= google) {
                cnt -= google
                google = (google * add).toInt();
                moneypersec += 600
                return true;
            }
            return false;
        }

        fun buyTourism(): Boolean {
            if (cnt >= tourism) {
                cnt -= tourism
                tourism = (tourism * add).toInt();
                moneypersec += 8000
                return true;
            }
            return false;
        }
    }
}