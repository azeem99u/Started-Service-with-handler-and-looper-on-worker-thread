package com.example.startservice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ResultReceiver;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.startservice.sevice.MyService;

public class DownloadHandler extends Handler {
    public void setResultReceiver(ResultReceiver resultReceiver) {
        this.resultReceiver = resultReceiver;
    }

    ResultReceiver resultReceiver;
    public DownloadHandler(){

    }
    private static final String TAG = "azeem";
    private MyService myService;
    @Override
    public void handleMessage(@NonNull Message msg) {
        downloadSongs(msg.obj.toString());
        myService.stopSelf(msg.arg1);
        String s = msg.obj.toString();
        Bundle bundle = new Bundle();
        bundle.putString(MainActivity.MESSAGE_KEY,s);
        resultReceiver.send(MainActivity.RESULT_OK,bundle);
    }

    private void downloadSongs(String song) {
        Log.d(TAG, "downloading started"+song);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "downloadSongs:Service task ended");
    }

    public void setMyService(MyService myService) {
        this.myService = myService;
    }

}
