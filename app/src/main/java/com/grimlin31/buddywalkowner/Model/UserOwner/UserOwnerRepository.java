package com.grimlin31.buddywalkowner.Model.UserOwner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;

import com.grimlin31.buddywalkowner.DataManager.Local.UserSQLHelper.UserContractsDataBase;
import com.grimlin31.buddywalkowner.DataManager.Local.UserSQLHelper.UserSQL;
import com.grimlin31.buddywalkowner.Model.LocalRepository;
import com.grimlin31.buddywalkowner.Model.Specification;

import java.util.Map;

public class UserOwnerRepository implements LocalRepository<UserOwnerModel> {

    private final SQLiteDatabase dbWrite;
    private final SQLiteDatabase dbRead;

    UserOwnerModel userOwnerModel;

    String tableName;

    public UserOwnerRepository(
            Context context
    ){
        UserSQL userSQL = new UserSQL(context);

        this.dbRead = userSQL.getReadableDatabase();
        this.dbWrite = userSQL.getWritableDatabase();

        this.tableName = UserContractsDataBase.tableName;
    }

    public UserOwnerModel castMapToClass(Map map, UserOwnerModel userOwnerModel) {
        return UserOwnerModel.setValuesFromMap(
                map,
                userOwnerModel
        );
    }

    @Override
    public long addValue(UserOwnerModel userOwnerModel) {
        ContentValues value = this.createSQLStruct(userOwnerModel);
        return dbWrite.insert(
                tableName,
                null,
                value
        );
    }

    @Override
    public long addValue(Map<String, String> userOwnerModel) {
        return dbWrite.insert(
                tableName,
                null,
                this.createSQLStruct(userOwnerModel)
        );
    }

    @Override
    public UserOwnerModel findBySpecification(Specification specification) {

        String key = specification.getValue().toString();

        String[] projection = {
                UserContractsDataBase.columnId,
                UserContractsDataBase.columnAddress,
                UserContractsDataBase.columnUserName,
                UserContractsDataBase.columnName,
                UserContractsDataBase.columnPhone,
                UserContractsDataBase.columnTypePet,
                UserContractsDataBase.columnEmail,
        };

        String whereParameter =
                UserContractsDataBase.columnId + " = ?";
        String[] whereValues = { key };

        Cursor cursor = this.dbRead.query(
                tableName,
                projection,
                whereParameter,
                whereValues,
                null,
                null,
                null
        );


        int columnId = cursor.getColumnIndex(UserContractsDataBase.columnId);
        int columnEmail = cursor.getColumnIndex(UserContractsDataBase.columnEmail);
        int columnUserName = cursor.getColumnIndex(UserContractsDataBase.columnUserName);
        int columnAddress = cursor.getColumnIndex(UserContractsDataBase.columnAddress);
        int columnTypePet = cursor.getColumnIndex(UserContractsDataBase.columnTypePet);
        int columnName = cursor.getColumnIndex(UserContractsDataBase.columnName);
        int columnPhone = cursor.getColumnIndex(UserContractsDataBase.columnPhone);

        while(cursor.moveToNext()) {

            this.userOwnerModel = new UserOwnerModel(
                    cursor.getString(columnId),
                    cursor.getString(columnEmail),
                    cursor.getString(columnUserName),
                    cursor.getString(columnPhone),
                    cursor.getString(columnName),
                    cursor.getString(columnAddress),
                    cursor.getString(columnTypePet)
            );

            if (specification.isExist(this.userOwnerModel)){
                cursor.moveToLast();
                return this.userOwnerModel;
            }
            this.userOwnerModel = null;
        }
        return this.userOwnerModel;
    }

    @Override
    public int deleteAll() {
        return this.dbWrite.delete(
                UserContractsDataBase.tableName,
                null,
                null
        );
    }

    @Override
    public ContentValues createSQLStruct(UserOwnerModel userOwnerModel) {
        ContentValues values = new ContentValues();
        values.put(UserContractsDataBase.columnAddress, userOwnerModel.address);
        values.put(UserContractsDataBase.columnUserName, userOwnerModel.username);
        values.put(UserContractsDataBase.columnEmail, userOwnerModel.email);
        values.put(UserContractsDataBase.columnName, userOwnerModel.name);
        values.put(UserContractsDataBase.columnId, userOwnerModel.id);
        values.put(UserContractsDataBase.columnPhone, userOwnerModel.phone);
        values.put(UserContractsDataBase.columnTypePet, userOwnerModel.type_pet);
        return values;
    }

    @Override
    public ContentValues createSQLStruct(Map model) {
        Parcel parcel = Parcel.obtain();
        parcel.writeMap(model);
        parcel.setDataPosition(0);
        return ContentValues.CREATOR.createFromParcel(parcel);
    }

    @Override
    public void unRegister() {
        this.dbWrite.close();
        this.dbRead.close();
    }
}
