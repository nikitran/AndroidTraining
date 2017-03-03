package com.example.nikitran.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by nikitran on 2/24/17.
 */

public class MovieProvider extends ContentProvider {

    private static final int GENRE = 100;
    private static final int GENRE_ID = 101;
    private static final int MOVIE = 200;
    private static final int MOVIE_ID = 201;

    private static final UriMatcher mUriMatcher = buildUriMatcher();
    private MovieDBHelper mOpenHelper;

    private static UriMatcher buildUriMatcher() {
        String content = MovieContract.CONTENT_AUTHORITY;

        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(content, MovieContract.PATH_GENRE, GENRE);
        matcher.addURI(content, MovieContract.PATH_GENRE + "/#", MOVIE_ID);
        matcher.addURI(content, MovieContract.PATH_MOVIE, MOVIE);
        matcher.addURI(content, MovieContract.PATH_MOVIE + "/#",  MOVIE_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new MovieDBHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        Cursor returnCursor;

        long _id;
        switch (mUriMatcher.match(uri)){
            case GENRE:
                returnCursor = db.query(
                        MovieContract.GenreEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case GENRE_ID:
                _id = ContentUris.parseId(uri);
                returnCursor = db.query(
                        MovieContract.GenreEntry.TABLE_NAME,
                        projection,
                        MovieContract.GenreEntry._ID + " =?",
                        new String[] {String.valueOf(_id)},
                        null,
                        null,
                        sortOrder
                );
                break;
            case MOVIE:
                returnCursor = db.query(
                        MovieContract.MovieEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case MOVIE_ID:
                _id = ContentUris.parseId(uri);
                returnCursor = db.query(
                        MovieContract.MovieEntry.TABLE_NAME,
                        projection,
                        MovieContract.MovieEntry._ID + " =?",
                        new String[] {String.valueOf(_id)},
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }

        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return returnCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch(mUriMatcher.match(uri)) {
            case GENRE :
                return MovieContract.GenreEntry.CONTENT_TYPE;
            case GENRE_ID :
                return MovieContract.GenreEntry.CONTENT_ITEM_TYPE;
            case MOVIE :
                return MovieContract.GenreEntry.CONTENT_TYPE;
            case MOVIE_ID :
                return MovieContract.MovieEntry.CONTENT_ITEM_TYPE;
            default :
                throw new UnsupportedOperationException("Unknown URI: " + uri);

        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        long _id;
        Uri returnUri;

        switch (mUriMatcher.match(uri)) {
            case GENRE:
                _id = db.insert(MovieContract.GenreEntry.TABLE_NAME, null, values);
                if(_id > 0) {
                    returnUri = MovieContract.GenreEntry.buildMovieUri(_id);
                }else{
                    throw new UnsupportedOperationException("Unable to insert rows into" + uri);
                }
                break;
            case MOVIE:
                _id = db.insert(MovieContract.MovieEntry.TABLE_NAME, null, values);
                if(_id > 0) {
                    returnUri = MovieContract.MovieEntry.buildMovieUri(_id);
                }else{
                    throw new UnsupportedOperationException("Unable to insert rows into" + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);

        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int rows;

        switch (mUriMatcher.match(uri)){
            case GENRE:
                rows = db.delete(MovieContract.GenreEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case MOVIE:
                rows = db.delete(MovieContract.MovieEntry.TABLE_NAME, selection,selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri" + uri);
        }
        if(rows != 0 || selection == null){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int rows;

        switch (mUriMatcher.match(uri)){
            case GENRE:
                rows = db.update(MovieContract.GenreEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case MOVIE:
                rows = db.update(MovieContract.MovieEntry.TABLE_NAME, values, selection,selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri" + uri);
        }
        if(rows != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rows;
    }
}
