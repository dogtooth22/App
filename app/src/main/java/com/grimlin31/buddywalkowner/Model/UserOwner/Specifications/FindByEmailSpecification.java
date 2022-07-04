package com.grimlin31.buddywalkowner.Model.UserOwner.Specifications;

import com.grimlin31.buddywalkowner.Model.Specification;
import com.grimlin31.buddywalkowner.Model.UserOwner.UserOwnerModel;

public class FindByEmailSpecification
        implements Specification<UserOwnerModel, String> {

    private String userId;

    public FindByEmailSpecification(String userId){
        this.userId = userId;
    }

    @Override
    public boolean isExist(UserOwnerModel userOwnerModel) {
        return userOwnerModel.getId().equalsIgnoreCase(this.userId);
    }

    @Override
    public String getValue() {
        return this.userId;
    }
}
