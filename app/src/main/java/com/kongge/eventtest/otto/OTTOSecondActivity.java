package com.kongge.eventtest.otto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kongge.eventtest.EventData;
import com.kongge.eventtest.R;
import com.squareup.otto.Subscribe;


public class OTTOSecondActivity extends AppCompatActivity {

    public static final String TAG = "testLog";

    private EditText edittext;
    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otto_second_activity);
        edittext = (EditText) findViewById(R.id.edittext);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goback();
            }
        });

        Log.i(TAG, "OTTOSecondActivity onCreate:BusProvider.getInstance().register(this)");
        BusProvider.getInstance().register(this);   //注册事件
    }

    @Subscribe
    public void subscribeEvent(EventData data){
        Log.i(TAG, "OTTOSecondActivity subscribeEvent:" + data.getContent());
    }

    private void goback() {
        String inputStr = edittext.getText().toString();

        EventData eventData = new EventData();
        eventData.setContent(inputStr);

        Log.i(TAG, "OTTOSecondActivity goback:BusProvider.getInstance().post---->" + inputStr);
        BusProvider.getInstance().post(eventData);
        finish();
    }

    public void sendMsg() {
        EventData eventData = new EventData();
        eventData.setContent("test msg");
        BusProvider.getInstance().post(eventData);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "OTTOSecondActivity onCreate:BusProvider.getInstance().unregister(this)");
        BusProvider.getInstance().unregister(this); //取消订阅
    }
}
