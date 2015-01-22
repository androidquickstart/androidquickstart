package com.github.ufologist.android;

import android.app.Activity;
import android.os.Bundle;

import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

/**
 * 基础Activity, 已经实现了友盟的session统计
 * 
 * @author Sun
 * @version 2015-01-09
 */
public class BaseActivity extends Activity {
    private String activityName = "BaseActivity";
    private static final String STATUS_ONRESUME = "onResume";
    private static final String STATUS_ONPAUSE = "onPause";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.activityName = getClass().getSimpleName();
        analyseAppLaunch();
    }
    @Override
    public void onResume() {
        super.onResume();
        analyseSession(STATUS_ONRESUME);
    }
    @Override
    public void onPause() {
        super.onPause();
        analyseSession(STATUS_ONPAUSE);
    }

    /**
     * 友盟消息推送服务需要统计应用启动数据
     * 在所有的Activity 的onCreate 函数添加
     * 注意: 如果不调用此方法，将会导致按照"几天不活跃"条件来推送失效。
     */
    protected void analyseAppLaunch() {
        PushAgent.getInstance(this).onAppStart();
    }
    
    /**
     * session的统计
     * 
     * @param status
     */
    protected void analyseSession(String status) {
        if (status.equals(STATUS_ONRESUME)) {
            MobclickAgent.onResume(this);
        } else {
            MobclickAgent.onPause(this);
        }
    }

    public String getOnlineConfigParam(String name) {
        // 获取友盟在线参数, 会有延时或者说是缓存, 不是在页面上改了, 下次进app就会立马看见修改后的内容
        return MobclickAgent.getConfigParams(this, name);
    }

    public String getActivityName() {
        return this.activityName;
    }
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
