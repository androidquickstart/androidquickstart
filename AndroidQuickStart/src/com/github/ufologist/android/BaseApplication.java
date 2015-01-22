package com.github.ufologist.android;

import com.umeng.fb.push.FeedbackPush;
import com.umeng.update.UmengUpdateAgent;

import android.app.Application;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化反馈推送的相关设置
        FeedbackPush.getInstance(this).init(false);
        // 应用推广栏, 需要在友盟后台自动更新那里开启 推广位开关
        // 设置这个后会在更新提示的对话框底部显示一排精品应用推荐下载
        UmengUpdateAgent.setSlotId(getString(R.string.umeng_update_slot_id));
    }
}
