package zhgxtc.com.zft_zhgxtc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import common.LoginUserInfo;
import common.Utils;
import model.sys_users;

public class SplashScreen extends BaseAppCompatActivity {
    private static final int animTime = 100;
    private static final int moveTime = 150;
    private ImageView iv_image, iv_loading;

    boolean isFirstIn = false;
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;
    private static final long SPLASH_DELAY_MILLIS = 3000;
    public static final String SHAREDPREFERENCES_NAME = "first_pref";
    public static final String IS_FIRST_IN="isFirstIn";

    private String account=null;
    private String password=null;
    public int stye=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        iv_image = this.findViewById(R.id.iv_image);
        iv_loading = this.findViewById(R.id.iv_loading);

        account = Utils.ReadSharePreference(this,"sjh","Sjh");
        password =Utils.ReadSharePreference(this,"pwd","Pwd");
        init();
        showAnim();
        AnimationDrawable mAnimation = (AnimationDrawable) iv_loading.getDrawable();
        mAnimation.start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    stye=1;
                    break;
                case GO_GUIDE:
                    stye=2;
                    break;
            }
            super.handleMessage(msg);
        }
    };

    //用SharedPreferences 判断是否首次打开APP
    private void init() {
        SharedPreferences preferences = getSharedPreferences(SHAREDPREFERENCES_NAME, MODE_PRIVATE);
        isFirstIn = preferences.getBoolean(IS_FIRST_IN, true);
        if (isFirstIn) {
            mHandler.sendEmptyMessage(GO_GUIDE);

        } else {
            mHandler.sendEmptyMessage(GO_HOME);
        }
    }

    private boolean isAutoLogin(){
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }

    /**
     * 启动动画
     */
    private void showAnim() {
        final AnimationDrawable anim = new AnimationDrawable();

        anim.addFrame(getResources().getDrawable(R.mipmap.ic_20001), animTime);
        anim.addFrame(getResources().getDrawable(R.mipmap.ic_20002), animTime);
        anim.addFrame(getResources().getDrawable(R.mipmap.ic_20003), animTime);
        anim.addFrame(getResources().getDrawable(R.mipmap.ic_20004), animTime);
        anim.addFrame(getResources().getDrawable(R.mipmap.ic_20005), animTime);
        anim.addFrame(getResources().getDrawable(R.mipmap.ic_20006), animTime);
        anim.addFrame(getResources().getDrawable(R.mipmap.ic_20007), animTime);
        anim.addFrame(getResources().getDrawable(R.mipmap.ic_20008), animTime);
        anim.addFrame(getResources().getDrawable(R.mipmap.ic_20010), animTime);
        anim.addFrame(getResources().getDrawable(R.mipmap.ic_20011), animTime);
        anim.addFrame(getResources().getDrawable(R.mipmap.ic_20012), animTime);
        anim.addFrame(getResources().getDrawable(R.mipmap.ic_20013), animTime);
        anim.addFrame(getResources().getDrawable(R.mipmap.ic_20014), animTime);
        anim.addFrame(getResources().getDrawable(R.mipmap.ic_20015), animTime);

        anim.setOneShot(true);

        iv_image.setBackgroundDrawable(anim);
        anim.start();
        int duration = 0;

        for (int i = 0; i < anim.getNumberOfFrames(); i++) {
            duration += anim.getDuration(i);
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            public void run() {
                //动画结束
                moveAnim();
            }

        }, duration);
    }

    /**
     * 移动动画
     */
    private void moveAnim(){
        if (isAutoLogin()) {
            //已有登陆账号
            //进入登录逻辑
            iv_loading.setVisibility(View.VISIBLE);
            AnimationDrawable mAnimation = (AnimationDrawable) iv_loading.getDrawable();
            mAnimation.start();
            LoginUserInfo.UserModel =new sys_users();
            LoginUserInfo.UserModel.sjh =Utils.ReadSharePreference(SplashScreen.this,"sjh","Sjh");
            LoginUserInfo.UserModel.xm =Utils.ReadSharePreference(SplashScreen.this,"xm","Xm");
            LoginUserInfo.UserModel.grxz =Utils.ReadSharePreference(SplashScreen.this,"grxz","Grxz");
            Utils.StartActivity(SplashScreen.this,MainActivity.class,true);
        }else {
            //没有登陆账号
            //动画后进入登录页面
            showLoadingImageView();
        }

    }

    private void scheduleShowTargetActivity() {
        AlphaAnimation alphaAnimation = (AlphaAnimation) AnimationUtils.loadAnimation(this, R.anim.alpha_img);
        iv_image.startAnimation(alphaAnimation);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO 运行
                if (stye==2) {
                    Intent intent = new Intent(SplashScreen.this,
                            GuideViewActivity.class);
                    intent.putExtra("stype", "2");
                    startActivity(intent);
                    SplashScreen.this.finish();
                    overridePendingTransition(R.anim.alpha_in,
                            R.anim.alpha_out);
                } else{
                    Intent intent = new Intent(SplashScreen.this,
                            LoginActivity.class);
                    SplashScreen.this.startActivity(intent);
                    SplashScreen.this.finish();
                    overridePendingTransition(R.anim.alpha_in,
                            R.anim.alpha_out);

                }

            }
        }, 500);// 0.5秒后执行Runnable中的run方法

    }

    /**
     * 显示loading
     */
    private void showLoadingImageView() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO 运行
                scheduleShowTargetActivity();
            }
        }, moveTime);

    }

}
