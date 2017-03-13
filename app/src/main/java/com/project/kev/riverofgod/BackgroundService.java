package com.project.kev.riverofgod;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;



public class BackgroundService extends Service {
    private Timer timer=new Timer();
    AvailableMatActivity.AsyncHttpTask asyncHttpTask;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
               // asyncHttpTask=new AvailableMatActivity.AsyncHttpTask();
                asyncHttpTask.execute();
            }
        },0,5*60*1000);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
