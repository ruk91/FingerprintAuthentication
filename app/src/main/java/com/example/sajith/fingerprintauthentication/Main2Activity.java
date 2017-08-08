package com.example.sajith.fingerprintauthentication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class Main2Activity extends AppCompatActivity {

    Handler handler;
    Runnable r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        handler = new Handler();
        r = new Runnable() {

            @Override
            public void run() {

                Toast.makeText(Main2Activity.this, "You were inactive for 5 seconds",Toast.LENGTH_SHORT).show();

                int pid = android.os.Process.myPid();
                android.os.Process.killProcess(pid);
                moveTaskToBack(true);
                finish();
            }
        };
        startHandler();
    }


    public void onBackPressed() {

        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);

        moveTaskToBack(true);
        finish();

    }

    @Override
    public void onUserInteraction() {

        super.onUserInteraction();
        stopHandler();//stop first and then start
        startHandler();
    }
    public void stopHandler() {
        handler.removeCallbacks(r);
    }
    public void startHandler() {
        handler.postDelayed(r, 5000); //for 5 secs
    }


}
