package com.example.studybuddy

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        //Read the sharedPreferences money variable
        val prefsMain = getSharedPreferences("Main", 0)
        val saveNameBtn = findViewById<Button>(R.id.saveNameSettingsButton)
        val saveTimeBtn = findViewById<Button>(R.id.saveTimeSettingsButton)

        //Set saved value on startup
        findViewById<EditText>(R.id.editNameText).setText(prefsMain.getString("Name", "Frederick"))
        findViewById<EditText>(R.id.editTimeText).setText(prefsMain.getInt("Time", 25).toString())

        saveNameBtn.setOnClickListener{
            storeName(prefsMain)
        }

        saveTimeBtn.setOnClickListener{
            storeTime(prefsMain)
        }
    }

    fun storeName(sharedPref: SharedPreferences){
        with (sharedPref.edit()) {
            putString("Name", findViewById<EditText>(R.id.editNameText).text.toString())
            apply()
        }
    }

    fun storeTime(sharedPref: SharedPreferences){
        with (sharedPref.edit()) {
            putInt("Time", findViewById<EditText>(R.id.editTimeText).text.toString().toInt()) //Seems stupid... but this is the way
            apply()
        }
    }
}
