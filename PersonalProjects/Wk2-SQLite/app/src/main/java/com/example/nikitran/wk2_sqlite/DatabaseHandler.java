package com.example.nikitran.wk2_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nikitran on 1/30/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    //database version
    private static final int DATABASE_VERSION = 1;
    //Database name
    private static final String DATABASE_NAME = "contactManager";
    //table name
    private static final String TABLE_CONTACTS = "contacts";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PHONE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        //create tables again;
        onCreate(db);
    }

    // adding a new contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());        //contact name
        values.put(KEY_PHONE, contact.getPhone());      //contact phone

        //insert row
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    // readding contacts from the database
    // public Contact getContact(int id){
    //    SQLiteDatabase db = this.getReadableDatabase();

    //Cursor cursor = db.query(TABLE_CONTACTS, newString[]{KEY_ID, Cursor KEY_NAME, KEY_PHONE}, KEY_ID + "=?", )
    //}
}
