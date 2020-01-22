package com.example.studybuddy

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout


class HallOfFame : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hall_of_fame)

        setBackground()

        val hallOfFameBtn = findViewById<Button>(R.id.buttonSaveToHOF)
        hallOfFameBtn.setOnClickListener{
            AlertDialog.Builder(this@HallOfFame)
                .setTitle(getString(R.string.alert_hof_title))
                .setMessage(getString(R.string.alert_hof_text)) // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes,
                    DialogInterface.OnClickListener { _, _ ->
                        saveToHallOfFame()
                        var listView = findViewById<ListView>(R.id.listView)

                        val names = generateNamesArray(this@HallOfFame.getSharedPreferences("HOF", Context.MODE_PRIVATE))
                        val buddys = generateBuddyArray(this@HallOfFame.getSharedPreferences("HOF", Context.MODE_PRIVATE))

                        var customAdapter = CustomAdapter(this, names, buddys)
                        listView.adapter = customAdapter
                    }) // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        }


        val names = generateNamesArray(this@HallOfFame.getSharedPreferences("HOF", Context.MODE_PRIVATE))
        val buddys = generateBuddyArray(this@HallOfFame.getSharedPreferences("HOF", Context.MODE_PRIVATE))


        var listView = findViewById<ListView>(R.id.listView)


        var customAdapter = CustomAdapter(this, names, buddys)
        listView.adapter = customAdapter



    }


    fun setBackground(){
        val mainPrefs = getSharedPreferences("Main", 0)
        val backgroundsMap = mapOf("background1" to R.drawable.background1, "background2" to R.drawable.background2, "background3" to R.drawable.background3, "background4" to R.drawable.background4, "background5" to R.drawable.background5, "background6" to R.drawable.background6)
        findViewById<ConstraintLayout>(R.id.HallOfFameCL).setBackgroundResource(backgroundsMap.get(mainPrefs.getString("ActiveBackground", "background1"))!!) //give ID to layout in XML
    }


    fun saveToHallOfFame(){
        val sharedPref = this@HallOfFame.getSharedPreferences("HOF", Context.MODE_PRIVATE)
        val mainPrefs = getSharedPreferences("Main", 0)

        with (sharedPref.edit()) {
            putString("Buddys", sharedPref.getString("Buddys", "") + mainPrefs.getString("StudyBuddy", "buddy1") + mainPrefs.getString("ActivePot", "pot1") + "_" + mainPrefs.getString("Name", "Frederick") + ";") //Adds StudyBuddy and name to 'list'
            apply()
        }

        with(mainPrefs.edit()) {
            putInt("HOFEntries", mainPrefs.getInt("HOFEntries", 0)+1)
            apply()
        }
        resetStudyBuddy(mainPrefs)
    }


    fun resetStudyBuddy(mainPrefs: SharedPreferences){
        with(mainPrefs.edit()) {
            putString("StudyBuddy", "buddy1")
            putString("Name", "Frederick")
            apply()
        }
    }

    fun generateNamesArray(sharedPref: SharedPreferences): ArrayList<String>{
        var sharedPrefList = sharedPref.getString("Buddys", "")!!.split((";"))
        Log.i("HOF Entries", sharedPrefList.toString())
        var names = arrayListOf<String>()
        if (sharedPrefList[0] != ""){
            for (i in 0 until sharedPrefList.size-1){
                Log.i("NameEntry", sharedPrefList[i].split("_")[1])
                names.add(sharedPrefList[i].split("_")[1])
            }
        } else{
            return arrayListOf("")
        }
        Log.i("Names", names.toString())
        return names
    }


    fun generateBuddyArray(sharedPref: SharedPreferences): ArrayList<Int>{
        var sharedPrefList = sharedPref.getString("Buddys", "")!!.split((";"))
        Log.i("HOF Entries", sharedPrefList.toString())
        var buddysNames = arrayListOf<String>()
        if (sharedPrefList[0] != ""){
            for (i in 0 until sharedPrefList.size-1){
                Log.i("BuddyEntry", sharedPrefList[i].split("_")[0])
                buddysNames.add(sharedPrefList[i].split("_")[0])
            }
        } else{
            return arrayListOf()
        }
        Log.i("Buddys", buddysNames.toString())
        return getAllHOFStudyBuddys(buddysNames)
    }


    fun getAllHOFStudyBuddys(buddysNames: ArrayList<String>): ArrayList<Int>{
        val studyBuddyMap = mapOf("buddy1pot1" to R.drawable.buddy1_pot1, "buddy1pot2" to R.drawable.buddy1_pot2, "buddy1pot3" to R.drawable.buddy1_pot3, "buddy1pot4" to R.drawable.buddy1_pot4, "buddy1pot5" to R.drawable.buddy1_pot5, "buddy1pot6" to R.drawable.buddy1_pot6,
            "buddy2pot1" to R.drawable.buddy2_pot1, "buddy2pot2" to R.drawable.buddy2_pot2, "buddy2pot3" to R.drawable.buddy2_pot3, "buddy2pot4" to R.drawable.buddy2_pot4, "buddy2pot5" to R.drawable.buddy2_pot5, "buddy2pot6" to R.drawable.buddy2_pot6,
            "buddy3pot1" to R.drawable.buddy3_pot1, "buddy3pot2" to R.drawable.buddy3_pot2, "buddy3pot3" to R.drawable.buddy3_pot3, "buddy3pot4" to R.drawable.buddy3_pot4, "buddy3pot5" to R.drawable.buddy3_pot5, "buddy3pot6" to R.drawable.buddy3_pot6)

        var buddys = arrayListOf<Int>()
        for (buddy in buddysNames){
            buddys.add(studyBuddyMap.get(buddy)!!)
        }
        Log.i("BuddysObjects", buddys.toString())
        return buddys
    }

}




class CustomAdapter(private val context: Activity, namesList: ArrayList<String>, studyBuddysList: ArrayList<Int>): BaseAdapter() {

    var names = namesList
    var studyBuddys = studyBuddysList

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