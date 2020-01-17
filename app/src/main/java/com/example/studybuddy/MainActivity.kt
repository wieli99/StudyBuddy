package com.example.studybuddy

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.*

class MainActivity : AppCompatActivity() {
    var reachedZero: Boolean = false
    var money = 0
    var moneyEveryTenSeconds = 0

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

    }


    fun createOnClickListeners(timer: Chronometer, sharedPref: SharedPreferences){
        val btnPause =  findViewById<Button>(R.id.pauseButtonMain)
        val btnStart =  findViewById<Button>(R.id.startButtonMain)

        var lastPause: Long = SystemClock.elapsedRealtime()
        var isPaused = false


        btnStart.setOnClickListener{
            val timeMilliSeconds = 1 * 20 * 1000 //25 Min
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

        timer.setOnChronometerTickListener {
            checkIfZeroIsReached(timer, btnStart)
            moneyEveryTenSeconds += 1
            if (moneyEveryTenSeconds == 10){
                moneyEveryTenSeconds = 0
                money += 1
                storeMoney(money, sharedPref)
            }
        }
    }



    fun checkIfZeroIsReached(timer: Chronometer, btnStart: Button){
        Log.i("timeLeft", (timer.getBase() - SystemClock.elapsedRealtime()).toString())

        if (timer.getBase() - SystemClock.elapsedRealtime() <= 0){
            reachedZero = true
            timer.stop()
            btnStart.setText(getString(R.string.start_button))
            Toast.makeText(this@MainActivity, "You should take a short brake now!", Toast.LENGTH_LONG).show()

        }
    }



    fun storeMoney(money: Int, sharedPref: SharedPreferences){
        with (sharedPref.edit()) {
            putInt("Money", money)
            apply()
        }

        Log.i("Money", sharedPref.getInt("Money", 0).toString())

        findViewById<TextView>(R.id.moneyMain).setText(sharedPref.getInt("Money", 0).toString() + "c")
    }
}


