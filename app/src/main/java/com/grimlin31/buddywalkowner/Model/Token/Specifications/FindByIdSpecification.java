package com.grimlin31.buddywalkowner.Model.Token.Specifications;

import com.grimlin31.buddywalkowner.Model.Specification;
import com.grimlin31.buddywalkowner.Model.Token.TokenModel;

public class FindByIdSpecification
        implements Specification<TokenModel, String> {

    private String email;

    public FindByIdSpecification(String email){
        this.email = email;
    }


    @Override
    public boolean isExist(TokenModel tokenModel) {
        return tokenModel.getUser().equalsIgnoreCase(this.email);
    }

    @Override
    public String getValue() {
        return this.email;
    }
}
