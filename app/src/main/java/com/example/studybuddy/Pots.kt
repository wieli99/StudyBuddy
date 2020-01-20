package com.example.studybuddy

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView

class Pots : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pots)

        val prefsShopPots = getSharedPreferences("Pots", 0)


        checkPotsPurchaseStatus(prefsShopPots)

    }

    private fun setOnclickListeners(){
        val prefsMain = getSharedPreferences("Main", 0)
        val button = findViewById<Button>(R.id.selectPotButton)
        val button2 = findViewById<Button>(R.id.selectPotButton2)
        val button3 = findViewById<Button>(R.id.selectPotButton3)
        val button4 = findViewById<Button>(R.id.selectPotButton4)
        val button5 = findViewById<Button>(R.id.selectPotButton5)
        val button6 = findViewById<Button>(R.id.selectPotButton6)

        button.setOnClickListener{
            setActivePot("pot1", prefsMain)
        }

        button2.setOnClickListener{
            if (checkIfPotIsAlreadyPurchased("pot2", prefsMain)){
                setActivePot("pot2", prefsMain)
            }
        }

        button3.setOnClickListener{
            if (checkIfPotIsAlreadyPurchased("pot3", prefsMain)){
                setActivePot("pot3", prefsMain)
            }
        }

        button4.setOnClickListener{
            if (checkIfPotIsAlreadyPurchased("pot4", prefsMain)){
                setActivePot("pot4", prefsMain)
            }
        }

        button5.setOnClickListener{
            if (checkIfPotIsAlreadyPurchased("pot5", prefsMain)){
                setActivePot("pot5", prefsMain)
            }
        }

        button6.setOnClickListener{
            if (checkIfPotIsAlreadyPurchased("pot6", prefsMain)){
                setActivePot("pot6", prefsMain)
            }
        }
    }

    private fun checkPotsPurchaseStatus(prefsShopPots: SharedPreferences) {
        if (checkIfPotIsAlreadyPurchased("pot2", prefsShopPots)) {
            removePadlock(findViewById(R.id.padlock2))
        }
        if (checkIfPotIsAlreadyPurchased("pot3", prefsShopPots)) {
            removePadlock(findViewById(R.id.padlock3))
        }
        if (checkIfPotIsAlreadyPurchased("pot4", prefsShopPots)) {
            removePadlock(findViewById(R.id.padlock4))
        }
        if (checkIfPotIsAlreadyPurchased("pot5", prefsShopPots)) {
            removePadlock(findViewById(R.id.padlock5))
        }
        if (checkIfPotIsAlreadyPurchased("pot6", prefsShopPots)) {
            removePadlock(findViewById(R.id.padlock6))
        }
    }

    fun removePadlock(padlock: ImageView){
        padlock.visibility = View.INVISIBLE
    }

    fun checkIfPotIsAlreadyPurchased(pot: String, sharedPref: SharedPreferences): Boolean{
        if (sharedPref.getBoolean(pot, false)){
            Log.i("PotBoughtStatus", pot + " is already bought")
            return true
        }
        Log.i("PotBoughtStatus", pot + " is not yet bought")
        return false
    }

    fun setActivePot(potName: String, mainPrefs: SharedPreferences){
        with (mainPrefs.edit()) {
            Log.i("ActivePot", potName)
            putString("ActivePot", potName)
            apply()
        }
    }
}
