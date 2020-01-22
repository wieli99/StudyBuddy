package com.wieliFritz.studybuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

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

        setBackground()
    }


    fun setBackground(){
        val mainPrefs = getSharedPreferences("Main", 0)
        val backgroundsMap = mapOf("background1" to R.drawable.background1, "background2" to R.drawable.background2, "background3" to R.drawable.background3, "background4" to R.drawable.background4, "background5" to R.drawable.background5, "background6" to R.drawable.background6)
        findViewById<ConstraintLayout>(R.id.shopCL).setBackgroundResource(backgroundsMap.get(mainPrefs.getString("ActiveBackground", "background1"))!!) //give ID to layout in XML
    }
}
