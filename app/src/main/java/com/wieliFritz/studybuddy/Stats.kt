package com.wieliFritz.studybuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class Stats : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)
        loadStatsValues()
        setBackground()
    }

    fun loadStatsValues(){
        val totalTime = findViewById<TextView>(R.id.statsTotalTime)
        val totalSessions = findViewById<TextView>(R.id.statsTotalSessions)
        val totalMoney = findViewById<TextView>(R.id.statsTotalMoney)
        val averageSessions = findViewById<TextView>(R.id.statsAvgSessionLength)
        val totalItems = findViewById<TextView>(R.id.statsTotalItemsBought)
        val totalEntries = findViewById<TextView>(R.id.statsTotalHOfEntries)

        val prefsMain = getSharedPreferences("Main", 0)

        totalTime.setText((prefsMain.getInt("TotalMoney", 0) * 10 / 60).toString() + " " + getString(R.string.stats_min)) //Calculates time based in money
        totalSessions.setText((prefsMain.getInt("TotalSessions", 0)).toString() + " " + getString(R.string.stats_sessions))
        totalMoney.setText((prefsMain.getInt("TotalMoney", 0)).toString() + " " + getString(R.string.money_unit))
        averageSessions.setText((prefsMain.getInt("TotalMoney", 0) * 10 / 60 / prefsMain.getInt("TotalSessions", 1)).toString() + " " + getString(R.string.stats_min))
        totalItems.setText((prefsMain.getInt("TotalItems", 0)).toString() + " " + getString(R.string.stats_items))
        totalEntries.setText((prefsMain.getInt("HOFEntries", 0)).toString() + " " + getString(R.string.stats_entries))

        Log.i("TotalTime", (prefsMain.getInt("TotalMoney", 0) * 10 / 60).toString() + getString(R.string.stats_min))
        Log.i("TotalSessions", (prefsMain.getInt("TotalSessions", 0)).toString() + getString(R.string.stats_sessions))
        Log.i("TotalMoney", (prefsMain.getInt("TotalMoney", 0)).toString() + getString(R.string.money_unit))
        Log.i("AvgSession", (prefsMain.getInt("TotalMoney", 0) * 10 / 60 / prefsMain.getInt("TotalSessions", 1)).toString() +  getString(R.string.stats_min))
        Log.i("TotalItems", (prefsMain.getInt("TODO", 0) * 10).toString() + getString(R.string.stats_items))
        Log.i("TotalEntries", (prefsMain.getInt("HOFEntries", 0)).toString() + getString(R.string.stats_entries))
    }

    fun setBackground(){
        val mainPrefs = getSharedPreferences("Main", 0)
        val backgroundsMap = mapOf("background1" to R.drawable.background1, "background2" to R.drawable.background2, "background3" to R.drawable.background3, "background4" to R.drawable.background4, "background5" to R.drawable.background5, "background6" to R.drawable.background6)
        findViewById<ConstraintLayout>(R.id.statsCL).setBackgroundResource(backgroundsMap.get(mainPrefs.getString("ActiveBackground", "background1"))!!) //give ID to layout in XML
    }
}
