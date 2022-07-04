package com.grimlin31.buddywalkowner.DataManager.Local.TokenSQLHelper;

public final class TokenContactDataBase {

    // Prevent instantiate by error.
    private TokenContactDataBase() {}

    //Users Table Name
    private static final String tableName = "token";
    //User Table Columns
    private static final String userEmail = "email";
    private static final String columnPass = "pass";
    private static final String columnUid = "uid";

    public static final String SQL_CREATE = "CREATE TABLE " + tableName + " (" +
            userEmail + " TEXT PRIMARY KEY , " +
            columnPass + " TEXT, " + columnUid + " TEXT" + ")";

    public static final String SQL_DELETE =
            "DROP TABLE IF EXISTS " + tableName;

    public static String getColumnPass() {
        return columnPass;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static String getColumnUid() {
        return columnUid;
    }

    public static String getTableName() {
        return tableName;
    }
}
