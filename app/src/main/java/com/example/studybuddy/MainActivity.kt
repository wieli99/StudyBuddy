package com.example.studybuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.*

class MainActivity : AppCompatActivity() {
    var reachedZero: Boolean = false
    var money = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnPause =  findViewById<Button>(R.id.PauseButton)
        val btnStart =  findViewById<Button>(R.id.StartButton)

        val btn = findViewById<ImageView>(R.id.ShoppingCart)

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
            val timeMilliSeconds = 1 * 60 * 1000 //25 Min
            timer.setBase(SystemClock.elapsedRealtime() + timeMilliSeconds)
            timer.start()
            btnStart.setText(getString(R.string.Restart))
            isPaused = false
            btnPause.setText(getString(R.string.Pause))
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
            money += 1
            Log.i("money", money.toString())
        }
    }

    fun testZero(timer: Chronometer, btnStart: Button){
        Log.i("timeLeft", (timer.getBase() - SystemClock.elapsedRealtime()).toString())
        if (timer.getBase() - SystemClock.elapsedRealtime() <= 0){
            reachedZero = true
            timer.stop()
            btnStart.setText(getString(R.string.Start))
            Toast.makeText(this@MainActivity, "You should now take a short brake!", Toast.LENGTH_LONG).show()

        }
    }
}


