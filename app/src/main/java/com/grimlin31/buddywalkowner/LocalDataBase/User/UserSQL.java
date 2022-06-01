package com.grimlin31.buddywalkowner.LocalDataBase.User;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class UserSQL extends DBHelper {

    public UserSQL(Context context){
        super(context);
    }

    //Users Table Name
    private final String tableName = "user";
    //User Table Columns
    private final String columnId = "id";
    private final String columnEmail = "email";
    private final String columnPassword = "password";
    private final String columnUserName = "username";

    private final String columnAddress = "address";
    private final String columnTypePet = "type_pet";
    private final String columnName = "name";
    private final String columnPhone = "phone";


    public String setTable() {
        return "CREATE TABLE " + this.tableName + "(" +
                this.columnId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                this.columnUserName + " TEXT, " +
                this.columnEmail + " TEXT, " +
                this.columnPassword + " TEXT," +
                this.columnName + "TEXT," +
                this.columnAddress + "TEXT," +
                this.columnTypePet + "TEXT," +
                this.columnPhone + "INTEGER" + ")";
    }

    public String delete() {
        return  "DROP TABLE IF EXISTS " + this.tableName;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
        db.execSQL(this.setTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        //Drop User Table if exist
        db.execSQL(this.delete());
        // Create tables again
        onCreate(db);

    }
}
