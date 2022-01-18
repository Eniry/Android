package com.mingqing.jin.android_3;


import java.io.Serializable;
import java.util.Random;

public class UpInfoBean implements Serializable {

    //up主名称
    private String upName;
    //up主头像
    private int upImage;
    //up主动态图
    private int bodyImage;
    //是否取关
    private boolean isFocus;
    //粉丝数
    private int fans;

    public UpInfoBean(String upName, int upImage,int bodyImage) {
        this.upName = upName;
        this.upImage = upImage;
        this.bodyImage=bodyImage;
        this.isFocus=true;
        this.fans = new Random().nextInt(1000);
    }


    public String getUpName() {
        return upName;
    }

    public void setUpName(String upName) {
        this.upName = upName;
    }

    public int getUpImage() {
        return upImage;
    }

    public void setUpImage(int upImage) {
        this.upImage = upImage;
    }

    public int getBodyImage() {
        return bodyImage;
    }

    public void setBodyImage(int bodyImage) {
        this.bodyImage = bodyImage;
    }

    public boolean isFocus() {
        return isFocus;
    }

    public void setFocus(boolean focus) {
        isFocus = focus;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }
}
