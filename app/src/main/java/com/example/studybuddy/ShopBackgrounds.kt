package com.example.studybuddy

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class ShopBackgrounds : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_backgrounds)

        //Read the sharedPreferences money variable
        val prefsMain = getSharedPreferences("Main", 0)

        Log.i("Money", prefsMain.getInt("Money", 0).toString())

        //Create sharedPreferences for Backgrounds
        val prefsShopBackgrounds = this@ShopBackgrounds.getSharedPreferences("Backgrounds", Context.MODE_PRIVATE)

        //Set Money value
        findViewById<TextView>(R.id.moneyBackgroundsShop).setText(prefsMain.getInt("Money", 0).toString() + "c")

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
                if(purchaseBackground("background", prefsShopBackgrounds, 200)){
                    setButtonText(button)
                }
            } else {
                Toast.makeText(this@ShopBackgrounds, "Background already purchased", Toast.LENGTH_SHORT).show()
            }
        }

        button2.setOnClickListener{
            if (!checkIfBackgroundIsAlreadyPurchased("background2", prefsShopBackgrounds)){
                if(purchaseBackground("background2", prefsShopBackgrounds, 250)){
                    setButtonText(button2)
                }
            } else {
                Toast.makeText(this@ShopBackgrounds, "Background already purchased", Toast.LENGTH_SHORT).show()
            }
        }

        button3.setOnClickListener{
            if (!checkIfBackgroundIsAlreadyPurchased("background3", prefsShopBackgrounds)){
                if(purchaseBackground("background3", prefsShopBackgrounds, 300)) {
                        setButtonText(button3)
                    }
            } else {
                Toast.makeText(this@ShopBackgrounds, "Background already purchased", Toast.LENGTH_SHORT).show()
            }
        }

        button4.setOnClickListener{
            if (!checkIfBackgroundIsAlreadyPurchased("background4", prefsShopBackgrounds)){
                if(purchaseBackground("background4", prefsShopBackgrounds, 350)){
                    setButtonText(button4)
                }
            } else {
                Toast.makeText(this@ShopBackgrounds, "Background already purchased", Toast.LENGTH_SHORT).show()
            }
        }

        button5.setOnClickListener{
            if (!checkIfBackgroundIsAlreadyPurchased("background5", prefsShopBackgrounds)){
                if(purchaseBackground("background5", prefsShopBackgrounds, 400)){
                    setButtonText(button5)
                }
            } else {
                Toast.makeText(this@ShopBackgrounds, "Background already purchased", Toast.LENGTH_SHORT).show()
            }
        }

        button6.setOnClickListener{
            if (!checkIfBackgroundIsAlreadyPurchased("background6", prefsShopBackgrounds)){
                if(purchaseBackground("background6", prefsShopBackgrounds, 500)){
                    setButtonText(button6)
                }
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


    fun purchaseBackground(pot: String, sharedPref: SharedPreferences, price: Int): Boolean{
        var mainPrefs = getSharedPreferences("Main", 0)
        var money = mainPrefs.getInt("Money", 0)
        if (money >= price){
            updateMoney(mainPrefs, money, price)
            storeBoughtBackground(pot, sharedPref)
            storeItemBought(mainPrefs)
            return true
        } else{
            Toast.makeText(this@ShopBackgrounds, "Not enough money!", Toast.LENGTH_SHORT).show()
            return false
        }
    }


    fun updateMoney(mainPrefs: SharedPreferences, money: Int, price: Int){
        with (mainPrefs.edit()) {
            Log.i("Money", money.toString())
            putInt("Money", money-price)
            apply()
        }
        findViewById<TextView>(R.id.moneyBackgroundsShop).setText(mainPrefs.getInt("Money", 0).toString())
        Log.i("Money", money.toString())
    }


    fun storeBoughtBackground(backgrond: String, sharedPref: SharedPreferences){
        with (sharedPref.edit()) {
            putBoolean(backgrond, true)
            apply()
        }

        Log.i("BackgroundBought", "bought " + backgrond + "!")
        Toast.makeText(this@ShopBackgrounds, "You bought a background!", Toast.LENGTH_SHORT).show()
    }

    fun storeItemBought(preferences: SharedPreferences){
        with (preferences.edit()) {
            putInt("TotalItems", preferences.getInt("TotalItems", 0) + 1)
            apply()
        }
    }


}
