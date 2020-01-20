package com.example.studybuddy

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class Backgrounds : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_backgrounds)

        val prefsShopBackgrounds = getSharedPreferences("Backgrounds", 0)


        checkBackgroundsPurchaseStatus(prefsShopBackgrounds)

        setOnclickListeners(prefsShopBackgrounds)

        setBackground()
    }

    private fun setOnclickListeners(prefsShopBackgrounds: SharedPreferences){
        val prefsMain = getSharedPreferences("Main", 0)
        val button = findViewById<Button>(R.id.selectBackgroundButton)
        val button2 = findViewById<Button>(R.id.selectBackgroundButton2)
        val button3 = findViewById<Button>(R.id.selectBackgroundButton3)
        val button4 = findViewById<Button>(R.id.selectBackgroundButton4)
        val button5 = findViewById<Button>(R.id.selectBackgroundButton5)
        val button6 = findViewById<Button>(R.id.selectBackgroundButton6)

        //Choose the currently active Background
        getActiveBackground(prefsMain, listOf<Button>(button, button2, button3, button4, button5, button6))

        button.setOnClickListener{
            setActiveBackground("background1", prefsMain)
            deselectAllBackgrounds(listOf(button, button2, button3, button4, button5, button6))
            button.setText(getString(R.string.active))
        }

        button2.setOnClickListener{
            if (checkIfBackgroundIsAlreadyPurchased("background2", prefsShopBackgrounds)){
                setActiveBackground("background2", prefsMain)
                deselectAllBackgrounds(listOf(button, button2, button3, button4, button5, button6))
                button2.setText(getString(R.string.active))
            } else{
                Toast.makeText(this@Backgrounds, getString(R.string.toast_purchase_in_shop), Toast.LENGTH_SHORT).show()
            }
        }

        button3.setOnClickListener{
            if (checkIfBackgroundIsAlreadyPurchased("background3", prefsShopBackgrounds)){
                setActiveBackground("background3", prefsMain)
                deselectAllBackgrounds(listOf(button, button2, button3, button4, button5, button6))
                button3.setText(getString(R.string.active))
            } else{
                Toast.makeText(this@Backgrounds, getString(R.string.toast_purchase_in_shop), Toast.LENGTH_SHORT).show()
            }
        }

        button4.setOnClickListener{
            if (checkIfBackgroundIsAlreadyPurchased("background4", prefsShopBackgrounds)){
                setActiveBackground("background4", prefsMain)
                deselectAllBackgrounds(listOf(button, button2, button3, button4, button5, button6))
                button4.setText(getString(R.string.active))
            } else{
                Toast.makeText(this@Backgrounds, getString(R.string.toast_purchase_in_shop), Toast.LENGTH_SHORT).show()
            }
        }

        button5.setOnClickListener{
            if (checkIfBackgroundIsAlreadyPurchased("background5", prefsShopBackgrounds)){
                setActiveBackground("background5", prefsMain)
                deselectAllBackgrounds(listOf(button, button2, button3, button4, button5, button6))
                button5.setText(getString(R.string.active))
            } else{
                Toast.makeText(this@Backgrounds, getString(R.string.toast_purchase_in_shop), Toast.LENGTH_SHORT).show()
            }
        }

        button6.setOnClickListener{
            if (checkIfBackgroundIsAlreadyPurchased("background6", prefsShopBackgrounds)){
                setActiveBackground("background6", prefsMain)
                deselectAllBackgrounds(listOf(button, button2, button3, button4, button5, button6))
                button6.setText(getString(R.string.active))
            } else{
                Toast.makeText(this@Backgrounds, getString(R.string.toast_purchase_in_shop), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkBackgroundsPurchaseStatus(prefsShopBackgrounds: SharedPreferences) {
        if (checkIfBackgroundIsAlreadyPurchased("background2", prefsShopBackgrounds)) {
            removePadlock(findViewById(R.id.padlock2))
        }
        if (checkIfBackgroundIsAlreadyPurchased("background3", prefsShopBackgrounds)) {
            removePadlock(findViewById(R.id.padlock3))
        }
        if (checkIfBackgroundIsAlreadyPurchased("background4", prefsShopBackgrounds)) {
            removePadlock(findViewById(R.id.padlock4))
        }
        if (checkIfBackgroundIsAlreadyPurchased("background5", prefsShopBackgrounds)) {
            removePadlock(findViewById(R.id.padlock5))
        }
        if (checkIfBackgroundIsAlreadyPurchased("background6", prefsShopBackgrounds)) {
            removePadlock(findViewById(R.id.padlock6))
        }
    }

    fun removePadlock(padlock: ImageView){
        padlock.visibility = View.INVISIBLE
    }

    fun checkIfBackgroundIsAlreadyPurchased(background: String, sharedPref: SharedPreferences): Boolean{
        if (sharedPref.getBoolean(background, false)){
            Log.i("BackgroundBoughtStatus", background + getString(R.string.is_already_bought))
            return true
        }
        Log.i("BackgroundBoughtStatus", background + getString(R.string.is_not_yet_bought))
        return false
    }

    fun setActiveBackground(backgroundName: String, mainPrefs: SharedPreferences){
        with (mainPrefs.edit()) {
            Log.i("ActiveBackground", backgroundName)
            putString("ActiveBackground", backgroundName)
            apply()
        }
    }

    fun getActiveBackground(prefsMain: SharedPreferences, btnList: List<Button>){
        val backgroundsMap = mapOf("background1" to btnList[0], "background2" to btnList[1], "background3" to btnList[2], "background4" to btnList[3], "background5" to btnList[4], "background6" to btnList[5])
        //Set the value of the button of the currently active background
        Log.i("DefaultActiveBackground", backgroundsMap.get(prefsMain.getString("ActiveBackground", "background1")).toString())
        backgroundsMap.get(prefsMain.getString("ActiveBackground", "background1"))?.setText(getString(R.string.active))
    }

    fun deselectAllBackgrounds(btnList: List<Button>){
        for (btn in btnList){
            btn.setText(getString(R.string.select))
        }
    }

    fun setBackground(){
        val mainPrefs = getSharedPreferences("Main", 0)
        findViewById<ConstraintLayout>(R.id.BackgroundsCL).setBackgroundResource(R.drawable.background2) //give ID to layout in XML
        //TODO: Amke BG depend on selection
    }
}
