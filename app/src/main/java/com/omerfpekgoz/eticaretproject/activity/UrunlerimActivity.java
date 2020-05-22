package com.omerfpekgoz.eticaretproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.omerfpekgoz.eticaretproject.R;
import com.omerfpekgoz.eticaretproject.adapter.EsnafUrunlerimAdapter;
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

public class UrunlerimActivity extends AppCompatActivity {

    private Toolbar toolbarEsnafUrunlerim;
    private RecyclerView recViewEsnafUrunlerim;
    private Spinner spinKategoriSec;

    private ArrayList<String> kategoriler = new ArrayList<>();
    private ArrayAdapter<String> kategoriAdapter;

    private List<Urunler> urunlerList;
    private EsnafUrunlerimAdapter esnafUrunlerimAdapter;

    private IUrunlerDao urunlerDao;
    private int urun_esnafid=UyeLogin.uye_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urunlerim);

        toolbarEsnafUrunlerim = findViewById(R.id.toolbarEsnafUrunlerim);
        recViewEsnafUrunlerim = findViewById(R.id.recViewEsnafUrunlerim);
        spinKategoriSec = findViewById(R.id.spinKategoriSec);


        urunlerDao = APIUtils.getUrunlerDao();

        toolbarEsnafUrunlerim.setTitle("");
        setSupportActionBar(toolbarEsnafUrunlerim);

        urunlerList = new ArrayList<>();
        esnafUrunlerimAdapter = new EsnafUrunlerimAdapter();

        recViewEsnafUrunlerim.setHasFixedSize(true);
        recViewEsnafUrunlerim.setLayoutManager(new LinearLayoutManager(this));


        kategoriAdapter = new ArrayAdapter<String>(UrunlerimActivity.this,R.layout.spinner_item, android.R.id.text1, kategoriler);
        kategoriAdapter.setDropDownViewResource(R.layout.spinner_droptown1);
        spinKategoriSec.setAdapter(kategoriAdapter);

        spinKategoriSec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                urunlerList.clear();
                String secilenKategori = kategoriler.get(position);
                tumUrunler();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        tumKategoriByEsnafId();

    }

    public void tumUrunler() {

        final int urun_esnafid = UyeLogin.uye_id;

        urunlerDao.tumUrunlerByEsnafId(urun_esnafid, spinKategoriSec.getSelectedItem().toString()).enqueue(new Callback<UrunlerResponse>() {
            @Override
            public void onResponse(Call<UrunlerResponse> call, Response<UrunlerResponse> response) {

                Log.e("Cevap", response.message());

                urunlerList = new ArrayList<>();
                urunlerList = response.body().getUrunler();

                if (urunlerList != null) {
                    esnafUrunlerimAdapter = new EsnafUrunlerimAdapter(UrunlerimActivity.this, urunlerList, urunlerDao);
                    recViewEsnafUrunlerim.setAdapter(esnafUrunlerimAdapter);
                }

            }

            @Override
            public void onFailure(Call<UrunlerResponse> call, Throwable t) {

            }
        });


    }
    public void tumKategoriByEsnafId(){
        urunlerDao.kategoriAdByEsnafId(urun_esnafid).enqueue(new Callback<UrunlerResponse>() {
            @Override
            public void onResponse(Call<UrunlerResponse> call, Response<UrunlerResponse> response) {

                urunlerList=response.body().getUrunler();

                for (Urunler urun:urunlerList){
                    kategoriler.add(urun.getUrunKategori());
                }

                kategoriAdapter = new ArrayAdapter<String>(UrunlerimActivity.this, android.R.layout.activity_list_item, android.R.id.text1, kategoriler);
                spinKategoriSec.setAdapter(kategoriAdapter);


            }

            @Override
            public void onFailure(Call<UrunlerResponse> call, Throwable t) {

            }
        });
    }
}
