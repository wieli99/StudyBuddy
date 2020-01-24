package com.wieliFritz.studybuddy

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout

class Settings : AppCompatActivity() {
    var current = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setBackground()

        seekBarListeners()


        //Read the sharedPreferences money variable
        val prefsMain = getSharedPreferences("Main", 0)

        //Set saved value on startup
        findViewById<EditText>(R.id.editNameText).setText(prefsMain.getString("Name", "Frederick"))
    }


    override fun onPause() {
        super.onPause()
        storeName(getSharedPreferences("Main", 0))
    }


    fun storeName(sharedPref: SharedPreferences){
        with (sharedPref.edit()) {
            putString("Name", findViewById<EditText>(R.id.editNameText).text.toString())
            apply()
        }
    }


    fun storeTime(sharedPref: SharedPreferences){
        with (sharedPref.edit()) {
            putInt("Time", current)
            apply()
        }
    }


    fun seekBarListeners(){
        val seekBarValue = findViewById<TextView>(R.id.seekBarValue)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val prefs = getSharedPreferences("Main", 0)
        current = prefs.getInt("Time", 25)

        seekBar.setProgress(current - 5)
        seekBarValue.setText(current.toString() + getString(R.string.stats_min))

        seekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                current = progress + 5 //5 = min
                seekBarValue.setText(current.toString() + getString(R.string.stats_min))
                storeTime(prefs)
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
            }
        })
    }


    fun setBackground(){
        val mainPrefs = getSharedPreferences("Main", 0)
        val backgroundsMap = mapOf("background1" to R.drawable.background1, "background2" to R.drawable.background2, "background3" to R.drawable.background3, "background4" to R.drawable.background4, "background5" to R.drawable.background5, "background6" to R.drawable.background6)
        findViewById<ConstraintLayout>(R.id.settingsCL).setBackgroundResource(backgroundsMap.get(mainPrefs.getString("ActiveBackground", "background1"))!!) //give ID to layout in XML
    }
}
