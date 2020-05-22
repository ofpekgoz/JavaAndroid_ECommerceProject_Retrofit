package com.omerfpekgoz.eticaretproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.omerfpekgoz.eticaretproject.R;
import com.omerfpekgoz.eticaretproject.model.CRUDResponse;
import com.omerfpekgoz.eticaretproject.model.UyeLogin;
import com.omerfpekgoz.eticaretproject.network.APIUtils;
import com.omerfpekgoz.eticaretproject.service.IUrunlerDao;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UrunEkleActivity extends AppCompatActivity {

    private Toolbar toolbarUrunEkle;
    private EditText txtUrunAdi,txtUrunKategoriAdi,txtUrunSatisFiyati,txtUrunStokDurumu;
    private Button btnUrunEkle;

    private IUrunlerDao urunlerDao;

    private Animation animationSoldanGelen,animationSagdanGelen,animationYukardanGelen,animAsagidanYukari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_ekle);

        toolbarUrunEkle=findViewById(R.id.toolbarUrunGuncelle);
        txtUrunAdi=findViewById(R.id.txtUrunAdi);
        txtUrunKategoriAdi=findViewById(R.id.txtUrunKategoriAdi);
        txtUrunSatisFiyati=findViewById(R.id.txtUrunSatisFiyati);
        txtUrunStokDurumu=findViewById(R.id.txtUrunStokDurumu);
        btnUrunEkle=findViewById(R.id.btnUrunSil);

        animation();

        urunlerDao= APIUtils.getUrunlerDao();

        toolbarUrunEkle.setTitle("");
        setSupportActionBar(toolbarUrunEkle);

        btnUrunEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urunEkle();
            }
        });


    }

    public void urunEkle(){

        if (TextUtils.isEmpty(txtUrunAdi.getText().toString())) {
            Snackbar.make(btnUrunEkle, "Ürün Adı Giriniz", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(txtUrunKategoriAdi.getText().toString())) {
            Snackbar.make(btnUrunEkle, "Ürün Kategorisini Giriniz", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(txtUrunSatisFiyati.getText().toString())) {
            Snackbar.make(btnUrunEkle, "Ürün Satış Fiyarını Giriniz", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(txtUrunStokDurumu.getText().toString())) {
            Snackbar.make(btnUrunEkle, "Ürün Stok Durmunu Giriniz", Snackbar.LENGTH_SHORT).show();
            return;
        }


        String urun_ad=txtUrunAdi.getText().toString().trim();
        String urun_kategori=txtUrunKategoriAdi.getText().toString().trim();
        float urun_satisfiyat=Float.parseFloat(txtUrunSatisFiyati.getText().toString().trim());
        int urun_stokdurumu=Integer.parseInt(txtUrunStokDurumu.getText().toString().trim());
        int urun_esnafid= UyeLogin.uye_id;

        Log.e("ad",urun_ad);
        Log.e("urun_kategori",urun_kategori);
        Log.e("urun_satisfiyat", String.valueOf(urun_satisfiyat));
        Log.e("urun_stokdurumu", String.valueOf(urun_stokdurumu));
        Log.e("urun_esnafid", String.valueOf(urun_esnafid));






        urunlerDao.urunEkle(urun_ad,urun_kategori,urun_satisfiyat,urun_stokdurumu,urun_esnafid).enqueue(new Callback<CRUDResponse>() {
            @Override
            public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {

                if (response.body().getSuccess()==1){
                    Toast.makeText(getApplicationContext(), "Ürün Eklendi", Toast.LENGTH_LONG).show();
                    textTemizle();
                }else{
                    Toast.makeText(getApplicationContext(), "Ürün Ekleme Başarısız", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<CRUDResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ürün Ekleme Başarısız", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void textTemizle(){
        txtUrunAdi.setText("");
        txtUrunKategoriAdi.setText("");
        txtUrunSatisFiyati.setText("");
        txtUrunStokDurumu.setText("");

    }

    public void animation() {


        animationSagdanGelen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_sagdan_gelen);
        animationSoldanGelen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_soldan_gelen);
        animationYukardanGelen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_yukardan_gelen);
        animAsagidanYukari = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_asagidan_yukari);

        txtUrunAdi.setAnimation(animationSoldanGelen);
        txtUrunKategoriAdi.setAnimation(animationSagdanGelen);
        txtUrunSatisFiyati.setAnimation(animationSoldanGelen);
        txtUrunStokDurumu.setAnimation(animationSagdanGelen);
        btnUrunEkle.setAnimation(animAsagidanYukari);


    }
}
