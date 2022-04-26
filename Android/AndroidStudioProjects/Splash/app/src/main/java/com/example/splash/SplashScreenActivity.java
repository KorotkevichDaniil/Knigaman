package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 5000;
    private Thread mSplashThread;
    private Timer timer;
    private MyTimerTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // TODO:Упражнение 1 (просто задержка на 5 секунд)
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);*/

        // TODO:Упражнение 2 (использование потока)
        /*mSplashThread = new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (this){
                        wait(SPLASH_TIME_OUT);
                    }
                }catch (InterruptedException e){

                }
                finally {
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                    interrupt();
                }
            }
        };
        mSplashThread.start();*/

        // TODO:Упражнение 3 (использование таймера)
        timer = new Timer();
        task = new MyTimerTask();
        timer.schedule(task, 3000);
    }

    class MyTimerTask extends TimerTask{
        @Override
        public void run() {
            timer.cancel();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Три секунды прошло", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}