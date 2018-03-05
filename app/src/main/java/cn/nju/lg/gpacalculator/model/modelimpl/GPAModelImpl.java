package com.lee.nju_gpa_calculator.model.modelimpl;

import com.lee.nju_gpa_calculator.model.modelinterface.GPAModel;
import com.lee.nju_gpa_calculator.model.service.GPAService;
import com.lee.nju_gpa_calculator.model.service.RetrofitServer;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by 果宝 on 2018/2/3.
 */

public class GPAModelImpl implements GPAModel {

    private final String method = "searchTermList";

    private GPAService gpaService;

    public GPAModelImpl() {
        gpaService = RetrofitServer.getInstance().create(GPAService.class);
    }

    @Override
    public void getAchievementList(Observer<Response<ResponseBody>> observer) {
        gpaService.getAchievementList(method)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getAchievementInfoByTerm(Observer<Response<ResponseBody>> observer, String term) {
        gpaService.getAchievementInfo(method, term)
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
