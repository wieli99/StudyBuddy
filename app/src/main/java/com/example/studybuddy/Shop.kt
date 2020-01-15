package com.example.studybuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Shop : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        var shopBtn = findViewById<Button>(R.id.ShopPots)
        //To Navigate To Shop
        shopBtn.setOnClickListener{
            val intent = Intent(this, ShopPots::class.java)
            startActivity(intent)
        }
    }
}
