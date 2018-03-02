package com.lee.nju_gpa_calculator.presenter;

import android.util.Log;

import com.lee.nju_gpa_calculator.contract.GPAContract;
import com.lee.nju_gpa_calculator.model.ModelRepository;
import com.lee.nju_gpa_calculator.model.modelinterface.GPAModel;
import com.lee.nju_gpa_calculator.model.vopo.GradeVO;
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

                    for(String term : termMap.keySet()) {
                        Log.e("onNext", term);
                    }

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
                        List<GradeVO> gradeVOList = GPAHtmlParser.getCoursesBy(response);
                        termVOs.add(new TermVO(termName, gradeVOList));
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
                        gpaView.setAchievementInfo( termSorts(termVOs) );
                    }
                }
            }, term);
        }
    }

    private List<TermVO> termSorts(List<TermVO> termVOs) {
        List<TermVO> sortList = new ArrayList<>(termVOs);

        for(int i = 0; i < sortList.size() - 1; i ++) {
            for(int j = 0; j < sortList.size() - i - 1; j ++) {
                TermVO term1 = sortList.get(j);
                String t1 = term1.getTermName();

                TermVO term2 = sortList.get(j + 1);
                String t2 = term2.getTermName();

                if(t1.compareTo(t2) < 0) {
                    sortList.set(j, term2);
                    sortList.set(j+1, term1);
                }
            }
        }

        return sortList;
    }

}
