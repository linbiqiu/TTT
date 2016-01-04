package com.nima519.entity;

import cn.bmob.im.bean.BmobChatUser;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by nima519 on 2016/1/4.
 */
public class User extends BmobChatUser {
    public static final  String TAG = "USER";
    private BmobRelation favorite;
    private String sex;
    private String signature;

    public BmobRelation getFavorite(){
        return this.favorite;
    }

    public void setFavorite(BmobRelation favorite) {
        this.favorite = favorite;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
