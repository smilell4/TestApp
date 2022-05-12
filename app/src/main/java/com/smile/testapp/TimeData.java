package com.smile.testapp;

import android.content.Context;
import android.content.SharedPreferences;

public class TimeData {
    private Context context;
    private static SharedPreferences timeSP;
    private static TimeData getTimeDate;

    private TimeData(Context context) {
        this.context = context;
        timeSP = context.getSharedPreferences("TimeSP", Context.MODE_PRIVATE);
    }

    public static TimeData getGetTimeDate(Context context) {
        if (getTimeDate == null) {
            synchronized (TimeData.class) {
                if (getTimeDate == null) {
                    return new TimeData(context);
                }
            }
        }
        return getTimeDate;
    }

    public void write(String value, int type) {

        if (type == Constants.TYPE_CHARGE) {
            timeSP.edit().putString("charge_time", value).apply();
        } else if (type == Constants.TYPE_DISCHARGE) {
            timeSP.edit().putString("discharge_time", value).apply();
        }

    }

    public String getTime(String key) {

        return timeSP.getString(key, "暂无数据！");
    }
}
