package com.grimlin31.buddywalkowner.LocalDataBase.User;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


class DBHelper extends SQLiteOpenHelper {

    //Database Version
    private static final int databaseVersion = 1;
    //Database Name
    private static final String databaseName = "BuddyWalker.db";

    public DBHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}