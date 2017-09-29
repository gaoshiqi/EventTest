package com.kongge.eventtest.otto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kongge.eventtest.EventData;
import com.kongge.eventtest.R;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;


public class OTTOMainActivity extends AppCompatActivity {

    public static final String TAG = "testLog";

    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otto_main_activity);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnClick();
            }
        });

        BusProvider.getInstance().register(this);   //注册事件
    }

    private void onBtnClick() {
        startActivity(new Intent(this, OTTOSecondActivity.class));
    }

    /**
     * 必须要有返回值,当其他地方注册的时候，会立马回调该方法
     * @return
     */
    @Produce
    public EventData providerEvent(){
        String content = "hello world from OTTOMainActivity";
        Log.i(TAG, "OTTOMainActivity providerEvent:" + content);
        EventData eventData = new EventData();
        eventData.setContent(content);
        return eventData;
    }

    @Subscribe
    public void subscribeEvent(EventData data){
        Log.i(TAG, "OTTOMainActivity subscribeEvent:" + data.getContent());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this); //取消订阅
    }

}
