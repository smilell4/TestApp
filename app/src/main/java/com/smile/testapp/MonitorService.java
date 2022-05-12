package com.smile.testapp;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import androidx.annotation.Nullable;


public class MonitorService extends Service {

    private ChargeMonitor chargeMonitor;
    private IntentFilter filter;
    private OnProgressListener onProgressListener;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyIBinder();
    }

    public class MyIBinder extends Binder {
        public String getTime() {
            return chargeMonitor.getWriteTime();
        }

    }

    public void setOnProgressListener(OnProgressListener onProgressListener) {
        this.onProgressListener = onProgressListener;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TAG", "Service-->onStartCommand()");
        if (chargeMonitor == null && filter == null) {
            Constants.isStart = true;
            chargeMonitor = new ChargeMonitor();
            filter = new IntentFilter(Constants.UPDATE_BATTERY_STATE_ACTION);
            getBaseContext().registerReceiver(chargeMonitor, filter);
            Log.d("TAG", "registerReceiver-->start");
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TAG", "Service-->onCreate()");
    }

    @Override
    public void onDestroy() {
        if (chargeMonitor != null) {
            getBaseContext().unregisterReceiver(chargeMonitor);
        }
        Log.d("TAG", "Service-->onDestroy()");
        super.onDestroy();

    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
    }


}
