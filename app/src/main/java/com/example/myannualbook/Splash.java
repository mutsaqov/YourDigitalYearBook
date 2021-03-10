package com.example.myannualbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    private TextView tv_1, tv_2, tv_3;
    private ImageView iv;
    private ProgressBar pb;

    private View satu, dua, tiga, empat, lima;
    //animation
    Animation topAnimation, bottomAnimation, middleAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        tv_1 = (TextView) findViewById(R.id.textatas);
        tv_2 = (TextView) findViewById(R.id.textbwh);
        tv_3 = findViewById(R.id.welcome);

        iv = (ImageView) findViewById(R.id.val);
        pb = findViewById(R.id.pbar);

        satu = findViewById(R.id.firstline);
        dua = findViewById(R.id.secondline);
        tiga = findViewById(R.id.thirdline);
        empat = findViewById(R.id.fourthline);
        lima = findViewById(R.id.fifthline);


        //Animations
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_anim);
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.mid_anim);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);


        //Load The Animations
        satu.setAnimation(topAnimation);
        dua.setAnimation(topAnimation);
        tiga.setAnimation(topAnimation);
        empat.setAnimation(topAnimation);
        lima.setAnimation(topAnimation);

        iv.setAnimation(middleAnimation);
        tv_1.setAnimation(middleAnimation);
        tv_2.setAnimation(middleAnimation);
        tv_3.setAnimation(bottomAnimation);

        pb.setAnimation(bottomAnimation);


        final Intent intent = new Intent(this, teacher_register.class);
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
