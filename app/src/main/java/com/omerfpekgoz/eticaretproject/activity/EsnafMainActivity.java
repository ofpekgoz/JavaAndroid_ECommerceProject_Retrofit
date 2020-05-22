package com.omerfpekgoz.eticaretproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.omerfpekgoz.eticaretproject.R;

public class EsnafMainActivity extends AppCompatActivity {

    private Toolbar toolbarEsnafMain;
    private Button btnSiparisler,btnUrunlerim,btnUrunEkle,btnUrunGuncelle,btnUrunSil;

    private Animation animationSoldanGelen,animationSagdanGelen,animationYukardanGelen;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esnaf_main);

        toolbarEsnafMain=findViewById(R.id.toolbarEsnafMain);

        btnSiparisler=findViewById(R.id.btnSiparisler);
        btnUrunlerim=findViewById(R.id.btnUrunlerim);
        btnUrunEkle=findViewById(R.id.btnUrunEkle);
        btnUrunGuncelle=findViewById(R.id.btnUrunGuncelle);
        btnUrunSil=findViewById(R.id.btnUrunSil);


        animation();  //Animasyon İşlemleri çağırdık

        btnUrunEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EsnafMainActivity.this,UrunEkleActivity.class));
            }
        });

        btnUrunlerim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EsnafMainActivity.this,UrunlerimActivity.class));
            }
        });

        btnUrunGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EsnafMainActivity.this,UrunGuncelleActivity.class));
            }
        });
        btnUrunSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EsnafMainActivity.this,UrunSilActivity.class));
            }
        });


        toolbarEsnafMain.setTitle("");
        setSupportActionBar(toolbarEsnafMain);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    public void animation(){


        animationSagdanGelen= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_sagdan_gelen);
        animationSoldanGelen= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_soldan_gelen);
        animationYukardanGelen= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_yukardan_gelen);

        btnSiparisler.setAnimation(animationSoldanGelen);
        btnUrunlerim.setAnimation(animationSagdanGelen);
        btnUrunEkle.setAnimation(animationSoldanGelen);
        btnUrunGuncelle.setAnimation(animationSagdanGelen);
        btnUrunSil.setAnimation(animationSoldanGelen);


    }
}
