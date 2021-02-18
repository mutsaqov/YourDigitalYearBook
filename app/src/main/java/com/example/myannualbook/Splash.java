package com.example.myannualbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    private TextView tv_1, tv_2, tv_3, tv_4;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tv_1 = (TextView) findViewById(R.id.textatas);
        tv_2 = (TextView) findViewById(R.id.text2020);
        tv_3 = (TextView) findViewById(R.id.by);
        tv_4 = (TextView) findViewById(R.id.freeze);

        iv = (ImageView) findViewById(R.id.val);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.transition);
        tv_1.startAnimation(animation);
        iv.startAnimation(animation);
        tv_2.startAnimation(animation);
        tv_3.startAnimation(animation);
        tv_4.startAnimation(animation);

        final Intent intent = new Intent(this, Register.class);
        Thread timer = new Thread() {
            public void run() {
             try {
                 sleep(4000);

             }catch (InterruptedException e) {
                 e.printStackTrace();
             }
             finally {
                 startActivity(intent);
                 finish();
             }

            }
        };
        timer.start();
    }
}
