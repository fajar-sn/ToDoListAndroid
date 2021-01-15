package com.pens.todolist.data.repository

import android.content.ContentValues
import android.content.Context
import com.pens.todolist.data.model.Task

class TaskTableHandler(context: Context?): TableHandler<Task> {

    private val databaseHelper = DatabaseHelper(context)

    override fun create(task: Task) {
        val db = databaseHelper.writableDatabase

        val values = ContentValues()
        values.put(DatabaseContract.FeedTask.COLUMN_TITLE, task.title)
        values.put(DatabaseContract.FeedTask.COLUMN_DESCRIPTION, task.description)
        values.put(DatabaseContract.FeedTask.COLUMN_DATE, task.date)
        values.put(DatabaseContract.FeedTask.COLUMN_IS_DONE, task.isDone)
        values.put(DatabaseContract.FeedTask.COLUMN_USER_ID, task.userId)

        db.insert(DatabaseContract.FeedTask.TABLE_NAME, null, values)
    }

    override fun readById(id: String): Task {
        val db = databaseHelper.writableDatabase

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(
            DatabaseContract.FeedTask.ID,
            DatabaseContract.FeedTask.COLUMN_TITLE,
            DatabaseContract.FeedTask.COLUMN_DESCRIPTION,
            DatabaseContract.FeedTask.COLUMN_DATE,
            DatabaseContract.FeedTask.COLUMN_IS_DONE,
            DatabaseContract.FeedTask.COLUMN_USER_ID
        )

        // Filter results WHERE "id" = id
        val selection = DatabaseContract.FeedTask.ID + " = ?"
        val selectionArgs = arrayOf(id)

        // How you want the results sorted in the resulting Cursor
        val sortOrder = DatabaseContract.FeedTask.COLUMN_TITLE + " DESC"

        val cursor = db.query(
            DatabaseContract.FeedTask.TABLE_NAME,  // The table to query
            projection,  // The array of columns to return (pass null to get all)
            selection,  // The columns for the WHERE clause
            selectionArgs,  // The values for the WHERE clause
            null,  // don't group the rows
            null,  // don't filter by row groups
            sortOrder // The sort order
        )

        cursor?.moveToFirst()

        return Task(
            cursor?.getInt(
                cursor.getColumnIndexOrThrow(DatabaseContract.FeedTask.ID)
            ),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3),
            cursor.getInt(4),
            cursor.getInt(5)
        )
    }

    fun readAll(): List<Task> {
        val taskList = ArrayList<Task>()

        val selectQuery = "SELECT  * FROM " + DatabaseContract.FeedTask.TABLE_NAME

        val db = databaseHelper.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val task = Task(
                    cursor?.getInt(
                        cursor.getColumnIndexOrThrow(DatabaseContract.FeedTask.ID)
                    ),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                )

                taskList.add(task)
            } while (cursor.moveToNext())
        }

        return taskList

    }

    override fun update(task: Task) {
        val db = databaseHelper.writableDatabase

        // Set new values
        val values = ContentValues()
        values.put(DatabaseContract.FeedTask.COLUMN_TITLE, task.title)
        values.put(DatabaseContract.FeedTask.COLUMN_DESCRIPTION, task.description)
        values.put(DatabaseContract.FeedTask.COLUMN_DATE, task.date)
        values.put(DatabaseContract.FeedTask.COLUMN_IS_DONE, task.isDone)
        values.put(DatabaseContract.FeedTask.COLUMN_USER_ID, task.userId)

        // Which row to update, based on the title
        val selection = DatabaseContract.FeedTask.ID + " LIKE ?"
        val selectionArgs = arrayOf(task.id.toString())

        db.update(
            DatabaseContract.FeedTask.TABLE_NAME,
            values,
            selection,
            selectionArgs
        )
    }

    override fun delete(task: Task) {
        val db = databaseHelper.writableDatabase
        val selection = DatabaseContract.FeedTask.ID + " LIKE ?"
        val selectionArgs = arrayOf(task.id.toString())
        db.delete(DatabaseContract.FeedTask.TABLE_NAME, selection, selectionArgs)
    }
}