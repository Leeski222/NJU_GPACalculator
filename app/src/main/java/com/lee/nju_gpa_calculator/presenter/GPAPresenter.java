package com.lee.nju_gpa_calculator.presenter;

import android.util.Log;

import com.lee.nju_gpa_calculator.contract.GPAContract;
import com.lee.nju_gpa_calculator.model.ModelRepository;
import com.lee.nju_gpa_calculator.model.modelinterface.GPAModel;
import com.lee.nju_gpa_calculator.model.vopo.CourseVO;
import com.lee.nju_gpa_calculator.model.vopo.TermVO;
import com.lee.nju_gpa_calculator.presenter.htmlparser.GPAHtmlParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by 果宝 on 2018/2/3.
 */

public class GPAPresenter implements GPAContract.Presenter {

    private Map<String, String> termMap;
    private List<TermVO> termVOs;

    private GPAModel gpaModel;
    private GPAContract.View gpaView;

    public GPAPresenter(GPAContract.View gpaView) {
        this.gpaModel = ModelRepository.getInstance().getGPAModel();
        this.gpaView = gpaView;
        this.termVOs = new ArrayList();
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
                if(response != null && response.isSuccessful()) {
                    termMap = GPAHtmlParser.getTermsBy(response);
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
        final int sum = termMap.size();

        for(final String term : termMap.keySet()) {
            gpaModel.getAchievementInfoByTerm(new Observer<Response<ResponseBody>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Response<ResponseBody> response) {
                    if(response != null && response.isSuccessful()) {
                        String termName = termMap.get(term);
                        List<CourseVO> courseVOList = GPAHtmlParser.getCoursesBy(response);
                        termVOs.add(new TermVO(termName, courseVOList));
                        Log.e("getAchievementInfo", termVOs.size() + "");
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
                    if(termVOs.size() == sum) {
                        gpaView.setAchievementInfo(termVOs);
                    }
                }
            }, term);
        }

    }

}
