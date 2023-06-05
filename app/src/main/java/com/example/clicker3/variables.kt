package com.example.clicker3

import android.content.Context
import android.widget.TextView

class variables {
    companion object {
        public var cnt = 0.0f
        public var moneypersec = 0.0f
        public var add = 1.2f
        public var lem_stand = 100
        public var startUp = 1000
        public var advAgency = 12000
        public var twitter = 150000
        public var google = 15000000
        public var tourism = 30000000
        fun buylem() : Boolean {
            if (cnt >= lem_stand) {
                cnt -= lem_stand
                lem_stand = (lem_stand * add).toInt();
                moneypersec += 0.1f
                return true;
            }
            return false;
        }
        fun buyStartUp() : Boolean {
            if (cnt >= startUp) {
                cnt -= startUp
                startUp = (startUp * add).toInt();
                moneypersec += 1.0f
                return true;
            }
            return false;
        }
        fun buyAdvAgency() : Boolean {
            if (cnt >= advAgency) {
                cnt -= advAgency
                advAgency = (advAgency * add).toInt();
                moneypersec += 8.0f
                return true;
            }
            return false;
        }
        fun buyTwitter() : Boolean {
            if (cnt >= twitter) {
                cnt -= twitter
                twitter = (twitter * add).toInt();
                moneypersec += 90.0f
                return true;
            }
            return false;
        }
        fun buyGoogle() : Boolean {
            if (cnt >= google) {
                cnt -= google
                google = (google * add).toInt();
                moneypersec += 700.0f
                return true;
            }
            return false;
        }
        fun buyTourism() : Boolean {
            if (cnt >= tourism) {
                cnt -= tourism
                tourism = (tourism * add).toInt();
                moneypersec += 5000.0f
                return true;
            }
            return false;
        }
    }
}