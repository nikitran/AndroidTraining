package com.example.nikitran.wk2_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nikitran on 1/30/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;                       //database version
    private static final String DATABASE_NAME = "contactManager";        //Database Name
    private static final String TABLE_NAME_CONTACTS = "contacts";        //table name

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";

    private String CREATE_CONTACTS_TABLE;
    private String SQL_DELETE_ENTRIES;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PHONE + " TEXT" + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME_CONTACTS;

        db.execSQL(SQL_DELETE_ENTRIES);                             //drop older table if existed
        onCreate(db);                                               //create tables again;
    }

    //calling onDowngrade is not required
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);

    }

    //-------------------------------- Adding a new contact --------------------------------
    // insert()
    // 1st argument: table name
    // 2nd argument: tells the framework what to do in the event that the ContentValues is empty
    // 3rd arguement: ContentValues
    //
    // If you specify the name of a column, the framework inserts a row and sets the value of that
    // column to null. If you specify null, like in this code sample, the framework does not insert
    // a row when there are no values.
    //---------------------------------------------------------------------------------------
    public void addContact(Contact contact) {
        // 1. Gets the data repository in write mode:
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. insert data into the ContentValue object
        // by creating a new map of values, where column names are the keys
        ContentValues entry = new ContentValues();
        entry.put(KEY_NAME, contact.getName());        //contact name
        entry.put(KEY_PHONE, contact.getPhone());      //contact phone

        // 3. pass the ContentValue object into the insert()
        db.insert(TABLE_NAME_CONTACTS, null, entry);
        db.close();
    }

    // -------------------------------- reading contacts from the database-------------------
    //---------------------------------------------------------------------------------------
    // public Contact getContact(int id){
    //    SQLiteDatabase db = this.getReadableDatabase();

    //Cursor cursor = db.query(TABLE_CONTACTS, newString[]{KEY_ID, Cursor KEY_NAME, KEY_PHONE}, KEY_ID + "=?", )
    //}
}
