package com.example.startservice;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.RecoverySystem;
import android.os.ResultReceiver;
import android.widget.TextView;

import com.example.startservice.sevice.MyService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String AZEEM = "azeem";
    public static final String MESSAGE_KEY = "RESULT";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(view -> {
            MyResultReceiver myResultReceiver = new MyResultReceiver(null);
            for (String song: Songs.songs) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                intent.putExtra(AZEEM,song);
                intent.putExtra(Intent.EXTRA_RESULT_RECEIVER,myResultReceiver);
                startService(intent);
            }
        });

    }

    class MyResultReceiver extends ResultReceiver {

        public MyResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == RESULT_OK && resultData != null){
                String a = resultData.getString(MESSAGE_KEY);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.append(a);
                    }
                });
            }
        }
    }
}