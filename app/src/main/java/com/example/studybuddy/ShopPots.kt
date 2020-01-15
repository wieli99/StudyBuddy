package com.example.studybuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class ShopPots : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop_pots)

        //Read the sharedPreferences money variable
        var prefs = getSharedPreferences("Main", 0)

        Log.i("Money", prefs.getInt("Money", 0).toString())

        var button = findViewById<Button>(R.id.button)
        var button2 = findViewById<Button>(R.id.button2)
        var button3 = findViewById<Button>(R.id.button3)
        var button4 = findViewById<Button>(R.id.button4)
        var button5 = findViewById<Button>(R.id.button5)
        var button6 = findViewById<Button>(R.id.button6)

        button.setOnClickListener{
            setButtonText(button)
        }

        button2.setOnClickListener{
            setButtonText(button2)
        }

        button3.setOnClickListener{
            setButtonText(button3)
        }

        button4.setOnClickListener{
            setButtonText(button4)
        }

        button5.setOnClickListener{
            setButtonText(button5)
        }

        button6.setOnClickListener{
            setButtonText(button6)
        }
    }


    fun setButtonText(btn: Button){
        btn.text = getString(R.string.Purchased)
    }



}
