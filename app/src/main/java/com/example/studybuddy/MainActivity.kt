package com.example.studybuddy

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    var reachedZero: Boolean = false
    var money = 0
    var moneyEveryTenSeconds = 0
    var minutes = 25

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val sharedPref = this@MainActivity.getSharedPreferences("Main", Context.MODE_PRIVATE)
        money = sharedPref.getInt("Money", 0)

        //Set correct Money on startup to textView
        findViewById<TextView>(R.id.moneyMain).setText(sharedPref.getInt("Money", 0).toString() + "c")


        changeScreensListeners()


        //Prepare Timer
        val timer = findViewById<Chronometer>(R.id.simpleChronometer)
        timer.setCountDown(true)



        createOnClickListeners(timer, sharedPref)

        setBackground()
        setStudyBuddy(sharedPref)
    }


    //Also executes when activity is restarted by the back navigation
    override fun onResume() {
        super.onResume()
        val sharedPref = this@MainActivity.getSharedPreferences("Main", Context.MODE_PRIVATE)
        setStudyBuddyName(sharedPref)
        setTimerTime(sharedPref)
        money = sharedPref.getInt("Money", 0)
        findViewById<TextView>(R.id.moneyMain).setText(sharedPref.getInt("Money", 0).toString() + "c")
        setBackground()
        setStudyBuddy(sharedPref)
    }


    private fun changeScreensListeners() {
        val shoppingBtn = findViewById<ImageView>(R.id.shoppingCartMain)
        //To Navigate To Shop
        shoppingBtn.setOnClickListener {
            startActivity(Intent(this, Shop::class.java))
        }

        val settingsBtn = findViewById<ImageView>(R.id.settingsWheel)
        //To Navigate to Settings
        settingsBtn.setOnClickListener{
            startActivity(Intent(this, Settings::class.java))
        }

        val backgroundsBtn = findViewById<Button>(R.id.backgroundsButtonMain)
        //To navigate to Backgrounds selection
        backgroundsBtn.setOnClickListener{
            startActivity(Intent(this, Backgrounds::class.java))
        }

        val potsBtn = findViewById<Button>(R.id.potsButtonMain)
        //To navigate to Pots selection
        potsBtn.setOnClickListener{
            startActivity(Intent(this, Pots::class.java))
        }

    }


    fun createOnClickListeners(timer: Chronometer, sharedPref: SharedPreferences){
        val btnPause =  findViewById<Button>(R.id.pauseButtonMain)
        val btnStart =  findViewById<Button>(R.id.startButtonMain)
        val btnStats = findViewById<Button>(R.id.statsButtonMain)

        var lastPause: Long = SystemClock.elapsedRealtime()
        var isPaused = false


        btnStart.setOnClickListener{
            val timeMilliSeconds = minutes * 60 * 1000 //25 Min
            timer.setBase(SystemClock.elapsedRealtime() + timeMilliSeconds)
            timer.start()
            btnStart.setText(getString(R.string.restart_button))
            isPaused = false
            btnPause.setText(getString(R.string.pause_button))
            reachedZero = false
            moneyEveryTenSeconds = 0
        }

        btnPause.setOnClickListener{
            if (!isPaused) {
                lastPause = SystemClock.elapsedRealtime()
                timer.stop()
                isPaused = true
                btnPause.setText(getString(R.string.resume_button))
            } else if (!reachedZero) {
                timer.setBase(timer.getBase() + SystemClock.elapsedRealtime() - lastPause)
                isPaused = false
                timer.start()
                btnPause.setText(getString(R.string.pause_button))
            }
        }

        btnStats.setOnClickListener{
            startActivity(Intent(this, Stats::class.java))
        }

        timer.setOnChronometerTickListener {
            checkIfZeroIsReached(timer, btnStart, sharedPref)
            moneyEveryTenSeconds += 1
            if (moneyEveryTenSeconds == 10){
                moneyEveryTenSeconds = 0
                money += 1
                storeMoney(money, sharedPref)
            }
            upgradeStudyBuddyWhenEnoughTimePassed()
            setStudyBuddy(this@MainActivity.getSharedPreferences("Main", Context.MODE_PRIVATE))
        }
    }



    fun checkIfZeroIsReached(timer: Chronometer, btnStart: Button, sharedPref: SharedPreferences){
        Log.i("timeLeft", (timer.getBase() - SystemClock.elapsedRealtime()).toString())

        if (timer.getBase() - SystemClock.elapsedRealtime() <= 0){
            reachedZero = true
            timer.stop()
            btnStart.setText(getString(R.string.start_button))
            Toast.makeText(this@MainActivity, "You should take a short brake now!", Toast.LENGTH_LONG).show()

            //Make entry for statistiks
            storeTimeForStatistiks(minutes, sharedPref)
        }
    }


    fun storeTimeForStatistiks(minutes: Int, sharedPref: SharedPreferences){
        with (sharedPref.edit()) {
            putInt("TotalSessions", sharedPref.getInt("TotalSessions", 0) +1)
            apply()
        }
    }


    fun storeMoney(money: Int, sharedPref: SharedPreferences){
        with (sharedPref.edit()) {
            putInt("Money", money)
            //For statistiks
            putInt("TotalMoney", sharedPref.getInt("TotalMoney", 0) +1)
            apply()
        }

        Log.i("Money", sharedPref.getInt("Money", 0).toString())

        findViewById<TextView>(R.id.moneyMain).setText(sharedPref.getInt("Money", 0).toString() + "c")
    }


    fun setStudyBuddyName(sharedPref: SharedPreferences){
        findViewById<TextView>(R.id.studyBuddyName).setText(sharedPref.getString("Name", "Frederick"))
    }


    fun setTimerTime(sharedPref: SharedPreferences){
        minutes = sharedPref.getInt("Time", 25)
    }


    fun setBackground(){
        val mainPrefs = getSharedPreferences("Main", 0)
        val backgroundsMap = mapOf("background1" to R.drawable.background1, "background2" to R.drawable.background2, "background3" to R.drawable.background3, "background4" to R.drawable.background4, "background5" to R.drawable.background5, "background6" to R.drawable.background6)
        findViewById<ConstraintLayout>(R.id.MainCL).setBackgroundResource(backgroundsMap.get(mainPrefs.getString("ActiveBackground", "background1"))!!) //give ID to layout in XML
    }


    fun setStudyBuddy(sharedPref: SharedPreferences){
        val studyBuddyMap = mapOf("buddy1pot1" to R.drawable.buddy1_pot1, "buddy1pot2" to R.drawable.buddy1_pot2, "buddy1pot3" to R.drawable.buddy1_pot3, "buddy1pot4" to R.drawable.buddy1_pot4, "buddy1pot5" to R.drawable.buddy1_pot5, "buddy1pot6" to R.drawable.buddy1_pot6,
            "buddy2pot1" to R.drawable.buddy2_pot1, "buddy2pot2" to R.drawable.buddy2_pot2, "buddy2pot3" to R.drawable.buddy2_pot3, "buddy2pot4" to R.drawable.buddy2_pot4, "buddy2pot5" to R.drawable.buddy2_pot5, "buddy2pot6" to R.drawable.buddy2_pot6,
            "buddy3pot1" to R.drawable.buddy3_pot1) //TODO: fehlende graphiken

        findViewById<ImageView>(R.id.studyBuddy).setImageResource(studyBuddyMap.get(sharedPref.getString("StudyBuddy", "buddy1") + sharedPref.getString("ActivePot", "pot1"))!!)
    }


    fun upgradeStudyBuddyWhenEnoughTimePassed(){
        val sharedPref = this@MainActivity.getSharedPreferences("Main", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putInt("UpgradeTime", sharedPref.getInt("UpgradeTime", 0) + 1)  //TODO: Reset this on HOF entry
            apply()
        }
        if (sharedPref.getInt("UpgradeTime", 1) % 7500 == 0){ //Every 7500 secs gained
            if (sharedPref.getString("StudyBuddy", "buddy1") == "buddy1"){ //And current studybuddy is v1
                setActiveStudyBuddy("buddy2", sharedPref)   //Upgrade to v2
            } else if (sharedPref.getString("StudyBuddy", "buddy1") == "buddy2"){
                setActiveStudyBuddy("buddy3", sharedPref)   //Upgrade to v2
            }
        }
    }


    fun setActiveStudyBuddy(name: String, sharedPref: SharedPreferences){
        with (sharedPref.edit()) {
            putString("StudyBuddy", name)
            apply()
        }
    }
}
