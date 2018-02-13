package com.lee.nju_gpa_calculator.model;

import com.lee.nju_gpa_calculator.model.modelimpl.GPAModelImpl;
import com.lee.nju_gpa_calculator.model.modelimpl.LoginModelImpl;
import com.lee.nju_gpa_calculator.model.modelinterface.GPAModel;
import com.lee.nju_gpa_calculator.model.modelinterface.LoginModel;

/**
 * Created by 果宝 on 2018/2/8.
 */

public class ModelRepository {

    private static ModelRepository modelRepository;

    private ModelRepository() {
    }

    public static ModelRepository getInstance() {
        if(modelRepository == null) {
            modelRepository = new ModelRepository();
        }

        return modelRepository;
    }

    public LoginModel getLoginModel() {
        return new LoginModelImpl();
    }

    public GPAModel getGPAModel() {
        return new GPAModelImpl();
    }

}
