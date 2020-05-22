package com.omerfpekgoz.eticaretproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.omerfpekgoz.eticaretproject.R;
import com.omerfpekgoz.eticaretproject.model.CRUDResponse;
import com.omerfpekgoz.eticaretproject.network.APIUtils;
import com.omerfpekgoz.eticaretproject.service.IUyelerDao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KayitOlActivity extends AppCompatActivity {

    private EditText txtEmailKayit, txtSifreKayit, txtTelNoKayit;
    private Spinner spinHesapTuruKayit;
    private Button btnKayitOl, btnDogTarih;

    private List<String> hesaplar = new ArrayList<>();
    private ArrayAdapter<String> veriAdaptor;

    private IUyelerDao uyelerDao;

    private Animation animationSoldanGelen, animationSagdanGelen, animationYukardanGelen, animAsagidanYukari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);

        txtEmailKayit = findViewById(R.id.txtEmailKayit);
        txtSifreKayit = findViewById(R.id.txtSifreKayit);
        txtTelNoKayit = findViewById(R.id.txtTelNoKayit);
        btnDogTarih = findViewById(R.id.btnDogTarih);
        spinHesapTuruKayit = findViewById(R.id.spinHesapTuruKayit);
        btnKayitOl = findViewById(R.id.btnKayitOl);

        animation();

        uyelerDao = APIUtils.getUyelerDao();

        //Karakter sayıları için Filtre koyduk
        InputFilter[] filterArray = new InputFilter[3];
        filterArray[0] = new InputFilter.LengthFilter(10);  //Tel no
        filterArray[1] = new InputFilter.LengthFilter(16);   //Şifre
        filterArray[2] = new InputFilter.LengthFilter(50);   //Email

        txtTelNoKayit.setFilters(new InputFilter[]{filterArray[0]});
        txtSifreKayit.setFilters(new InputFilter[]{filterArray[1]});
        txtEmailKayit.setFilters(new InputFilter[]{filterArray[2]});


        hesaplar.add("Esnaf");
        hesaplar.add("Müşteri");

        veriAdaptor = new ArrayAdapter<String>(KayitOlActivity.this,R.layout.spinner_item, android.R.id.text1, hesaplar);
        veriAdaptor.setDropDownViewResource(R.layout.spinner_droptown1);
        spinHesapTuruKayit.setAdapter(veriAdaptor);

        spinHesapTuruKayit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String secilen = hesaplar.get(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnDogTarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar();
            }
        });

        btnKayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uyeEkle();
            }
        });
    }


    public void uyeEkle() {

        String uye_eposta = txtEmailKayit.getText().toString().trim();
        String uye_sifre = txtSifreKayit.getText().toString().trim();
        String uye_telno = txtTelNoKayit.getText().toString().trim();
        if (btnDogTarih.getText().toString().equals("Doğum Tarihiniz")) {
            Snackbar.make(btnKayitOl, "Doğum Tarihinizi Giriniz", Snackbar.LENGTH_SHORT).show();
            return;
        }
        java.sql.Date uye_dtarih = new java.sql.Date(java.util.Date.parse(btnDogTarih.getText().toString()));  //Doğum Tarihi aldık


        Boolean uye_kayitturu;

        if (spinHesapTuruKayit.getSelectedItemPosition() == 0) {  //Esnaf ise
            uye_kayitturu = false;
        } else {                                                   //Müşteri ise
            uye_kayitturu = true;
        }


        if (TextUtils.isEmpty(uye_eposta)) {
            Snackbar.make(btnKayitOl, "E-mail Giriniz", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(uye_sifre)) {
            Snackbar.make(btnKayitOl, "Şifrenizi  Giriniz", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(uye_telno)) {
            Snackbar.make(btnKayitOl, "Telefon Numaranızı Giriniz", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (uye_telno.length() < 10) {
            Snackbar.make(btnKayitOl, "Telefon Numarası 10 Haneli Olmalıdır", Snackbar.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(uye_kayitturu.toString())) {
            Snackbar.make(btnKayitOl, "Kayıt Türünüzü Seçiniz Giriniz", Snackbar.LENGTH_SHORT).show();
            return;
        }


        uyelerDao.uyeEkle(uye_eposta, uye_sifre, uye_telno, uye_dtarih, uye_kayitturu).enqueue(new Callback<CRUDResponse>() {
            @Override
            public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {

                if (response.body().getSuccess() == 1) {
                    Toast.makeText(getApplicationContext(), "Yeni Üye Kaydedildi", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(KayitOlActivity.this, LoginActivity.class));
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Yeni Üye Kaydetme Başarısız", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<CRUDResponse> call, Throwable t) {

            }
        });

    }

    //Takvim İşlemi
    public void calendar() {
        // Şimdiki zaman bilgilerini alıyoruz. güncel yıl, güncel ay, güncel gün.
        final Calendar takvim = Calendar.getInstance();
        int yil = takvim.get(Calendar.YEAR);
        int ay = takvim.get(Calendar.MONTH);
        int gun = takvim.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(KayitOlActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // ay değeri 0 dan başladığı için (Ocak=0, Şubat=1,..,Aralık=11)
                        // değeri 1 artırarak gösteriyoruz.
                        month += 1;
                        // year, month ve dayOfMonth değerleri seçilen tarihin değerleridir.
                        // Edittextte bu değerleri gösteriyoruz.
                        btnDogTarih.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                }, yil, ay, gun);
        // datepicker açıldığında set edilecek değerleri buraya yazıyoruz.
        // şimdiki zamanı göstermesi için yukarda tanmladığımz değşkenleri kullanyoruz.

        // dialog penceresinin button bilgilerini ayarlıyoruz ve ekranda gösteriyoruz.
        dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Seç", dpd);
        dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", dpd);
        dpd.show();

    }

    public void animation() {


        animationSagdanGelen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_sagdan_gelen);
        animationSoldanGelen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_soldan_gelen);
        animationYukardanGelen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_yukardan_gelen);
        animAsagidanYukari = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_asagidan_yukari);

        txtEmailKayit.setAnimation(animationSoldanGelen);
        txtSifreKayit.setAnimation(animationSagdanGelen);
        txtTelNoKayit.setAnimation(animationSoldanGelen);
        btnDogTarih.setAnimation(animationSagdanGelen);
        spinHesapTuruKayit.setAnimation(animationSoldanGelen);
        btnKayitOl.setAnimation(animAsagidanYukari);

    }

}
