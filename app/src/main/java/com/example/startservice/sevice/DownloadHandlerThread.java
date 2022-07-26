package com.example.startservice.sevice;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.util.Log;

import androidx.annotation.NonNull;

public class DownloadHandlerThread extends HandlerThread {
    private Handler handler;
    private MyService myService;

    private static final String TAG = "Azeem";
    public DownloadHandlerThread() {
        super("back", Process.THREAD_PRIORITY_BACKGROUND);
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onLooperPrepared() {
        handler = new Handler(){
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(@NonNull Message msg) {
                    Log.d(TAG, ":"+Thread.currentThread());
                    Log.d(TAG, "downloading started"+msg.obj.toString());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "downloadSongs:Service task ended");
            }
        };
    }



    public Handler getHandler() {
        return handler;

    }

    public void setMyService(MyService myService) {
        this.myService = myService;
    }
}
