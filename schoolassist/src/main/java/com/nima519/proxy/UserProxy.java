package com.nima519.proxy;

import android.content.Context;

import com.nima519.entity.User;
import com.nima519.utils.L;

import cn.bmob.im.BmobUserManager;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.ResetPasswordByEmailListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by nima519 on 2016/1/4.
 */
public class UserProxy {
    public  static String TAG ="UserProxy";
    private Context mContext;

    private ILoginListener loginListener;
    private IResetPasswordListener resetPasswordListener;
    private ISignUpListener signUpListener;
    private IUpdateListener updateListener;

    BmobUserManager userManager;

    public UserProxy(Context mContext) {
        this.mContext = mContext;
        this.userManager = BmobUserManager.getInstance(this.mContext);
    }

    public User getCurrentUser(){
        User localUser = (User)BmobUser.getCurrentUser(this.mContext,User.class);
        if(localUser!=null){
            L.i(TAG,"本地用户信息"+localUser.getObjectId()+"-"+localUser.getUsername()+"-"+localUser.getSessionToken()+"-" + localUser.getCreatedAt() + "-" + localUser.getUpdatedAt() + "-" + localUser.getSignature() + "-" + localUser.getSex());
        }
        L.i(TAG,"本地用户为空，请登陆");
        return null;
    }

    public void login(String userName,String pwd){
        BmobUser localBmobUser = new BmobUser();
        localBmobUser.setUsername(userName);
        localBmobUser.setPassword(pwd);
        localBmobUser.login(this.mContext, new SaveListener() {
            @Override
            public void onSuccess() {
                if (UserProxy.this.loginListener != null)
                {
                    UserProxy.this.loginListener.onLoginSuccess();
                    return;
                }
                L.i(TAG,"login listener is null, you must set one!");
            }

            @Override
            public void onFailure(int i, String s) {
                if(UserProxy.this.loginListener != null){
                    UserProxy.this.loginListener.onLoginFailure(s);
                    return ;
                }
                L.i(TAG,"login listener is null, you must set one!");
            }
        });
    }

    public void logout(){
        BmobUser.logOut(this.mContext);
        StringBuilder localStringBuilder = new StringBuilder("logout result:");
        if(getCurrentUser()==null){}

    }
    public void signUp(String userName,String password,String email){
        final User localUser = new User();
        localUser.setUsername(userName);
        localUser.setPassword(password);
        localUser.setEmail(email);
        //和当前adroid设备id绑定
        localUser.setInstallId(BmobInstallation.getInstallationId(this.mContext));
        localUser.setSex("female");
        localUser.setSignature("这个家伙很懒，什么都不说。。。。。。。");
        localUser.signUp(this.mContext, new SaveListener() {
            @Override
            public void onSuccess() {
                if(UserProxy.this.signUpListener != null){
                    UserProxy.this.signUpListener.onSignUpSuccess();
                    return ;
                }
                L.i(TAG,"signUpListener is null, you must set one!");
            }

            @Override
            public void onFailure(int i, String s) {
                if(UserProxy.this.signUpListener != null){
                    UserProxy.this.signUpListener.onSignUpFailure(s);
                    return ;
                }
                L.i(TAG,"signUpListener is null, you must set one!");
            }
        });
    }

    /**
     *
     * @param
     * args:用户名,Email,Password,Sex,Signature
     *
     */
    public void update(String...args){
        User localUser = getCurrentUser();
        localUser.setUsername(args[0]);
        localUser.setEmail(args[1]);
        localUser.setPassword(args[2]);
        localUser.setSex(args[3]);
        localUser.setSignature(args[4]);
        localUser.update(this.mContext, new UpdateListener() {
            @Override
            public void onSuccess() {
                if (UserProxy.this.updateListener != null)
                {
                    UserProxy.this.updateListener.onUpdateSuccess();
                    return;
                }
                L.i(TAG,"updateListener is null, you must set one!");
            }

            @Override
            public void onFailure(int i, String s) {
                if (UserProxy.this.updateListener != null)
                {
                    UserProxy.this.updateListener.onUpdateFailure(s);
                    return;
                }
                L.i(TAG,"updateListener is null, you must set one!");
            }
        });
    }
    public void restPassword(String email){
        BmobUser.resetPasswordByEmail(this.mContext, email, new ResetPasswordByEmailListener() {
            @Override
            public void onSuccess() {
                if (UserProxy.this.resetPasswordListener != null)
                {
                    UserProxy.this.resetPasswordListener.onResetSuccess();
                    return;
                }
                L.i(TAG,"rest listener is null ,you must set one");
            }

            @Override
            public void onFailure(int i, String s) {
                if (UserProxy.this.resetPasswordListener != null)
                {
                    UserProxy.this.resetPasswordListener.onResetFailure(s);
                    return;
                }
                L.i(TAG,"rest listener is null ,you must set one");
            }
        } );
    }


    public static abstract interface ILoginListener
    {
        public abstract void onLoginFailure(String paramString);

        public abstract void onLoginSuccess();
    }

    public static abstract interface IResetPasswordListener
    {
        public abstract void onResetFailure(String paramString);

        public abstract void onResetSuccess();
    }

    public static abstract interface ISignUpListener
    {
        public abstract void onSignUpFailure(String paramString);

        public abstract void onSignUpSuccess();
    }

    public static abstract interface IUpdateListener
    {
        public abstract void onUpdateFailure(String paramString);

        public abstract void onUpdateSuccess();
    }

    public void setLoginListener(ILoginListener loginListener) {
        this.loginListener = loginListener;
    }

    public void setSignUpListener(ISignUpListener signUpListener) {
        this.signUpListener = signUpListener;
    }

    public void setUpdateListener(IUpdateListener updateListener) {
        this.updateListener = updateListener;
    }
}
