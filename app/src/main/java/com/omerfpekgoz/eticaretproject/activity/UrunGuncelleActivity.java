package com.omerfpekgoz.eticaretproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.omerfpekgoz.eticaretproject.R;
import com.omerfpekgoz.eticaretproject.model.CRUDResponse;
import com.omerfpekgoz.eticaretproject.model.Urunler;
import com.omerfpekgoz.eticaretproject.model.UrunlerResponse;
import com.omerfpekgoz.eticaretproject.model.UyeLogin;
import com.omerfpekgoz.eticaretproject.network.APIUtils;
import com.omerfpekgoz.eticaretproject.service.IUrunlerDao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UrunGuncelleActivity extends AppCompatActivity {

    private Toolbar toolbarUrunGuncelle;
    private Spinner spinKategoriSec, spinUrunAdiSec;
    private EditText txtUrunSatisFiyati, txtUrunStokDurumu;
    private Button btnUrunGuncelle;
    private TextView txtKategori,txtUrunAdi;

    private ArrayList<String> kategoriler = new ArrayList<>();
    private ArrayAdapter<String> kategoriAdapter;

    private ArrayList<String> urunAdlari = new ArrayList<>();
    private ArrayAdapter<String> urunAdlariAdapter;


    private List<Urunler> urunlerList;

    private IUrunlerDao urunlerDao;

    private String secilenKategori;
    private int urun_id;

    private Animation animationSoldanGelen, animationSagdanGelen, animationYukardanGelen, animAsagidanYukari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_guncelle);

        toolbarUrunGuncelle = findViewById(R.id.toolbarUrunGuncelle);
        spinKategoriSec = findViewById(R.id.spinKategoriSec);
        spinUrunAdiSec = findViewById(R.id.spinUrunAdiSec);
        txtUrunSatisFiyati = findViewById(R.id.txtUrunSatisFiyati);
        txtUrunStokDurumu = findViewById(R.id.txtUrunStokDurumu);
        btnUrunGuncelle = findViewById(R.id.btnUrunGuncelle);
        txtKategori = findViewById(R.id.txtKategori);
        txtUrunAdi = findViewById(R.id.txtUrunAdi);

        animation();

        urunlerDao = APIUtils.getUrunlerDao();

        toolbarUrunGuncelle.setTitle("");
        setSupportActionBar(toolbarUrunGuncelle);

        kategoriSec();

        spinKategoriSec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                urunAdlari.clear();    //Her Spinner çalıştığında liste yenilecek
                secilenKategori = kategoriler.get(position);
                urunAdiSec(secilenKategori);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinUrunAdiSec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                urunlerList.clear();
                String secilenUrunAdi = urunAdlari.get(position);
                urunBilgilerGetir(secilenUrunAdi);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnUrunGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urunGuncelle();
            }
        });
    }

    public void kategoriSec() {

        int urun_esafid = UyeLogin.uye_id;

        urunlerDao.kategoriAdByEsnafId(urun_esafid).enqueue(new Callback<UrunlerResponse>() {
            @Override
            public void onResponse(Call<UrunlerResponse> call, Response<UrunlerResponse> response) {

                urunlerList = new ArrayList<>();

                urunlerList = response.body().getUrunler();

                for (Urunler urun : urunlerList) {
                    kategoriler.add(urun.getUrunKategori());
                }

                kategoriAdapter = new ArrayAdapter<String>(UrunGuncelleActivity.this,R.layout.spinner_item, android.R.id.text1, kategoriler);
                kategoriAdapter.setDropDownViewResource(R.layout.spinner_droptown1);
                spinKategoriSec.setAdapter(kategoriAdapter);
            }

            @Override
            public void onFailure(Call<UrunlerResponse> call, Throwable t) {

            }
        });


    }

    public void urunAdiSec(String secilenKategori) {

        final int urun_esnafId = UyeLogin.uye_id;

        urunlerDao.urunAdlariByKategoriAd(urun_esnafId, secilenKategori).enqueue(new Callback<UrunlerResponse>() {
            @Override
            public void onResponse(Call<UrunlerResponse> call, Response<UrunlerResponse> response) {

                urunlerList = new ArrayList<>();
                urunlerList = response.body().getUrunler();

                for (Urunler urun : urunlerList) {
                    urunAdlari.add(urun.getUrunAd());
                }

                urunAdlariAdapter = new ArrayAdapter<String>(UrunGuncelleActivity.this,R.layout.spinner_item, android.R.id.text1, urunAdlari);
                urunAdlariAdapter.setDropDownViewResource(R.layout.spinner_droptown1);
                spinUrunAdiSec.setAdapter(urunAdlariAdapter);
            }

            @Override
            public void onFailure(Call<UrunlerResponse> call, Throwable t) {

            }
        });

    }

    public void urunBilgilerGetir(String secilenUrunAdi) {

        int urun_esnafid = UyeLogin.uye_id;

        urunlerDao.bilgilerByUrunAdi(urun_esnafid, secilenKategori, secilenUrunAdi).enqueue(new Callback<UrunlerResponse>() {
            @Override
            public void onResponse(Call<UrunlerResponse> call, Response<UrunlerResponse> response) {

                urunlerList = new ArrayList<>();
                urunlerList = response.body().getUrunler();

                for (Urunler urun : urunlerList) {
                    urun_id = Integer.parseInt(urun.getId());
                    txtUrunSatisFiyati.setText(urun.getUrunSatisfiyat());
                    txtUrunStokDurumu.setText(urun.getUrunStokdurumu());
                }

            }

            @Override
            public void onFailure(Call<UrunlerResponse> call, Throwable t) {

            }
        });

    }

    public void urunGuncelle() {

        if (TextUtils.isEmpty(txtUrunSatisFiyati.getText())) {
            Snackbar.make(btnUrunGuncelle, "Satış Fiyatı Giriniz", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(txtUrunStokDurumu.getText())) {
            Snackbar.make(btnUrunGuncelle, "Stok Durumu Giriniz", Snackbar.LENGTH_SHORT).show();
            return;
        }

        float urun_satisfiyat = Float.parseFloat(txtUrunSatisFiyati.getText().toString());
        int urun_stokdurumu = Integer.parseInt(txtUrunStokDurumu.getText().toString());


        urunlerDao.urunGuncelle(urun_id, urun_satisfiyat, urun_stokdurumu).enqueue(new Callback<CRUDResponse>() {
            @Override
            public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {
                if (response.body().getSuccess() == 1) {
                    Toast.makeText(UrunGuncelleActivity.this, "Ürün Güncellendi", Toast.LENGTH_SHORT);
                    txtUrunSatisFiyati.setText("");
                    txtUrunStokDurumu.setText("");
                }
            }

            @Override
            public void onFailure(Call<CRUDResponse> call, Throwable t) {

                Toast.makeText(UrunGuncelleActivity.this, "Ürün Güncellenemedi", Toast.LENGTH_SHORT);
            }
        });
    }

    public void animation() {


        animationSagdanGelen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_sagdan_gelen);
        animationSoldanGelen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_soldan_gelen);
        animationYukardanGelen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_yukardan_gelen);
        animAsagidanYukari = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_asagidan_yukari);

        spinKategoriSec.setAnimation(animationSoldanGelen);
        txtKategori.setAnimation(animationSoldanGelen);
        spinUrunAdiSec.setAnimation(animationSagdanGelen);
        txtUrunAdi.setAnimation(animationSagdanGelen);
        txtUrunSatisFiyati.setAnimation(animationSoldanGelen);
        txtUrunStokDurumu.setAnimation(animationSagdanGelen);
        btnUrunGuncelle.setAnimation(animAsagidanYukari);


    }
}
