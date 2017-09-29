package com.kongge.eventtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kongge.eventtest.eventbus.EventBusMainActivity;
import com.kongge.eventtest.otto.OTTOMainActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "testLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnEventBus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EventBusMainActivity.class));
            }
        });
        findViewById(R.id.btnOTTO).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OTTOMainActivity.class));
            }
        });
    }
}
