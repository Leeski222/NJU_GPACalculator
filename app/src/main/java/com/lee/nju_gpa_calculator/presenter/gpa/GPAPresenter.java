package com.lee.nju_gpa_calculator.presenter.gpa;

import com.lee.nju_gpa_calculator.contract.GPAContract;
import com.lee.nju_gpa_calculator.model.ModelRepository;
import com.lee.nju_gpa_calculator.model.modelinterface.GPAModel;
import com.lee.nju_gpa_calculator.model.vopo.AchievementsVO;

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
                    getAchievementInfo();
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
