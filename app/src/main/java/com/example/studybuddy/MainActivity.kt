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
        val btnPause =  findViewById<Button>(R.id.PauseButton)
        val btnStart =  findViewById<Button>(R.id.StartButton)

        val btn = findViewById<ImageView>(R.id.ShoppingCart)


        val sharedPref = this@MainActivity.getPreferences(Context.MODE_PRIVATE)
        money = sharedPref.getInt("Money", 0)

        //Set correct Money on startup to textView
        findViewById<TextView>(R.id.Money).setText(sharedPref.getInt("Money", 1).toString() + "c")

        //To Navigate To Shop
        btn.setOnClickListener{
            val intent = Intent(this, Shop::class.java)
            startActivity(intent)
        }









        var lastPause: Long = SystemClock.elapsedRealtime()
        var isPaused = false
        val timer = findViewById<Chronometer>(R.id.simpleChronometer)

        timer.setCountDown(true)

        btnStart.setOnClickListener{
            val timeMilliSeconds = 1 * 20 * 1000 //25 Min
            timer.setBase(SystemClock.elapsedRealtime() + timeMilliSeconds)
            timer.start()
            btnStart.setText(getString(R.string.Restart))
            isPaused = false
            btnPause.setText(getString(R.string.Pause))
            moneyEveryTenSeconds = 0
        }

        btnPause.setOnClickListener{
            if (!isPaused) {
                lastPause = SystemClock.elapsedRealtime()
                timer.stop()
                isPaused = true
                btnPause.setText(getString(R.string.Resume))
            } else if (!reachedZero) {
                timer.setBase(timer.getBase() + SystemClock.elapsedRealtime() - lastPause)
                isPaused = false
                timer.start()
                btnPause.setText(getString(R.string.Pause))
            }
        }

        timer.setOnChronometerTickListener {
            testZero(timer, btnStart)
            moneyEveryTenSeconds += 1
            if (moneyEveryTenSeconds == 10){
                moneyEveryTenSeconds = 0
                money += 1
                storeMoney(money, sharedPref)
            }
        }
    }

    fun testZero(timer: Chronometer, btnStart: Button){
        Log.i("timeLeft", (timer.getBase() - SystemClock.elapsedRealtime()).toString())
        if (timer.getBase() - SystemClock.elapsedRealtime() <= 0){
            reachedZero = true
            timer.stop()
            btnStart.setText(getString(R.string.Start))
            Toast.makeText(this@MainActivity, "You should take a short brake now!", Toast.LENGTH_LONG).show()

        }
    }


    fun storeMoney(money: Int, sharedPref: SharedPreferences){
        with (sharedPref.edit()) {
            putInt("Money", money)
            apply()
        }
        Log.i("Money", sharedPref.getInt("Money", 0).toString())
        findViewById<TextView>(R.id.Money).setText(sharedPref.getInt("Money", 0).toString() + "c")
    }
}


