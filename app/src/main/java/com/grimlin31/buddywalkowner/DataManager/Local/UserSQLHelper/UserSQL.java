package com.grimlin31.buddywalkowner.DataManager.Local.UserSQLHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.grimlin31.buddywalkowner.DataManager.Local.LocalDatabaseHelper;

public class UserSQL extends SQLiteOpenHelper {

    public UserSQL(Context context) {
        super(
            context,
            LocalDatabaseHelper.databaseName,
            null,
            LocalDatabaseHelper.databaseVersion
        );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UserContractsDataBase.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(UserContractsDataBase.SQL_DELETE);
        onCreate(db);
    }

}
