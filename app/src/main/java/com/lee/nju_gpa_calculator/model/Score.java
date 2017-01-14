package com.lee.nju_gpa_calculator.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Guo on 2017/1/15.
 * 保存单门学科成绩的实体类
 *
 * 采用android独有的Parcelable接口实现序列化
 * 相对于Java自带的Serializable接口，Parcelable接口实现更复杂，但效率更高
 *
 * Parcelable序列化实现原理是：
 * writeToParcel方法可将自己所写的对象映射成Parcel对象，createFromParcel方法可将Parcel对象映射成自己所写的对象
 * 也可以将Parcel看成是一个流，通过writeToParcel方法把对象写到流里面，通过createFromParcel方法从流里读取对象
 * 只不过这个读写过程需要使用者自己实现，因此writeToParcel方法中写的顺序和createFromParcel方法中读的顺序必须一致
 */
public class Score implements Parcelable{

    private String ID;              // 编号
    private String subject;         // 科目
    private String category;        // 类别
    private String finalScore;      // 总评
    private String credit;          // 学分

    //Parcelable接口必须实现的方法，可直接返回0
    @Override
    public int describeContents() {
        return 0;
    }

    //Parcelable接口必须实现的方法，用于将实体类的数据写入外部Parcel
    @Override
    public void writeToParcel(Parcel outParcel, int flags) {
        outParcel.writeString(ID);
        outParcel.writeString(subject);
        outParcel.writeString(category);
        outParcel.writeString(finalScore);
        outParcel.writeString(credit);
    }

    //用Parcelable接口序列化的类
    //内部必须实现实例化静态内部对象CREATOR
    //以此实现接口Parcelable.Creator
    public static final Parcelable.Creator<Score> CREATOR = new Creator<Score>() {
        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }

        @Override
        public Score createFromParcel(Parcel inputParcel) {
            return new Score(inputParcel);
        }
    };

    public Score(Parcel inputParcel) {
        ID = inputParcel.readString();
        subject = inputParcel.readString();
        category = inputParcel.readString();
        finalScore = inputParcel.readString();
        credit = inputParcel.readString();
    }

    //所有属性的setter和getter方法
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

}
