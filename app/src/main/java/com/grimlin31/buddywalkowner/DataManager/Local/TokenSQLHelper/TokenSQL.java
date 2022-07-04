package com.grimlin31.buddywalkowner.DataManager.Local.TokenSQLHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.grimlin31.buddywalkowner.DataManager.Local.LocalDatabaseHelper;

public class TokenSQL extends LocalDatabaseHelper {

    public TokenSQL(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        super.onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }
}
