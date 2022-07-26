package com.example.startservice.sevice;

import static com.example.startservice.MainActivity.AZEEM;

import android.app.Service;
import android.app.appsearch.SearchResult;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import com.example.startservice.DownloadHandler;
import com.example.startservice.DownloadThread;
import com.example.startservice.MainActivity;

public class MyService extends Service {

    DownloadThread downloadThread;
    private static final String TAG = "azeem";

    @Override
    public void onCreate() {
        downloadThread = new DownloadThread();
        downloadThread.start();
        while (downloadThread.mHandler == null){}
        downloadThread.mHandler.setMyService(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        Log.d(TAG, "onStartCommand: called");
        final String song = intent.getStringExtra(AZEEM);
        downloadThread.mHandler.setResultReceiver(intent.getParcelableExtra(Intent.EXTRA_RESULT_RECEIVER));
        Message message = Message.obtain();
        message.obj = song;
        message.arg1 = startId;
        downloadThread.mHandler.handleMessage(message);
        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: called");
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        Log.d(TAG, "onDestroy: called");
//        downloadHandlerThread.quit();
    }
}