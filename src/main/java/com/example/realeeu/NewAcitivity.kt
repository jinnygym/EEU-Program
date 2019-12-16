package com.example.realeeu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_acitivity.*

class NewAcitivity : AppCompatActivity() {
    lateinit var newBtn: Button
    lateinit var viewBtn: Button
    lateinit var updatedBtn: Button
    lateinit var deleteBtn: Button
    lateinit var calculateBtn: Button

    lateinit var editId : EditText
    lateinit var editDevice :EditText
    lateinit var editWatt : EditText
    lateinit var editHour : EditText
    lateinit var editDay : EditText

    lateinit var myDb: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_acitivity)


        newBtn =findViewById(R.id.newBtn)
        viewBtn=findViewById(R.id.viewBtn)
        updatedBtn =findViewById(R.id.updateBtn)
        deleteBtn =findViewById(R.id.deleteBtn)
        calculateBtn = findViewById(R.id.calculateBtn)


        editId =findViewById(R.id.editId)
        editDevice =findViewById(R.id.editDevice)
        editWatt =findViewById(R.id.editWatt)
        editHour =findViewById(R.id.editHour)
        editDay =findViewById(R.id.editDay)

        myDb = DatabaseHelper(this)


        AddData()
        updateData()
        deleteData()
        viewAllData()
        Next()

    }



    fun AddData() {

       newBtn.setOnClickListener(View.OnClickListener {

            val device = editDevice.text.toString().trim()
            val watt = editWatt.text.toString().toInt()
            val hour = editHour.text.toString().toInt()
            val day = editDay.text.toString().toInt()
            val isInserted = myDb.intertData(device, watt, hour, day)
            if (isInserted == true) {
                Toast.makeText(applicationContext, "Data inserted ", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Data could not be inserted ", Toast.LENGTH_SHORT).show()

            }
        })

    }

    fun viewAllData() {

        viewBtn.setOnClickListener(View.OnClickListener {
            val res = myDb.getAllData()

            if (res.getCount() == 0) {
                showMessage("Error ", "Nothing found")
                return@OnClickListener

            } else {
                val buffer = StringBuffer()
                while (res.moveToNext()) {
                    buffer.append("Id:" + res.getString(0) + "\n")
                    buffer.append("Device: " + res.getString(1) + "\n\n")
                    buffer.append("Watt: " + res.getString(2) + "\n\n")
                    buffer.append("Hour: " + res.getString(3) + "\n\n")
                    buffer.append("Day: " + res.getString(4) + "\n\n")

                }

                showMessage("Data", buffer.toString())
            }
        })


    }


    fun deleteData() {
        deleteBtn.setOnClickListener(View.OnClickListener {
            val id = editId.getText().toString().trim()

            val deleterows = myDb.daleteData(id)
            if (deleterows!! > 0 ) {
                Toast.makeText(applicationContext, "Data deleted ", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(applicationContext, "Data could not deleted ", Toast.LENGTH_SHORT).show()

            }
        })

    }

    fun updateData() {

        updatedBtn.setOnClickListener(View.OnClickListener {
            val id = editId.getText().toString().trim()
            val device = editDevice.text.toString().trim()
            val watt = editWatt.text.toString().toInt()
            val hour = editHour.text.toString().toInt()
            val day = editDay.text.toString().toInt()



            val isUpdated = myDb.updateData(id, device, watt, hour, day)
            if (isUpdated == true) {
                Toast.makeText(applicationContext, "Data updated ", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Data could not be updated ", Toast.LENGTH_SHORT).show()

            }
        })


    }
    fun Next() {

        val mNext = findViewById<Button>(R.id.calculateBtn)
        mNext.setOnClickListener{
            startActivity(Intent(this@NewAcitivity, LastActivity::class.java))

        }
    }

    private fun showMessage(title: String, message: String?) {

        val builder = AlertDialog.Builder(this)
        builder.create()
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.show()
    }


}



