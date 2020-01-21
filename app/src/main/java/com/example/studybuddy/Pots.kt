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

class Pots : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pots)

        val prefsShopPots = getSharedPreferences("Pots", 0)


        checkPotsPurchaseStatus(prefsShopPots)

        setOnclickListeners(prefsShopPots)

        setBackground()
    }

    private fun setOnclickListeners(prefsShopPots: SharedPreferences){
        val prefsMain = getSharedPreferences("Main", 0)
        val button = findViewById<Button>(R.id.selectPotButton)
        val button2 = findViewById<Button>(R.id.selectPotButton2)
        val button3 = findViewById<Button>(R.id.selectPotButton3)
        val button4 = findViewById<Button>(R.id.selectPotButton4)
        val button5 = findViewById<Button>(R.id.selectPotButton5)
        val button6 = findViewById<Button>(R.id.selectPotButton6)

        //Choose the currently active Pot
        getActivePot(prefsMain, listOf<Button>(button, button2, button3, button4, button5, button6))

        button.setOnClickListener{
            setActivePot("pot1", prefsMain)
            deselectAllPots(listOf(button, button2, button3, button4, button5, button6))
            button.setText(getString(R.string.active))
        }

        button2.setOnClickListener{
            if (checkIfPotIsAlreadyPurchased("pot2", prefsShopPots)){
                setActivePot("pot2", prefsMain)
                deselectAllPots(listOf(button, button2, button3, button4, button5, button6))
                button2.setText(getString(R.string.active))
            } else{
                Toast.makeText(this@Pots, getString(R.string.toast_purchase_in_shop), Toast.LENGTH_SHORT).show()
            }
        }

        button3.setOnClickListener{
            if (checkIfPotIsAlreadyPurchased("pot3", prefsShopPots)){
                setActivePot("pot3", prefsMain)
                deselectAllPots(listOf(button, button2, button3, button4, button5, button6))
                button3.setText(getString(R.string.active))
            } else{
                Toast.makeText(this@Pots, getString(R.string.toast_purchase_in_shop), Toast.LENGTH_SHORT).show()
            }
        }

        button4.setOnClickListener{
            if (checkIfPotIsAlreadyPurchased("pot4", prefsShopPots)){
                setActivePot("pot4", prefsMain)
                deselectAllPots(listOf(button, button2, button3, button4, button5, button6))
                button4.setText(getString(R.string.active))
            } else{
                Toast.makeText(this@Pots, getString(R.string.toast_purchase_in_shop), Toast.LENGTH_SHORT).show()
            }
        }

        button5.setOnClickListener{
            if (checkIfPotIsAlreadyPurchased("pot5", prefsShopPots)){
                setActivePot("pot5", prefsMain)
                deselectAllPots(listOf(button, button2, button3, button4, button5, button6))
                button5.setText(getString(R.string.active))
            } else{
                Toast.makeText(this@Pots, getString(R.string.toast_purchase_in_shop), Toast.LENGTH_SHORT).show()
            }
        }

        button6.setOnClickListener{
            if (checkIfPotIsAlreadyPurchased("pot6", prefsShopPots)){
                setActivePot("pot6", prefsMain)
                deselectAllPots(listOf(button, button2, button3, button4, button5, button6))
                button6.setText(getString(R.string.active))
            } else{
                Toast.makeText(this@Pots, getString(R.string.toast_purchase_in_shop), Toast.LENGTH_SHORT).show()
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
            Log.i("PotBoughtStatus", pot + getString(R.string.is_already_bought))
            return true
        }
        Log.i("PotBoughtStatus", pot + getString(R.string.is_not_yet_bought))
        return false
    }

    fun setActivePot(potName: String, mainPrefs: SharedPreferences){
        with (mainPrefs.edit()) {
            Log.i("ActivePot", potName)
            putString("ActivePot", potName)
            apply()
        }
    }

    fun getActivePot(prefsMain: SharedPreferences, btnList: List<Button>){
        val potsMap = mapOf("pot1" to btnList[0], "pot2" to btnList[1], "pot3" to btnList[2], "pot4" to btnList[3], "pot5" to btnList[4], "pot6" to btnList[5])
        //Set the value of the button of the currently active pot
        Log.i("DefaultActivePot", potsMap.get(prefsMain.getString("ActivePot", "pot1")).toString())
        potsMap.get(prefsMain.getString("ActivePot", "pot1"))?.setText(getString(R.string.active))
    }

    fun deselectAllPots(btnList: List<Button>){
        for (btn in btnList){
            btn.setText(getString(R.string.select))
        }
    }

    fun setBackground(){
        val mainPrefs = getSharedPreferences("Main", 0)
        val backgroundsMap = mapOf("background1" to R.drawable.background1, "background2" to R.drawable.background2, "background3" to R.drawable.background3, "background4" to R.drawable.background4, "background5" to R.drawable.background5, "background6" to R.drawable.background6)
        findViewById<ConstraintLayout>(R.id.PotsCL).setBackgroundResource(backgroundsMap.get(mainPrefs.getString("ActiveBackground", "background1"))!!) //give ID to layout in XML
    }
}
