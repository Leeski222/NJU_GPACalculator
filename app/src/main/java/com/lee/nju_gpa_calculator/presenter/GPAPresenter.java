package com.lee.nju_gpa_calculator.presenter;

import com.lee.nju_gpa_calculator.contract.GPAContract;
import com.lee.nju_gpa_calculator.model.ModelRepository;
import com.lee.nju_gpa_calculator.model.modelinterface.GPAModel;
import com.lee.nju_gpa_calculator.model.vopo.AchievementsVO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by 果宝 on 2018/2/3.
 */

public class GPAPresenter implements GPAContract.Presenter {

    private String grade;
    private List<String> termList;
    private AchievementsVO achievementsVO;

    private GPAModel gpaModel;
    private GPAContract.View gpaView;

    public GPAPresenter(GPAContract.View gpaView, String grade) {
        this.gpaModel = ModelRepository.getInstance().getGPAModel();
        this.gpaView = gpaView;
        this.grade = grade;
        this.termList = getTermList(grade);
        this.achievementsVO = new AchievementsVO();
    }

    @Override
    public void start() {
        getAchievementInfo();
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

    /**
     * 根据年级转换出对应的学期表
     * @param grade
     * @return
     */
    private List getTermList(String grade) {
        List<String> termList = new ArrayList();
        termList.add("");
        return termList;
    }

}
