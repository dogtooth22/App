package com.grimlin31.buddywalkowner.Model.Token;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.grimlin31.buddywalkowner.DataManager.Local.TokenSQLHelper.TokenContactDataBase;
import com.grimlin31.buddywalkowner.DataManager.Local.TokenSQLHelper.TokenSQL;
import com.grimlin31.buddywalkowner.Model.LocalRepository;
import com.grimlin31.buddywalkowner.Model.Specification;

import java.util.Map;

public class TokenRepository implements LocalRepository<TokenModel> {

    private final SQLiteDatabase dbWrite;
    private final SQLiteDatabase dbRead;

    private TokenModel token;

    public TokenRepository(Context context) {
        TokenSQL tokenSQL = new TokenSQL(context);

        this.dbRead = tokenSQL.getReadableDatabase();
        this.dbWrite = tokenSQL.getWritableDatabase();
    }

    @Override
    public long addValue(TokenModel tokenModel) {
        ContentValues value = this.createSQLStruct(tokenModel);
        return this.dbWrite.insert(TokenContactDataBase.getTableName(), null, value);
    }

    @Override
    public long addValue(Map<String, String> model) {
        return 0;
    }

    @Override
    public TokenModel findBySpecification(Specification specification) {
        String key = specification.getValue().toString();

        String userName;
        String pass;
        String uidUser;

        String[] projection = {
                TokenContactDataBase.getUserEmail(),
                TokenContactDataBase.getColumnPass(),
                TokenContactDataBase.getColumnUid(),
        };

        String whereParameter = TokenContactDataBase.getUserEmail() + " = ?";
        String[] whereValues = { key };

        Cursor cursor = this.dbRead.query(
                TokenContactDataBase.getTableName(),
                projection,
                whereParameter,
                whereValues,
                null,
                null,
                null
        );
        int columnName = cursor.getColumnIndex(TokenContactDataBase.getUserEmail());
        int columnToken = cursor.getColumnIndex(TokenContactDataBase.getColumnPass());
        int columnUid = cursor.getColumnIndex(TokenContactDataBase.getColumnUid());

        while(cursor.moveToNext()) {
            userName = cursor.getString(columnName);
            pass = cursor.getString(columnToken);
            uidUser = cursor.getString(columnUid);

            this.token = new TokenModel(userName, pass, uidUser);

            if (specification.isExist(this.token)){
                cursor.moveToLast();
                return this.token;
            }
            this.token = null;
        }
        return this.token;
    }

    @Override
    public int deleteAll() {
        return this.dbWrite.delete(
                TokenContactDataBase.getTableName(),
                null,
                null
        );
    }

    @Override
    public ContentValues createSQLStruct(TokenModel tokenModel) {
        ContentValues values = new ContentValues();
        values.put(TokenContactDataBase.getUserEmail(), tokenModel.getUser());
        values.put(TokenContactDataBase.getColumnPass(), tokenModel.getPass());
        values.put(TokenContactDataBase.getColumnPass(), tokenModel.getPass());
        return values;
    }

    @Override
    public ContentValues createSQLStruct(Map model) {
        return null;
    }

    @Override
    public void unRegister() {
        dbWrite.close();
        dbRead.close();
    }
}
