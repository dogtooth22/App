package com.grimlin31.buddywalkowner.DataManager.Local.UserSQLHelper;

public final class UserContractsDataBase {

    // Prevent instantiate by error.
    private UserContractsDataBase(){}

    //Users Table Name
    public static final String tableName = "user";
    //User Table Columns
    public static final String columnId = "id";
    public static final String columnEmail = "email";
    public static final String columnUserName = "username";

    public static final String columnAddress = "address";
    public static final String columnTypePet = "type_pet";
    public static final String columnName = "name";
    public static final String columnPhone = "phone";

    public static final String SQL_CREATE = "CREATE TABLE " + tableName + "(" +
            columnId + " TEXT PRIMARY KEY, " +
            columnUserName + " TEXT, " +
            columnEmail + " TEXT, " +
            columnName + " TEXT, " +
            columnAddress + " TEXT, " +
            columnTypePet + " TEXT, " +
            columnPhone + " TEXT" + " )";

    public static final String SQL_DELETE =
            "DROP TABLE IF EXISTS " + tableName;
}
