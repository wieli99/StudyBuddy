package com.example.studybuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class Stats : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)
        loadStatsValues()
    }

    override fun onResume() {
        super.onResume()
        loadStatsValues()
    }

    fun loadStatsValues(){
        val totalTime = findViewById<TextView>(R.id.statsTotalTime)
        val totalSessions = findViewById<TextView>(R.id.statsTotalSessions)
        val totalMoney = findViewById<TextView>(R.id.statsTotalMoney)
        val averageSessions = findViewById<TextView>(R.id.statsAvgSessionLength)
        val totalItems = findViewById<TextView>(R.id.statsTotalItemsBought)

        val prefsMain = getSharedPreferences("Main", 0)

        totalTime.setText((prefsMain.getInt("TotalMoney", 0) * 10 / 60).toString() + " min") //Calculates time based in money
        totalSessions.setText((prefsMain.getInt("TotalSessions", 0)).toString() + " sessions")
        totalMoney.setText((prefsMain.getInt("TotalMoney", 0)).toString() + "c")
        averageSessions.setText((prefsMain.getInt("TotalMoney", 0) * 10 / 60 / prefsMain.getInt("TotalSessions", 0)).toString() + " min")
        totalItems.setText((prefsMain.getInt("TotalItems", 0)).toString() + " items")

        Log.i("TotalTime", (prefsMain.getInt("TotalMoney", 0) * 10 / 60).toString() + " min")
        Log.i("TotalSessions", (prefsMain.getInt("TotalSessions", 0)).toString() + " sessions")
        Log.i("TotalMoney", (prefsMain.getInt("TotalMoney", 0)).toString() + "c")
        Log.i("AvgSession", (prefsMain.getInt("TotalMoney", 0) * 10 / 60 / prefsMain.getInt("TotalSessions", 0)).toString() + " min")
        Log.i("TotalItems", (prefsMain.getInt("TODO", 0) * 10).toString() + " items")
    }
}
