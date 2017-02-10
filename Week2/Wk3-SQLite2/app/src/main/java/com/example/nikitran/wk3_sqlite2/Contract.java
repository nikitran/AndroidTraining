package com.example.nikitran.wk3_sqlite2;

import android.provider.BaseColumns;

/**
 * Created by nikitran on 2/7/17.
 *
 * Contract class explicitly specifies the layout of the schema is a systematic way
 *
 * A contract class is a container for constants that define names for URIs, tables, and columns.
 * The contract class allows you to use the same constants across all the other classes in the same
 * package.This lets you change a column name in one place and have it propagate throughout your code.
 */

public class Contract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private Contract() {}

    /* Inner class that defines the table contents
     *
     * By implementing the BaseColumns interface, your inner class can inherit a primary key field
     * called _ID that some Android classes such as cursor adaptors will expect it to have.
     */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }
}


