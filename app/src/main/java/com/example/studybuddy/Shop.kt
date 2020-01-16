package com.example.studybuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Shop : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        var shopPotsBtn = findViewById<Button>(R.id.shopPotsButton)
        //To Navigate To Shop
        shopPotsBtn.setOnClickListener{
            val intent = Intent(this, ShopPots::class.java)
            startActivity(intent)
        }

        var shopBackgroundsBtn = findViewById<Button>(R.id.shopBackgroundsButton)
        //To Navigate To Shop
        shopBackgroundsBtn.setOnClickListener{
            val intent = Intent(this, ShopBackgrounds::class.java)
            startActivity(intent)
        }
    }
}
