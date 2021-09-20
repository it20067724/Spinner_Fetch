package com.example.spinner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 6;

    private static final String DATABASE_NAME = "spinnerExample";
    private static final String TABLE_NAME = "labels";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    // Database Name


    // Labels table name
    private static final String TABLE_LABELS = "labels";

    // Labels Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NAME2 = "lname";


    private static final String COLUMN_NAME_ROUTE_ID = "route_id";
    private static final String COLUMN_NAME_START_LOCATION = "start_location";
    private static final String COLUMN_NAME_END_LOCATION = "end_location";
    private static final String COLUMN_NAME_DISTANCE = "distance";
    private static final String COLUMN_NAME_CREATED_DATE = "created_date";
    private static final String COLUMN_NAME_MODIFIED_DATE = "modified_date";
    private static final String COLUMN_NAME_IS_DEFAULT = "is_default";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Category table create query
        String CREATE_ITEM_TABLE = "CREATE TABLE "
                + TABLE_NAME +
                " ("
                + COLUMN_NAME_ROUTE_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME_START_LOCATION +
                " TEXT, "
                + COLUMN_NAME_END_LOCATION +
                " TEXT, "
                + COLUMN_NAME_DISTANCE +
                " REAL, "
                + COLUMN_NAME_CREATED_DATE +
                " TEXT, "
                + COLUMN_NAME_MODIFIED_DATE +
                " TEXT, "
                + COLUMN_NAME_IS_DEFAULT +
                " INTEGER" + ")";

        db.execSQL(CREATE_ITEM_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    /**
     * Inserting new lable into lables table
     * */
    public void insertLabel(String label){
        SQLiteDatabase db = this.getWritableDatabase();
        Double distance= 10.50;
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_START_LOCATION, label);
        values.put(COLUMN_NAME_END_LOCATION, "Colombo");
        values.put(COLUMN_NAME_DISTANCE,distance);//column name, column value

        // Inserting Row
        db.insert(TABLE_NAME, null, values);//tableName, nullColumnHack, CotentValues
        db.close(); // Closing database connection
    }

    /**
     * Getting all labels
     * returns list of labels
     * */
    public List<String> getAllLabels(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }

    public List<String> getstartStoplocation(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1)+" - "+cursor.getString(2));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }

    public List<String> getroutelist(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }

    public String getRoutedetails(String rid){

        String locdata = null;

        // Select All Query
        String selection = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME_ROUTE_ID+" =?";
        String[] selectionArgs = {rid};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selection, selectionArgs);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                locdata=cursor.getString(1)+" - "+cursor.getString(2);//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return locdata;
    }
}