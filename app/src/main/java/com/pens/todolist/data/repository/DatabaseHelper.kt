package com.pens.todolist.data.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(
    context: Context?
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_USER)
        db?.execSQL(SQL_CREATE_TASK)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_TASK)
        db?.execSQL(SQL_DELETE_USER)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "Todolist.db"
        val SQL_CREATE_USER =
            "CREATE TABLE " + DatabaseContract.FeedUser.TABLE_NAME + " (" +
                DatabaseContract.FeedUser.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseContract.FeedUser.COLUMN_NAME + " TEXT," +
                DatabaseContract.FeedUser.COLUMN_EMAIL + " TEXT," +
                DatabaseContract.FeedUser.COLUMN_PASSWORD + " TEXT)"

        val SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + DatabaseContract.FeedUser.TABLE_NAME

        val SQL_CREATE_TASK =
            "CREATE TABLE " + DatabaseContract.FeedTask.TABLE_NAME + " (" +
                DatabaseContract.FeedTask.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseContract.FeedTask.COLUMN_TITLE + " TEXT, " +
                DatabaseContract.FeedTask.COLUMN_DESCRIPTION + " TEXT, " +
                DatabaseContract.FeedTask.COLUMN_DATE + " TEXT, " +
                DatabaseContract.FeedTask.COLUMN_IS_DONE + " INTEGER, " +
                DatabaseContract.FeedTask.COLUMN_USER_ID + " INTEGER, " +
                "CONSTRAINT fk_tasks " +
                    "FOREIGN KEY (" + DatabaseContract.FeedTask.COLUMN_USER_ID + ") " +
                    "REFERENCES " + DatabaseContract.FeedUser.TABLE_NAME + "(" + DatabaseContract.FeedUser.ID + ")" +
            ")"
        val SQL_DELETE_TASK =
            "DROP TABLE IF EXISTS " + DatabaseContract.FeedTask.TABLE_NAME
    }

}