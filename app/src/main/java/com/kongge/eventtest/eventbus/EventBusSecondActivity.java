package com.kongge.eventtest.eventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kongge.eventtest.EventData;
import com.kongge.eventtest.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class EventBusSecondActivity extends AppCompatActivity{

    public static final String TAG = "testLog";

    private EditText edittext;
    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_second_activity);

        edittext = (EditText) findViewById(R.id.edittext);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goback();
            }
        });
        EventBus.getDefault().register(this);
        Log.i(TAG, "EventBusSecondActivity onCreate:EventBus.getDefault().register(this)");
    }

    private void goback() {
        String inputStr = edittext.getText().toString();

        EventData eventData = new EventData();
        eventData.setContent(inputStr);

        /**
         * 参数自己定义，如果接受方法的类型与post的参数类型不一致，是不会接收到消息的
         */
        EventBus.getDefault().post(inputStr);
        Log.i(TAG, "EventBusSecondActivity goback:BusProvider.getInstance().post---->" + inputStr);
        finish();
    }

    /**
     * 需要使用注解@Subscribe，与方法名无关
     * @param eventData
     */
    @Subscribe
    public void onEventccc(EventData eventData) {

        Log.i(TAG, "EventBusSecondActivity onEventccc:" + eventData.getContent());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Log.i(TAG, "EventBusSecondActivity onCreate:EventBus.getDefault().unregister(this)");
    }
}
