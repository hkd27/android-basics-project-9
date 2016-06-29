package com.hemantdave.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by INDIA on 6/28/2016.
 */
public class DbHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_USERACTIVITY = "contactsManager";
    // User Activites.. Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_ACTION = "Action";
    private static final String KEY_TIME = "time";
    private static  final String TABLE_NAME="userBehaviour";
    Context context;
    public DbHandler(Context context) {

        super(context, DATABASE_USERACTIVITY, null, DATABASE_VERSION);
        this.context=context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE userBehaviour ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "time TEXT, " + "Action TEXT )";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    //for deleting all entries
    public void deleteAllEntries(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }
    //deleting database
    public void deleteDatabase(){

        context.deleteDatabase(DATABASE_USERACTIVITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS userBehaviour" );

        // Create tables again
        onCreate(db);
    }

    // Adding new User Activites..
    void addActivity(UserBehaviourPojo useractivity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TIME, useractivity.getTimeOfAction());
        values.put(KEY_ACTION, useractivity.getAction());
         // Contact Phone

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }



    // Getting All User Activites..
    public List<UserBehaviourPojo> getUserActivites() {
        List<UserBehaviourPojo> getAllData = new ArrayList<UserBehaviourPojo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserBehaviourPojo content = new UserBehaviourPojo();
                content.setId(Integer.parseInt(cursor.getString(0)));
                content.setTimeOfAction(cursor.getString(1));
                content.setAction(cursor.getString(2));
                // Adding User Activites.. to list
                getAllData.add(content);
            } while (cursor.moveToNext());
        }

        // return User Activites list
        return getAllData;
    }



    // Updating single User Activites..
    public int updateUserActivity(UserBehaviourPojo content) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TIME, content.getTimeOfAction());
        values.put(KEY_ACTION, content.getAction());

        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[] { String.valueOf(content.getId()) });
    }




}
