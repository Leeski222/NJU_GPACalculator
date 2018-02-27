package com.lee.nju_gpa_calculator.presenter;

import android.util.Log;

import com.lee.nju_gpa_calculator.contract.GPAContract;
import com.lee.nju_gpa_calculator.model.ModelRepository;
import com.lee.nju_gpa_calculator.model.modelinterface.GPAModel;
import com.lee.nju_gpa_calculator.model.vopo.AchievementsVO;
import com.lee.nju_gpa_calculator.presenter.htmlparser.GPAHtmlParser;
import com.lee.nju_gpa_calculator.utils.LogUtil;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by 果宝 on 2018/2/3.
 */

public class GPAPresenter implements GPAContract.Presenter {

    private List<String> termList;
    private AchievementsVO achievementsVO;

    private GPAModel gpaModel;
    private GPAContract.View gpaView;

    public GPAPresenter(GPAContract.View gpaView) {
        this.gpaModel = ModelRepository.getInstance().getGPAModel();
        this.gpaView = gpaView;
        this.achievementsVO = new AchievementsVO();
    }

    @Override
    public void start() {
        getAchievementList();
    }

    @Override
    public void getAchievementList() {
        gpaModel.getAchievementList(new Observer<Response<ResponseBody>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<ResponseBody> response) {
                termList = GPAHtmlParser.getTermList(response);
                if(termList != null) {
                    termList.add("20171");
//                    getAchievementInfo();
                } else {
                    gpaView.getAchievementsFailed();
                }
            }

            @Override
            public void onError(Throwable e) {
                gpaView.getAchievementsFailed();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getAchievementInfo() {
        for(String term : termList) {
            gpaModel.getAchievementInfoByTerm(new Observer<Response<ResponseBody>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Response<ResponseBody> response) {
                    try {
                        LogUtil.e("getAchievementInfo", response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    gpaView.getAchievementsFailed();
                }

                @Override
                public void onComplete() {

                }
            }, term);
        }

        gpaView.setAchievementInfo(achievementsVO);
    }

}
