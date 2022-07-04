package com.grimlin31.buddywalkowner.Model;

public interface Specification<Model, ModelValue1> {
    boolean isExist(Model model);
    ModelValue1 getValue();
}
