package com.omerfpekgoz.eticaretproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.omerfpekgoz.eticaretproject.R;
import com.omerfpekgoz.eticaretproject.model.UyeLogin;
import com.omerfpekgoz.eticaretproject.model.Uyeler;
import com.omerfpekgoz.eticaretproject.model.UyelerResponse;
import com.omerfpekgoz.eticaretproject.network.APIUtils;
import com.omerfpekgoz.eticaretproject.service.IUyelerDao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ImageView imgOfp;
    private Button btnGirisYap, btnKayitOl;
    private EditText txtEmail, txtSifre;

    private IUyelerDao uyelerDao;

    private Animation animationSoldanGelen, animationSagdanGelen, animationYukardanGelen, animAsagidanYukari;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imgOfp = findViewById(R.id.imgOfp);
        btnGirisYap = findViewById(R.id.btnGirisYap);
        btnKayitOl = findViewById(R.id.btnKayitOl);
        txtEmail = findViewById(R.id.txtEmail);
        txtSifre = findViewById(R.id.txtSifre);

        animation();

        uyelerDao = APIUtils.getUyelerDao();

        btnKayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, KayitOlActivity.class));
                finish();
            }
        });

        btnGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uyeGiris();
            }
        });
    }

    public void uyeGiris() {

        String e_posta = txtEmail.getText().toString().trim();
        String sifre = txtSifre.getText().toString().trim();
        if (TextUtils.isEmpty(e_posta)) {
            Snackbar.make(btnKayitOl, "E-mail Giriniz", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(sifre)) {
            Snackbar.make(btnKayitOl, "Şifre Giriniz", Snackbar.LENGTH_SHORT).show();
            return;
        }

        uyelerDao.uyeGiris(e_posta, sifre).enqueue(new Callback<UyelerResponse>() {
            @Override
            public void onResponse(Call<UyelerResponse> call, Response<UyelerResponse> response) {

                List<Uyeler> uyelerList = new ArrayList<>();

                uyelerList = response.body().getUyeler();
                if (response.body().getSuccess() == 1) {
                    Toast.makeText(getApplicationContext(), "Giriş Başarılı", Toast.LENGTH_LONG).show();
                }

                for (Uyeler uye : uyelerList) {
                    UyeLogin.uye_id = Integer.valueOf(uye.getId());

                    Log.e("Kayıt", "" + uye.getUyeKayitturu());

                    if (uye.getUyeKayitturu().equals("0")) {   //Esnaf ise

                        startActivity(new Intent(LoginActivity.this, EsnafMainActivity.class));
                        finish();
                    } else {       //Müiteri ise
                        startActivity(new Intent(LoginActivity.this, MusteriMainActivity.class));
                        finish();
                    }

                }
            }

            @Override
            public void onFailure(Call<UyelerResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Böyle Bir Kullanıcı Bulunmamaktadır", Toast.LENGTH_LONG).show();
            }

        });


    }

    public void animation() {


        animationSagdanGelen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_sagdan_gelen);
        animationSoldanGelen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_soldan_gelen);
        animationYukardanGelen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_yukardan_gelen);
        animAsagidanYukari = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_asagidan_yukari);

        imgOfp.setAnimation(animationYukardanGelen);
        txtSifre.setAnimation(animationSagdanGelen);
        txtEmail.setAnimation(animationSoldanGelen);
        btnGirisYap.setAnimation(animAsagidanYukari);
        btnKayitOl.setAnimation(animAsagidanYukari);

    }
}

