package com.javaorigin.test.apk;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Noor Zia on 5/26/2015.
 */
//for whole contact
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "List";
    //private static final String TABLE_NAME = "Lists";

    // Contacts table name
    //private static final String TABLE_CONTACTS = "contacts";

    // Contacts Table Columns names
    private static final String TableName="Contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NUMBER = "phone_number";

    public String CREATE_QUERY = "CREATE TABLE"+TableName+"("+KEY_ID+"TEXT,"+KEY_NAME+"TEXT,"+KEY_NUMBER+"TEXT};";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DATABASE OPERATIONS","DATABASE CREATED");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TableName + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_NUMBER + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    public void DeleteList(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS" + tableName);

    }
    public void DeleteContactFromList(String tableName , String Number){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, KEY_ID + " = ?",
                new String[] { Number });
        db.close();
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TableName);

        // Create tables again
        onCreate(db);
    }
    void addContact(TableData contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_NUMBER, contact.getPhoneNumber()); // Contact Phone
        // Inserting Row
        db.insert(TableName, null, values);
        db.close(); // Closing database connection
    }
    void addContact(Anonymous contact ){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_NUMBER, contact.getPhoneNumber()); // Contact Phone

        // Inserting Row
        db.insert(TableName, null, values);
        db.close();

    }
    public boolean isContact(String number){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TableName, new String[] { KEY_ID,
                        KEY_NAME, KEY_NUMBER}, KEY_ID + "=?",
                new String[] { number }, null, null, null, null);
        if (cursor != null)
            return true;
        else
            return false;


    }

}
