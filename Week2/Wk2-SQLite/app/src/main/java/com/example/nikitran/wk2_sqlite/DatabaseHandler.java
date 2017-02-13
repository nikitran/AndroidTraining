package com.example.nikitran.wk2_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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
     public Contact getContact(int id){
        // 1. get the data repository in Read mode:
        SQLiteDatabase db = this.getReadableDatabase();

         // 2. Define a projection that specifies which columns from the database you will actually
         // use after this query.
         String[] projection = {
                 KEY_ID,
                 KEY_NAME,
                 KEY_PHONE
         };

         // 3. Filter results WHERE "title" = 'My Title'
         String selection = KEY_ID + " = ?";

         // 4. The values for the WHERE clause
         String[] selectionArgs = { String.valueOf(id) };

         // 5. How you want the results sorted in the resulting Cursor
         String sortOrder = null;

        // 6. fine the row
         Cursor cursor = db.query(
                 TABLE_NAME_CONTACTS,
                 projection,
                 selection,
                 selectionArgs,
                 null,
                 null,
                 null);

         if(cursor != null)
            cursor.moveToFirst();

         Contact contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

            // return contact
            return contact;

     }

    // ----------------------------------Getting All Contacts---------------------------------------

    public List<Contact> getAllContacts() {
        // Create and array for contacts
        List<Contact> contactList = new ArrayList<Contact>();

        // 1. get the db in write mode:
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_CONTACTS;

        // 3. find the row
        Cursor cursor = db.rawQuery(
                selectQuery,
                null
        );

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact= new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhone(cursor.getString(2));

                // Adding contact to list
                contactList.add(contact);
                } while (cursor.moveToNext());      //more to next row
            }

        // return contact list
        return contactList;
    }

    //----------------------------------Getting contacts Count-------------------------------------
    public int getContactsCount() {

        SQLiteDatabase db = this.getReadableDatabase();

        // select all
        String countQuery = "SELECT  * FROM " + TABLE_NAME_CONTACTS;

        Cursor cursor = db.rawQuery(
                countQuery,
                null
        );

        cursor.close();

        // return count by calling get count
        return cursor.getCount();
    }

    //----------------------------------Updating single contact------------------------------------
    public int updateContact(Contact contact) {

        // 1. writable mode
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. New value for one column
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PHONE, contact.getPhone());

        // Which row to update, based on the title
        String selection =  KEY_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(contact.getID()) };

        // updating row
        return db.update(
                TABLE_NAME_CONTACTS,
                values,
                selection,
                selectionArgs
        );
    }

    //-------------------------------Deleting single contact---------------------------------------
    public void deleteContact(Contact contact) {
        // 1. writable
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. Define 'where' part of query.
        String selection = KEY_ID + " LIKE ?";

        // 3. Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(contact.getID()) };

        db.delete(
                TABLE_NAME_CONTACTS,
                selection,
                selectionArgs
        );
        db.close();
    }
}
