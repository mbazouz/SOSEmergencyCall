package com.example.mohamed.sosemergencycall;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mohamed on 05/12/2016.
 */

public class CountriesDatabase extends SQLiteOpenHelper {

    private static final String TABLE_COUNTRIES = "table_countries";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "Name";
    private static final String COL_POLICE = "Police";
    private static final String COL_AMBULANCE = "Ambulance";
    private static final String COL_FIRE = "Fire";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_COUNTRIES + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT NOT NULL, "
            + COL_POLICE + " INTEGER NOT NULL, " + COL_AMBULANCE + " INTEGER NOT NULL, "
            + COL_FIRE + " INTEGER NOT NULL" +
            ");";

    public CountriesDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // db.execSQL("DROP TABLE " + TABLE_COUNTRIES + ";");
         //onCreate(db);
    }

}
