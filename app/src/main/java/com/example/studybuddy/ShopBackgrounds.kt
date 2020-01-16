package com.example.studybuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class ShopBackgrounds : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_backgrounds)

        //Read the sharedPreferences money variable
        var prefs = getSharedPreferences("Main", 0)

        Log.i("Money", prefs.getInt("Money", 0).toString())

        var button = findViewById<Button>(R.id.buyBackgroundButton)
        var button2 = findViewById<Button>(R.id.buyBackgroundButton2)
        var button3 = findViewById<Button>(R.id.buyBackgroundButton3)
        var button4 = findViewById<Button>(R.id.buyBackgroundButton4)
        var button5 = findViewById<Button>(R.id.buyBackgroundButton5)
        var button6 = findViewById<Button>(R.id.buyBackgroundButton6)

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
        btn.text = getString(R.string.purchased)
    }


}
