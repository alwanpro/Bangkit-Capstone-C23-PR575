package com.example.nutriku.Utils

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import java.util.Date

sealed class timephase(timepattern : String) {
    object PAGI : timephase("05:00")
    object SIANG : timephase("11:00")
    object SORE : timephase("15:00")
    object MALAM : timephase("18:00")
}

class DateTime {
    fun getDayPhase() : String {
        val pattern = SimpleDateFormat("HH:MM")
        val time = Date()
        if(time.compareTo(pattern.parse(timephase.PAGI.toString())) > 0) return "Pagi"
        if(time.compareTo(pattern.parse(timephase.SIANG.toString())) > 0) return "Siang"
        return if(time.compareTo(pattern.parse(timephase.SORE.toString())) > 0) "Sore"
        else "Malam"
    }
}