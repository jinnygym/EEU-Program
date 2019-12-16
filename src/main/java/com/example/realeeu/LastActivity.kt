package com.example.realeeu

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_last.*
import java.util.ArrayList

class LastActivity : AppCompatActivity() {
    lateinit var myDb: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_last)
        val listView = findViewById(R.id.recipe_list_view) as ListView
        myDb = DatabaseHelper(this)
        val theList = ArrayList<String?>()
        val res = myDb.getAllData()
        if (res.count == 0) {
            Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show()
        } else {
            while (res.moveToNext()) {
                theList.add("Device :" + res.getString(1))
                theList.add("Watt:" + res.getString(2))
                theList.add("Hour :" + res.getString(3))
                theList.add("Day :" + res.getString(4))
                val listAdapter: ListAdapter =
                    ArrayAdapter<Any?>(this, android.R.layout.simple_list_item_1,
                        theList as List<Any?>
                    )
                listView.adapter = listAdapter
            }
        }

    }
}
