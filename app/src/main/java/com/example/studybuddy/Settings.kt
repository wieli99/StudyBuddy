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
import androidx.constraintlayout.widget.ConstraintLayout

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setBackground()

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
        Toast.makeText(this@Settings, getString(R.string.setting_saved_name_toast), Toast.LENGTH_SHORT).show()
    }


    fun storeTime(sharedPref: SharedPreferences){
        with (sharedPref.edit()) {
            putInt("Time", findViewById<EditText>(R.id.editTimeText).text.toString().toInt()) //Seems stupid... but this is the way
            apply()
        }
        Toast.makeText(this@Settings, getString(R.string.setting_saved_time_toast), Toast.LENGTH_SHORT).show()
    }


    fun setBackground(){
        val mainPrefs = getSharedPreferences("Main", 0)
        val backgroundsMap = mapOf("background1" to R.drawable.background1, "background2" to R.drawable.background2, "background3" to R.drawable.background3, "background4" to R.drawable.background4, "background5" to R.drawable.background5, "background6" to R.drawable.background6)
        findViewById<ConstraintLayout>(R.id.settingsCL).setBackgroundResource(backgroundsMap.get(mainPrefs.getString("ActiveBackground", "background1"))!!) //give ID to layout in XML
    }
}
