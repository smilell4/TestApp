package com.smile.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private Button mChargeButt, mDischargeButt;
    public TextView mOutputText;
    private Intent intent;
//    public static updateUIHandler upDateUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inIt();
    }

    @SuppressLint("SetTextI18n")
    private void inIt() {
        mChargeButt = findViewById(R.id.charge_time);
        mDischargeButt = findViewById(R.id.discharge_time);
        mOutputText = findViewById(R.id.text_output);
        mChargeButt.setOnClickListener(this::onClick);
        mDischargeButt.setOnClickListener(this::onClick);
        mOutputText.setText("上次充电：" + TimeData.getGetTimeDate(this).getTime("charge_time"));
    }

    @SuppressLint("NonConstantResourceId")
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.charge_time:
                if ("停止充电记录".contentEquals(mChargeButt.getText())) {
                    mChargeButt.setText(R.string.charge_time_butt);
                    stopService(intent);

                } else {
                    mChargeButt.setText("停止充电记录");
                    intent = new Intent(this, MonitorService.class);
                    intent.putExtra("action", "charge");
                    startService(intent);
                }

                break;
            case R.id.discharge_time:

                break;
        }
    }

    private void binService() {
        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }


}