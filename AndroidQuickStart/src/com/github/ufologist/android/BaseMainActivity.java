package com.github.ufologist.android;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.umeng.fb.FeedbackAgent;
import com.umeng.message.PushAgent;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

public class BaseMainActivity extends BaseActivity {
    protected FeedbackAgent fb;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup3rdService();
        checkUpdate();
    }

    protected void setup3rdService() {
        setupOnlineConfig();
        setupFeedback();
        setupPushService();
    }
    /**
     * 开启推送服务
     */
    protected void setupPushService() {
        PushAgent.getInstance(this).enable();
    }
    /**
     * 初始化反馈服务
     */
    protected void setupFeedback() {
        this.fb = new FeedbackAgent(this);
        // 开启反馈回复推送服务
        this.fb.sync();
        this.fb.openFeedbackPush();
    }
    protected void setupOnlineConfig() {
        MobclickAgent.updateOnlineConfig(this);
        // 在线参数回调接口，注意此接口只在在线参数有变化的时候才会回调
        // 会有延时, 不是在页面上改了里面会推送回调过来, 而且好像必须反复启动app才能够生效?
//        MobclickAgent.setOnlineConfigureListener(new UmengOnlineConfigureListener() {
//            @Override
//            public void onDataReceived(JSONObject data) {
//                if (data != null) {
//                    // {"onlineConfig":"test onlineConfig456"}
//                    Log.i(activityName, data.toString());
//                }
//            }
//        });
    }

    protected void checkUpdate() {
        // 默认在Wi-Fi接入情况下才进行自动更新提醒. 如需要在任意网络环境下都进行更新自动提醒, 则请在update调用之前添加以下代码
        // UmengUpdateAgent.setUpdateOnlyWifi(false);
        // 默认为增量更新, 通过setDeltaUpdate来设置, 要达到增量更新的目的, 友盟后台的自动更新那里必须同时存在新旧版本的apk
        // 增量更新的原理: http://bbs.umeng.com/thread-19-1-1.html
        UmengUpdateAgent.update(this);

        // 自动更新的另外一种方式
        // 静默下载更新
        // 用户首次进来后台下载新版本, 下载完成后会在通知栏提示用户安装, 如果用户没有安装, 下次进入应用会给出升级提示
        // 该方式与UmengUpdateAgent.update选择一种方式即可
        // UmengUpdateAgent.silentUpdate(this);
    }

    /*-------------------- 以下都是测试用的方法 --------------------*/
    public void testGetOnlineConfig(View view) {
        String onlineConfig = getOnlineConfigParam("onlineConfig");
        Toast.makeText(this, "获取到友盟在线参数\n onlineConfig=" + onlineConfig, Toast.LENGTH_LONG).show();
    }
    public void testStartFeedbackActivity(View view) {
        this.fb.startFeedbackActivity();
    }
    public void testForceUpdate(View view) {
        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
            @Override
            public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {
                // 用完setUpdateListener后需要恢复为默认配置, 否则以后所有的更新都会用这个监听器
                UmengUpdateAgent.setDefault();

                switch (updateStatus) {
                    case UpdateStatus.Yes: // has update
                        UmengUpdateAgent.showUpdateDialog(BaseMainActivity.this, updateInfo);
                        Toast.makeText(BaseMainActivity.this, "有更新", Toast.LENGTH_SHORT).show();
                        break;
                    case UpdateStatus.No: // has no update
                        Toast.makeText(BaseMainActivity.this, "已是最新版本", Toast.LENGTH_SHORT).show();
                        break;
                    case UpdateStatus.NoneWifi: // none wifi
                        Toast.makeText(BaseMainActivity.this, "没有wifi连接， 只在wifi下更新", Toast.LENGTH_SHORT).show();
                        break;
                    case UpdateStatus.Timeout: // time out
                        Toast.makeText(BaseMainActivity.this, "超时", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        UmengUpdateAgent.forceUpdate(this);
    }
}
