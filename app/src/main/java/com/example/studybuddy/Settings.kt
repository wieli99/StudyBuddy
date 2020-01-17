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

        findViewById<EditText>(R.id.editNameText).setText(prefsMain.getString("Name", "Frederick"))

        saveNameBtn.setOnClickListener{
            storeName(prefsMain)
        }
    }

    fun storeName(sharedPref: SharedPreferences){
        with (sharedPref.edit()) {
            putString("Name", findViewById<EditText>(R.id.editNameText).text.toString())
            apply()
        }
    }
}
