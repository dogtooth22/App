package com.grimlin31.buddywalkowner.Model;

import android.content.ContentValues;

import java.util.Map;

public interface LocalRepository<Model> {
    long addValue(Model model);
    long addValue(Map<String, String> model);
    Model findBySpecification(Specification specification);
    int deleteAll();

    // Utils Repositories
    ContentValues createSQLStruct(Model model);
    ContentValues createSQLStruct(Map model);
    void unRegister();
}
