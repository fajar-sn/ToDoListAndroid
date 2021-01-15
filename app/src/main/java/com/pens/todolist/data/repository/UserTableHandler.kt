package com.pens.todolist.data.repository

import android.content.ContentValues
import android.content.Context
import com.pens.todolist.data.model.User

class UserTableHandler(context: Context): TableHandler<User> {

    private val databaseHelper = DatabaseHelper(context)

    override fun create(user: User) {
        val db = databaseHelper.writableDatabase

        val values = ContentValues()
        values.put(DatabaseContract.FeedUser.COLUMN_NAME, user.name)
        values.put(DatabaseContract.FeedUser.COLUMN_EMAIL, user.email)
        values.put(DatabaseContract.FeedUser.COLUMN_PASSWORD, user.password)

        db.insert(DatabaseContract.FeedUser.TABLE_NAME, null, values)
    }

    override fun readById(id: String): User {
        val db = databaseHelper.writableDatabase

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(
            DatabaseContract.FeedUser.ID,
            DatabaseContract.FeedUser.COLUMN_NAME,
            DatabaseContract.FeedUser.COLUMN_EMAIL,
            DatabaseContract.FeedUser.COLUMN_PASSWORD,
        )

        // Filter results WHERE "id" = id
        val selection = DatabaseContract.FeedUser.ID + " = ?"
        val selectionArgs = arrayOf(id)

        // How you want the results sorted in the resulting Cursor
        val sortOrder = DatabaseContract.FeedUser.COLUMN_NAME + " DESC"

        val cursor = db.query(
            DatabaseContract.FeedUser.TABLE_NAME,  // The table to query
            projection,  // The array of columns to return (pass null to get all)
            selection,  // The columns for the WHERE clause
            selectionArgs,  // The values for the WHERE clause
            null,  // don't group the rows
            null,  // don't filter by row groups
            sortOrder // The sort order
        )

        cursor?.moveToFirst()

        return User(
            cursor?.getInt(
                cursor.getColumnIndexOrThrow(DatabaseContract.FeedUser.ID)
            ),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3)
        )
    }

    override fun update(user: User) {
        val db = databaseHelper.writableDatabase

        // Set new values
        val values = ContentValues()
        values.put(DatabaseContract.FeedUser.COLUMN_NAME, user.name)
        values.put(DatabaseContract.FeedUser.COLUMN_EMAIL, user.email)
        values.put(DatabaseContract.FeedUser.COLUMN_PASSWORD, user.password)

        // Which row to update, based on the title
        val selection = DatabaseContract.FeedUser.ID + " LIKE ?"
        val selectionArgs = arrayOf(user.id.toString())

        db.update(
            DatabaseContract.FeedUser.TABLE_NAME,
            values,
            selection,
            selectionArgs
        )
    }

    override fun delete(user: User) {
        val db = databaseHelper.writableDatabase
        val selection = DatabaseContract.FeedUser.ID + " LIKE ?"
        val selectionArgs = arrayOf(user.id.toString())
        db.delete(DatabaseContract.FeedUser.TABLE_NAME, selection, selectionArgs)
    }
}