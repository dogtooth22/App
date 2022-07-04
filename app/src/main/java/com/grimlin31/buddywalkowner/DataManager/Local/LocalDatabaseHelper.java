package com.grimlin31.buddywalkowner.DataManager.Local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.grimlin31.buddywalkowner.DataManager.Local.TokenSQLHelper.TokenContactDataBase;
import com.grimlin31.buddywalkowner.DataManager.Local.UserSQLHelper.UserContractsDataBase;


public class LocalDatabaseHelper extends SQLiteOpenHelper{

    //Database Version
    public static final int databaseVersion = 2;
    //Database Name
    public static final String databaseName = "BuddyWalker.db";

    public LocalDatabaseHelper(Context context) {
        super(
                context,
                LocalDatabaseHelper.databaseName,
                null,
                LocalDatabaseHelper.databaseVersion
        );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TokenContactDataBase.SQL_CREATE);
        sqLiteDatabase.execSQL(UserContractsDataBase.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(TokenContactDataBase.SQL_DELETE);
        sqLiteDatabase.execSQL(UserContractsDataBase.SQL_DELETE);
        onCreate(sqLiteDatabase);
    }
}