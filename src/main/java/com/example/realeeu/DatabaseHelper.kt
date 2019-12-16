package com.example.realeeu

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {



    companion object {

        val DATABASE_NAME = "Electronic.db"
        val TABLE_NAME = "electronic_table"
        val COL_1 = "ID"
        val COL_2 = "DEVICE"
        val COL_3 = "WATT"
        val COL_4 = "HOUR"
        val COL_5 = "DAY"
        val COL_6 = "TOTAL"
    }


    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_NAME(ID INTEGER PRIMARY KEY AUTOINCREMENT , DEVICE  TEXT , WATT INTEGER , HOUR INTEGER, DAY INTEGER, TOTAL DOUBLE)")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)

    }


    fun intertData(device: String, watt: Int, hour: Int, day: Int): Boolean? {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_2, device)
        cv.put(COL_3, watt)
        cv.put(COL_4, hour)
        cv.put(COL_5, day)
        val res = db.insert(TABLE_NAME , null, cv)
        return !res.equals(-1)
    }


    fun getAllData(): Cursor {

        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }


    fun updateData(id: String, device: String, watt: Int, hour: Int, day: Int): Boolean? {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_1, id)
        cv.put(COL_2, device)
        cv.put(COL_3, watt)
        cv.put(COL_4, hour)
        cv.put(COL_5, day)
        db.update(TABLE_NAME, cv, "ID=?", arrayOf(id))
        return true
    }

    fun daleteData(id: String): Int? {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "ID =? ", arrayOf(id))
    }
    fun calculateData(watt: Int, hour: Int, day: Int): Boolean?{
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_6, (watt/1000)*hour*day)
        val res = db.insert(TABLE_NAME, null, cv)
        return !res.equals(-1)
    }


}