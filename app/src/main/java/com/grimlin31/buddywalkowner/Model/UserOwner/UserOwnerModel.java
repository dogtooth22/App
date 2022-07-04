package com.grimlin31.buddywalkowner.Model.UserOwner;

import android.util.Log;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class UserOwnerModel implements Serializable {

    public static String collectionDB = "user_owner";

    public String id;

    public String email = "";
    public String username = "";

    public String phone = "";
    public String name = "";
    public String address = "";
    public String type_pet = "";

    public UserOwnerModel(){}

    public UserOwnerModel(
            String id,
            String email,
            String username,
            String phone,
            String name,
            String address,
            String typePet
    ){
        this.id = id;
        this.email = email;
        this.address = address;
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.type_pet = typePet;
    }

    public String getId() {
        return id;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setId(String id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setPhone(String phone) {
        this.phone = phone;
    }

    private void setTypePet(String typePet) {
        this.type_pet = typePet;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public static UserOwnerModel setValuesFromMap(
            Map<String, String> map,
            UserOwnerModel model
    ) {
        Class cls = UserOwnerModel.class;
        for (String key: map.keySet()) {
            try {
                Field field = cls.getField(key);
                field.set(model, map.get(key));
            } catch (Exception err) {
                Log.i("User_Owner_Model".toUpperCase(), key + " Does not exist in this class");
            }
        }
        return model;
    }

    public static Map convertObjToMapReflection(
            UserOwnerModel userOwnerModel
    ) {
        Map<String, Object> map = new HashMap<String,Object>();

        Field[] allFields = userOwnerModel.getClass().getDeclaredFields();
        for (Field field : allFields) {
            try {
                if (!field.getName().equalsIgnoreCase("collectionDB")){
                    field.setAccessible(true);
                    Object value = field.get(userOwnerModel);
                    map.put(field.getName(), (String) value);
                }
            } catch (IllegalAccessException e) {}
        }
        return map;
    }
}
