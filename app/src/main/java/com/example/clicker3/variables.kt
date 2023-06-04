package com.example.clicker3

import android.content.Context
import android.widget.TextView

class variables {
    companion object {
        public var cnt = 0
        public var moneypersec = 1;
        public var add = 1.2
        public var lem_stand_price = 100
        fun buylem() : Boolean {
            if(cnt>= lem_stand_price) {
                cnt -= lem_stand_price
                lem_stand_price = (lem_stand_price * add).toInt();
                ++moneypersec
                return true;
            }
            return false;
        }
    }
    //w
}