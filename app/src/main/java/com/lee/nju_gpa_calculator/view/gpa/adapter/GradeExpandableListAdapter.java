package com.lee.nju_gpa_calculator.view.gpa.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.lee.nju_gpa_calculator.R;
import com.lee.nju_gpa_calculator.model.vopo.GradeVO;
import com.lee.nju_gpa_calculator.model.vopo.TermVO;
import com.lee.nju_gpa_calculator.utils.CourseType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.grantland.widget.AutofitTextView;

/**
 * Created by 果宝 on 2018/3/2.
 */

public class GradeExpandableListAdapter extends BaseExpandableListAdapter{
    private List<TermVO> termData;
    private Map<String, Boolean> isSelect;

    public static List<GradeVO> selectGrades = new ArrayList<>();

    private Context mContext;

    private static class ViewHolderGroup{
        private TextView nameTextView;
        private TextView infoTextView;
    }

    private static class ViewHolderItem{
        private CheckBox selectCheckBox;
        private AutofitTextView nameAutofitTextView;
        private TextView typeTextView;
        private TextView creditTextView;
        private AutofitTextView enameAutofitTextView;
        private TextView finalScoreTextView;
    }

    public GradeExpandableListAdapter(List<TermVO> termData, Context mContext) {
        this.termData = termData;
        this.isSelect = new HashMap<>();
        this.mContext = mContext;
    }

    @Override
    public int getGroupCount() {
        return termData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return termData.get(groupPosition).getCourseList().size();
    }

    @Override
    public TermVO getGroup(int groupPosition) {
        return termData.get(groupPosition);
    }

    @Override
    public GradeVO getChild(int groupPosition, int childPosition) {
        return termData.get(groupPosition).getCourseList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //取得用于显示给定分组的视图. 这个方法仅返回分组的视图对象
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolderGroup groupHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_term, parent, false);

            groupHolder = new ViewHolderGroup();
            groupHolder.nameTextView = (TextView) convertView.findViewById(R.id.term_tv_name);
            groupHolder.infoTextView = (TextView) convertView.findViewById(R.id.term_tv_info);

            convertView.setTag(groupHolder);
        }else{
            groupHolder = (ViewHolderGroup) convertView.getTag();
        }

        TermVO termVO = termData.get(groupPosition);
        groupHolder.nameTextView.setText(termVO.getTermName());
        groupHolder.infoTextView.setText("共" + termVO.getCourseNumber() + "门课程，" + termVO.getCreditSum() + "个学分");

        return convertView;
    }

    //取得显示给定分组给定子位置的数据用的视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderItem itemHolder;

        final GradeVO gradeVO = termData.get(groupPosition).getCourseList().get(childPosition);

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_grade, parent, false);

            itemHolder = new ViewHolderItem();
            itemHolder.selectCheckBox = (CheckBox) convertView.findViewById(R.id.grade_cb_select);
            itemHolder.nameAutofitTextView = (AutofitTextView) convertView.findViewById(R.id.grade_aftv_subject);
            itemHolder.typeTextView = (TextView) convertView.findViewById(R.id.grade_tv_type);
            itemHolder.creditTextView = (TextView) convertView.findViewById(R.id.grade_tv_credit);
            itemHolder.enameAutofitTextView = (AutofitTextView) convertView.findViewById(R.id.grade_aftv_ename);
            itemHolder.finalScoreTextView = (TextView) convertView.findViewById(R.id.grade_tv_score);

            itemHolder.selectCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        isSelect.put(gradeVO.getSubject(), Boolean.TRUE);
                        selectGrades.add(gradeVO);
                    } else {
                        isSelect.put(gradeVO.getSubject(), Boolean.FALSE);
                        selectGrades.remove(gradeVO);
                    }
                }
            });

            convertView.setTag(itemHolder);
        }else{
            itemHolder = (ViewHolderItem) convertView.getTag();
        }

        Boolean isSelect = this.isSelect.get(gradeVO.getSubject());
        if(isSelect != null && isSelect == Boolean.TRUE) {
            itemHolder.selectCheckBox.setChecked(true);
        }

        itemHolder.nameAutofitTextView.setText(gradeVO.getSubject());

        itemHolder.typeTextView.setText("  " + gradeVO.getCategory() + "  ");
        this.initTextBackground(itemHolder.typeTextView, gradeVO.getType());

        itemHolder.creditTextView.setText(gradeVO.getCredit() + "学分");
        itemHolder.enameAutofitTextView.setText(gradeVO.getEnglishName());
        itemHolder.finalScoreTextView.setText(gradeVO.getFinalScore());

        return convertView;
    }

    //设置子列表是否可选中
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void initTextBackground(TextView textView, CourseType type) {
        final GradientDrawable mTextBackground = (GradientDrawable) textView.getBackground();

        int color = 0;

        switch (type) {
            case Training:
                color = mContext.getResources().getColor(R.color.colorRed);
                break;

            case PlatForm:
                color = mContext.getResources().getColor(R.color.colorOrange);
                break;

            case Core:
                color = mContext.getResources().getColor(R.color.colorBlue);
                break;

            case Optional:
                color = mContext.getResources().getColor(R.color.colorGreen);
                break;

            case General:
                color = mContext.getResources().getColor(R.color.colorPurple);
        }

        mTextBackground.setColor(color);
    }
}
