package com.example.studybuddy

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class ShopPots : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_pots)

        //Read the sharedPreferences money variable
        val prefsMain = getSharedPreferences("Main", 0)

        Log.i("Money", prefsMain.getInt("Money", 0).toString())

        //Create sharedPreferences for Pots
        val prefsShopPots = this@ShopPots.getSharedPreferences("Pots", Context.MODE_PRIVATE)

        //Set Money value
        findViewById<TextView>(R.id.moneyPotsShop).setText(prefsMain.getInt("Money", 0).toString() + "c")

        //Buttons to buy the pots
        val button = findViewById<Button>(R.id.buyPotButton)
        val button2 = findViewById<Button>(R.id.buyPotButton2)
        val button3 = findViewById<Button>(R.id.buyPotButton3)
        val button4 = findViewById<Button>(R.id.buyPotButton4)
        val button5 = findViewById<Button>(R.id.buyPotButton5)
        val button6 = findViewById<Button>(R.id.buyPotButton6)

        val buttonlist = listOf<Button>(button, button2, button3, button4, button5, button6)

        //Set Value for Button depending on purchase-state
        if (checkIfPotIsAlreadyPurchased("pot", prefsShopPots)){
            setButtonText(button)
        }
        for (i in 2..6){
            if (checkIfPotIsAlreadyPurchased("pot"+i, prefsShopPots)){
                setButtonText(buttonlist[i-1])
            }
            Log.i("i", buttonlist[i-1].toString())
        }


        button.setOnClickListener{
            Toast.makeText(this@ShopPots, "Pot already purchased", Toast.LENGTH_SHORT).show()
        }

        button2.setOnClickListener{
            if (!checkIfPotIsAlreadyPurchased("pot2", prefsShopPots)){
                if(purchasePot("pot2", prefsShopPots, 250)){
                    setButtonText(button2)
                }
            } else {
                Toast.makeText(this@ShopPots, "Pot already purchased", Toast.LENGTH_SHORT).show()
            }
        }

        button3.setOnClickListener{
            if (!checkIfPotIsAlreadyPurchased("pot3", prefsShopPots)){
                if(purchasePot("pot3", prefsShopPots, 300)){
                    setButtonText(button3)
                }
            } else {
                Toast.makeText(this@ShopPots, "Pot already purchased", Toast.LENGTH_SHORT).show()
            }
        }

        button4.setOnClickListener{
            if (!checkIfPotIsAlreadyPurchased("pot4", prefsShopPots)){
                if(purchasePot("pot4", prefsShopPots, 350)){
                    setButtonText(button4)
                }
            } else {
                Toast.makeText(this@ShopPots, "Pot already purchased", Toast.LENGTH_SHORT).show()
            }
        }

        button5.setOnClickListener{
            if (!checkIfPotIsAlreadyPurchased("pot5", prefsShopPots)){
                if(purchasePot("pot5", prefsShopPots, 400)){
                    setButtonText(button5)
                }
            } else {
                Toast.makeText(this@ShopPots, "Pot already purchased", Toast.LENGTH_SHORT).show()
            }
        }

        button6.setOnClickListener{
            if (!checkIfPotIsAlreadyPurchased("pot6", prefsShopPots)){
                if(purchasePot("pot6", prefsShopPots, 500)){
                    setButtonText(button6)
                }
            } else {
                Toast.makeText(this@ShopPots, "Pot already purchased", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun setButtonText(btn: Button){
        btn.text = getString(R.string.purchased)
    }


    fun checkIfPotIsAlreadyPurchased(pot: String, sharedPref: SharedPreferences): Boolean{
        if (sharedPref.getBoolean(pot, false)){
            Log.i("PotBoughtStatus", pot + " is already bought")
            return true
        }
        Log.i("PotBoughtStatus", pot + " is not yet bought")
        return false
    }

    fun purchasePot(pot: String, sharedPref: SharedPreferences, price: Int): Boolean{
        var mainPrefs = getSharedPreferences("Main", 0)
        var money = mainPrefs.getInt("Money", 0)
        if (money >= price){
            updateMoney(mainPrefs, money, price)
            storeBoughtPot(pot, sharedPref)
            storeItemBought(mainPrefs)
            return true
        } else{
            Toast.makeText(this@ShopPots, "Not enough money!", Toast.LENGTH_SHORT).show()
            return false
        }
    }


    fun updateMoney(mainPrefs: SharedPreferences, money: Int, price: Int){
        with (mainPrefs.edit()) {
            Log.i("Money", money.toString())
            putInt("Money", money-price)
            apply()
        }
        findViewById<TextView>(R.id.moneyPotsShop).setText(mainPrefs.getInt("Money", 0).toString())
        Log.i("Money", money.toString())
    }


    fun storeBoughtPot(pot: String, sharedPref: SharedPreferences){
        with (sharedPref.edit()) {
            putBoolean(pot, true)
            apply()
        }

        Log.i("PotBought", "bought " + pot + "!")
        Toast.makeText(this@ShopPots, "You bought a pot!", Toast.LENGTH_SHORT).show()
    }

    fun storeItemBought(preferences: SharedPreferences){
        with (preferences.edit()) {
            putInt("TotalItems", preferences.getInt("TotalItems", 0) + 1)
            apply()
        }
    }


}
