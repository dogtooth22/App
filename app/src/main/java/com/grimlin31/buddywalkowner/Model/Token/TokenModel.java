package com.grimlin31.buddywalkowner.Model.Token;

import android.content.ContentValues;
import android.util.Log;

import com.grimlin31.buddywalkowner.DataManager.Local.TokenSQLHelper.TokenContactDataBase;
import com.grimlin31.buddywalkowner.Model.UserOwner.UserOwnerModel;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;


public class TokenModel implements Serializable {

    public String pass;
    public String user;
    public String uidUser;

    public TokenModel(String user, String pass, String uidUser){
        this.user = user;
        this.pass = pass;
        this.uidUser = uidUser;
    }

    public TokenModel setValuesFromMap(
            Map<String, String> map,
            TokenModel model
    ) {
        Class cls = UserOwnerModel.class;
        for (String key: map.keySet()) {
            try {
                Field field = cls.getField(key);
                field.set(model, map.get(key));
            } catch (Exception err) {
                Log.i("User_Owner_Model".toUpperCase(), key + " does not exist in this class");
            }
        }
        return model;
    }

    public String getPass() {
        return pass;
    }

    public String getUser() {
        return user;
    }

    public String getUidUser() {
        return uidUser;
    }
}