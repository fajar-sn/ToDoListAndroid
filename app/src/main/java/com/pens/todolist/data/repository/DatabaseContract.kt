package com.pens.todolist.data.repository

import android.provider.BaseColumns
import android.provider.BaseColumns._ID

object DatabaseContract {

    class FeedTask : BaseColumns {

        companion object {
            const val TABLE_NAME = "tasks"
            const val COLUMN_TITLE = "title"
            const val COLUMN_DESCRIPTION = "description"
            const val COLUMN_DATE = "date"
            const val COLUMN_USER_ID = "user_id"
            const val COLUMN_IS_DONE = "is_done"
            const val ID = _ID
        }

    }

    class FeedUser : BaseColumns {

        companion object {
            const val TABLE_NAME = "users"
            const val ID = _ID
            const val COLUMN_NAME = "name"
            const val COLUMN_EMAIL = "email"
            const val COLUMN_PASSWORD = "password"
        }

    }

}