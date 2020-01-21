package com.example.studybuddy

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import java.security.AccessControlContext

class HallOfFame : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hall_of_fame)

        setBackground()

        val mainPrefs = getSharedPreferences("Main", 0)
        var listView = findViewById<ListView>(R.id.listView)


        var customAdapter = CustomAdapter(this)
        listView.adapter = customAdapter



    }


    fun setBackground(){
        val mainPrefs = getSharedPreferences("Main", 0)
        val backgroundsMap = mapOf("background1" to R.drawable.background1, "background2" to R.drawable.background2, "background3" to R.drawable.background3, "background4" to R.drawable.background4, "background5" to R.drawable.background5, "background6" to R.drawable.background6)
        findViewById<ConstraintLayout>(R.id.HallOfFameCL).setBackgroundResource(backgroundsMap.get(mainPrefs.getString("ActiveBackground", "background1"))!!) //give ID to layout in XML
    }
}

class CustomAdapter(private val context: Activity): BaseAdapter() {

    var names = arrayOf("Frederick", "Lisa", "Tom", "Hubert", "Michelle", "Elke", "Nina", "Florian")
    var studyBuddys = intArrayOf(R.drawable.buddy1_pot1, R.drawable.buddy3_pot1, R.drawable.buddy2_pot4, R.drawable.buddy1_pot6, R.drawable.buddy1_pot1, R.drawable.buddy3_pot1, R.drawable.buddy2_pot4, R.drawable.buddy1_pot6)


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var inflater = context.layoutInflater
        var view1 = inflater.inflate(R.layout.row_data, null)

        //Find view in row_data
        var name = view1.findViewById<TextView>(R.id.HOFName)
        var studyBuddy = view1.findViewById<ImageView>(R.id.HOFImage)

        name.setText(names[position])
        studyBuddy.setImageResource(studyBuddys[position])

        return view1
    }

    override fun getItem(position: Int): Any {
        return studyBuddys[position]
    }

    override fun getItemId(position: Int): Long {
        return studyBuddys[position].toLong()
    }

    override fun getCount(): Int {
        return studyBuddys.size
    }
}