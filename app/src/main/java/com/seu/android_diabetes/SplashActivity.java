package com.seu.android_diabetes;

import android.content.Intent;

import com.xuexiang.xui.widget.activity.BaseSplashActivity;

public class SplashActivity extends BaseSplashActivity {

    @Override
    protected long getSplashDurationMillis() {
        return 3000; //设置启动页面持续时间，单位毫秒
    }

    @Override
    protected void onCreateActivity() {
        initSplashView(R.mipmap.splash); //设置启动页面背景图片
        startSplash(false); //开始启动页面

    }

    @Override
    protected void onSplashFinished() {
        //启动页面结束后的逻辑，比如跳转到主界面或者引导界面
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}