package com.smile.testapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.Date;

public class ChargeMonitor extends BroadcastReceiver {

    private Date startDate;
    private String writeTime;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        int batteryState = extras.getInt(Constants.EXTRA_BATTERY_STATE);
        if (batteryState == 0) {
            write(context);

        }

    }

    private void write(Context context) {
        if (Constants.isStart) {
            startDate = new Date();
            Constants.isStart = false;
        }

        Date date = new Date();
        int time = (int) (date.getTime() - startDate.getTime()) / 1000 / 60;

        String writer;
        if (time > 60) {
            int hour = time / 60;
            int minute = time - hour * 60;
            writer = hour + "小时" + minute  + "分钟";
        } else {
            writer = time + "分钟";
        }
        Log.d("TAG", "-------------" + writer);
        TimeData.getGetTimeDate(context).write(writer, Constants.TYPE_CHARGE);
        writeTime = writer;
    }

    public String getWriteTime() {
        return writeTime;
    }
}
