package demo.dev.myapplication

import android.content.ContentProvider
import android.content.ContentValues
import android.content.SharedPreferences
import android.database.Cursor
import android.net.Uri

class MyContentProvider : ContentProvider() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(): Boolean {
        TODO("Not yet implemented")
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        // Implement query logic to retrieve data from SharedPreferences
        // We can use the uri and projection to determine what data to fetch
        // and return the results as a Cursor
        // ...
        return null
    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }
}

//from another app

/**
 * val uri = Uri.parse("content://com.rommansabbir.cpexample.package.mycontentprovider/some_data")
 * val projection = arrayOf("key1", "key2")
 *
 * val cursor = contentResolver.query(uri, projection, null, null, null)
 *
 * if (cursor != null && cursor.moveToFirst()) {
 *     val key1Value = cursor.getString(cursor.getColumnIndex("key1"))
 *     val key2Value = cursor.getInt(cursor.getColumnIndex("key2"))
 *     // Use the retrieved data in the other app
 *     cursor.close()
 * }
 *  */

//store data from another app

/**
 * val uri = Uri.parse("content://com.your.app.package.mycontentprovider/some_data")
 * val values = ContentValues().apply {
 *     put("key1", "Some value")
 *     put("key2", 42)
 * }
 *
 * val insertedUri = contentResolver.insert(uri, values)
 *  */