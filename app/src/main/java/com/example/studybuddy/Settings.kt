package com.example.studybuddy

import android.app.Activity
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

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
            //To Hide Keyboard
            findViewById<EditText>(R.id.editNameText).onEditorAction(EditorInfo.IME_ACTION_DONE);
        }

        saveTimeBtn.setOnClickListener{
            storeTime(prefsMain)
            //To Hide Keyboard
            findViewById<EditText>(R.id.editNameText).onEditorAction(EditorInfo.IME_ACTION_DONE);
        }
    }

    fun storeName(sharedPref: SharedPreferences){
        with (sharedPref.edit()) {
            putString("Name", findViewById<EditText>(R.id.editNameText).text.toString())
            apply()
        }
        Toast.makeText(this@Settings, "Name setting saved!", Toast.LENGTH_SHORT).show()
    }

    fun storeTime(sharedPref: SharedPreferences){
        with (sharedPref.edit()) {
            putInt("Time", findViewById<EditText>(R.id.editTimeText).text.toString().toInt()) //Seems stupid... but this is the way
            apply()
        }
        Toast.makeText(this@Settings, "Time setting saved!", Toast.LENGTH_SHORT).show()
    }
}
