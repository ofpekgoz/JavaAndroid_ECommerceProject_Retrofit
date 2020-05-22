package com.omerfpekgoz.eticaretproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.omerfpekgoz.eticaretproject.R;

public class MainActivity extends AppCompatActivity {

    private ImageView imgOfp,imgResim1,imgResim2;


    private static int GECİS_SURESİ=3000;


    private Animation animationSoldanGelen, animationSagdanGelen, animationYukardanGelen, animAsagidanYukari,animScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgOfp=findViewById(R.id.imgOfp);

        imgResim1=findViewById(R.id.imgResim1);
        imgResim2=findViewById(R.id.imgResim2);

        animation();


        //Splash Screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
            }
        },GECİS_SURESİ);



    }
    public void animation() {


        animationSagdanGelen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_sagdan_gelen);
        animationSoldanGelen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_soldan_gelen);
        animationYukardanGelen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_yukardan_gelen);
        animAsagidanYukari = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_asagidan_yukari);
        animScale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_scale);

        imgResim1.setAnimation(animationYukardanGelen);
        imgResim2.setAnimation(animAsagidanYukari);
        imgOfp.setAnimation(animScale);

    }
}
