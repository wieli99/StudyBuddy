package com.example.studybuddy

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class ShopPots : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_pots)

        //Read the sharedPreferences money variable
        var prefsMain = getSharedPreferences("Main", 0)

        Log.i("Money", prefsMain.getInt("Money", 0).toString())

        //Create sharedPreferences for Pots
        val prefsShopPots = this@ShopPots.getSharedPreferences("Pots", Context.MODE_PRIVATE)

        //Buttons to boy the pots
        var button = findViewById<Button>(R.id.buyPotButton)
        var button2 = findViewById<Button>(R.id.buyPotButton2)
        var button3 = findViewById<Button>(R.id.buyPotButton3)
        var button4 = findViewById<Button>(R.id.buyPotButton4)
        var button5 = findViewById<Button>(R.id.buyPotButton5)
        var button6 = findViewById<Button>(R.id.buyPotButton6)

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
            if (!checkIfPotIsAlreadyPurchased("pot", prefsShopPots)){
                storeBoughtPot("pot", prefsShopPots)
                setButtonText(button)
            } else {
                Toast.makeText(this@ShopPots, "Pot already purchased", Toast.LENGTH_SHORT).show()
            }
        }

        button2.setOnClickListener{
            if (!checkIfPotIsAlreadyPurchased("pot2", prefsShopPots)){
                storeBoughtPot("pot2", prefsShopPots)
                setButtonText(button2)
            } else {
                Toast.makeText(this@ShopPots, "Pot already purchased", Toast.LENGTH_SHORT).show()
            }
        }

        button3.setOnClickListener{
            if (!checkIfPotIsAlreadyPurchased("pot3", prefsShopPots)){
                storeBoughtPot("pot3", prefsShopPots)
                setButtonText(button3)
            } else {
                Toast.makeText(this@ShopPots, "Pot already purchased", Toast.LENGTH_SHORT).show()
            }
        }

        button4.setOnClickListener{
            if (!checkIfPotIsAlreadyPurchased("pot4", prefsShopPots)){
                storeBoughtPot("pot4", prefsShopPots)
                setButtonText(button4)
            } else {
                Toast.makeText(this@ShopPots, "Pot already purchased", Toast.LENGTH_SHORT).show()
            }
        }

        button5.setOnClickListener{
            if (!checkIfPotIsAlreadyPurchased("pot5", prefsShopPots)){
                storeBoughtPot("pot5", prefsShopPots)
                setButtonText(button5)
            } else {
                Toast.makeText(this@ShopPots, "Pot already purchased", Toast.LENGTH_SHORT).show()
            }
        }

        button6.setOnClickListener{
            if (!checkIfPotIsAlreadyPurchased("pot6", prefsShopPots)){
                storeBoughtPot("pot6", prefsShopPots)
                setButtonText(button6)
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

    fun storeBoughtPot(pot: String, sharedPref: SharedPreferences){
        with (sharedPref.edit()) {
            putBoolean(pot, true)
            apply()
        }

        Log.i("PotBought", "bought " + pot + "!")
        Toast.makeText(this@ShopPots, "You bought a pot!", Toast.LENGTH_SHORT).show()
    }


}
