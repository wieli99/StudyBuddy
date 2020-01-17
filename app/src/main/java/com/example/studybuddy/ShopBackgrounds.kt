package com.example.studybuddy

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class ShopBackgrounds : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_backgrounds)

        //Read the sharedPreferences money variable
        val prefs = getSharedPreferences("Main", 0)

        Log.i("Money", prefs.getInt("Money", 0).toString())

        //Create sharedPreferences for Backgrounds
        val prefsShopBackgrounds = this@ShopBackgrounds.getSharedPreferences("Backgrounds", Context.MODE_PRIVATE)

        //Buttons to buy the Backgrounds
        val button = findViewById<Button>(R.id.buyBackgroundButton)
        val button2 = findViewById<Button>(R.id.buyBackgroundButton2)
        val button3 = findViewById<Button>(R.id.buyBackgroundButton3)
        val button4 = findViewById<Button>(R.id.buyBackgroundButton4)
        val button5 = findViewById<Button>(R.id.buyBackgroundButton5)
        val button6 = findViewById<Button>(R.id.buyBackgroundButton6)

        val buttonlist = listOf<Button>(button, button2, button3, button4, button5, button6)

        //Set Value for Button depending on purchase-state
        if (checkIfBackgroundIsAlreadyPurchased("background", prefsShopBackgrounds)){
            setButtonText(button)
        }
        for (i in 2..6){
            if (checkIfBackgroundIsAlreadyPurchased("background"+i, prefsShopBackgrounds)){
                setButtonText(buttonlist[i-1])
            }
            Log.i("i", buttonlist[i-1].toString())
        }


        button.setOnClickListener{
            if (!checkIfBackgroundIsAlreadyPurchased("background", prefsShopBackgrounds)){
                storeBoughtBackground("background", prefsShopBackgrounds)
                setButtonText(button)
            } else {
                Toast.makeText(this@ShopBackgrounds, "Background already purchased", Toast.LENGTH_SHORT).show()
            }
        }

        button2.setOnClickListener{
            if (!checkIfBackgroundIsAlreadyPurchased("background2", prefsShopBackgrounds)){
                storeBoughtBackground("background2", prefsShopBackgrounds)
                setButtonText(button2)
            } else {
                Toast.makeText(this@ShopBackgrounds, "Background already purchased", Toast.LENGTH_SHORT).show()
            }
        }

        button3.setOnClickListener{
            if (!checkIfBackgroundIsAlreadyPurchased("background3", prefsShopBackgrounds)){
                storeBoughtBackground("background3", prefsShopBackgrounds)
                setButtonText(button3)
            } else {
                Toast.makeText(this@ShopBackgrounds, "Background already purchased", Toast.LENGTH_SHORT).show()
            }
        }

        button4.setOnClickListener{
            if (!checkIfBackgroundIsAlreadyPurchased("background4", prefsShopBackgrounds)){
                storeBoughtBackground("background4", prefsShopBackgrounds)
                setButtonText(button4)
            } else {
                Toast.makeText(this@ShopBackgrounds, "Background already purchased", Toast.LENGTH_SHORT).show()
            }
        }

        button5.setOnClickListener{
            if (!checkIfBackgroundIsAlreadyPurchased("background5", prefsShopBackgrounds)){
                storeBoughtBackground("background5", prefsShopBackgrounds)
                setButtonText(button5)
            } else {
                Toast.makeText(this@ShopBackgrounds, "Background already purchased", Toast.LENGTH_SHORT).show()
            }
        }

        button6.setOnClickListener{
            if (!checkIfBackgroundIsAlreadyPurchased("background6", prefsShopBackgrounds)){
                storeBoughtBackground("background6", prefsShopBackgrounds)
                setButtonText(button6)
            } else {
                Toast.makeText(this@ShopBackgrounds, "Background already purchased", Toast.LENGTH_SHORT).show()
            }
        }
    }



    fun setButtonText(btn: Button){
        btn.text = getString(R.string.purchased)
    }


    fun checkIfBackgroundIsAlreadyPurchased(background: String, sharedPref: SharedPreferences): Boolean{
        if (sharedPref.getBoolean(background, false)){
            Log.i("BackgroundBoughtStatus", background + " is already bought")
            return true
        }
        Log.i("BackgroundBoughtStatus", background + " is not yet bought")
        return false
    }

    fun storeBoughtBackground(backgrond: String, sharedPref: SharedPreferences){
        with (sharedPref.edit()) {
            putBoolean(backgrond, true)
            apply()
        }

        Log.i("BackgroundBought", "bought " + backgrond + "!")
        Toast.makeText(this@ShopBackgrounds, "You bought a background!", Toast.LENGTH_SHORT).show()
    }


}
