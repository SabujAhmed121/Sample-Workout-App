package eu.tutorials.a7_minutesworkoutapp

import android.app.Application

class HistoryApp: Application() {

    val db by lazy {
        HistoryDatabase.getInstance(this)
    }
}