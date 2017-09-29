package com.kongge.eventtest.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.kongge.eventtest.EventData;
import com.kongge.eventtest.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class EventBusMainActivity extends AppCompatActivity {

    public static final String TAG = "testLog";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventbus_main_activity);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventBusMainActivity.this, EventBusSecondActivity.class));
            }
        });


        EventBus.getDefault().register(this);

    }

    /**
     * 1.需要使用注解@Subscribe，如果没有使用注解，会报错
     * 2.与方法名无关
     * 3.参数自己设置，可以是自定义的JavaBean
     * @param
     */
    @Subscribe
    public void onEventaaa(Object object) {
        if (object instanceof EventData) {
            Log.i(TAG, "EventBusMainActivity onEventaaa:" + ((EventData)object).getContent());
        }

    }

    @Subscribe
    public void onEventbbb(EventData eventData) {
        Log.i(TAG, "EventBusMainActivity onEventbbb:" + eventData.getContent());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
