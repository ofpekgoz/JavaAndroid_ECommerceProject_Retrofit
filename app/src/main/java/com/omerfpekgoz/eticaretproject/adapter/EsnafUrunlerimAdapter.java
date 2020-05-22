package com.omerfpekgoz.eticaretproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.omerfpekgoz.eticaretproject.R;
import com.omerfpekgoz.eticaretproject.model.Urunler;
import com.omerfpekgoz.eticaretproject.model.UrunlerResponse;
import com.omerfpekgoz.eticaretproject.service.IUrunlerDao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EsnafUrunlerimAdapter extends RecyclerView.Adapter<EsnafUrunlerimAdapter.cardViewHolder> {


    private Context mContext;
    private List<Urunler> urunlerList;

    private IUrunlerDao urunlerDao;

    public EsnafUrunlerimAdapter(Context mContext, List<Urunler> urunlerList, IUrunlerDao urunlerDao) {
        this.mContext = mContext;
        this.urunlerList = urunlerList;
        this.urunlerDao = urunlerDao;
    }

    public EsnafUrunlerimAdapter() {
    }

    @NonNull
    @Override
    public cardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.urunlerim_card_tasarim, parent, false);

        return new cardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cardViewHolder holder, int position) {

        Urunler urun = urunlerList.get(position);

        holder.txtKategoriAd.setText("Kategori Adı: " + urun.getUrunKategori());
        holder.txtUrunAd.setText("Ürün Adı: " + urun.getUrunAd());
        holder.txtSatisFiyat.setText("Satış Fiyatı: " + String.valueOf(urun.getUrunSatisfiyat()));
        holder.txtStokDurumu.setText("Stok Durumu: " + String.valueOf(urun.getUrunStokdurumu()));


    }

    @Override
    public int getItemCount() {
        return urunlerList.size();
    }


    public class cardViewHolder extends RecyclerView.ViewHolder {

        private CardView cardViewUrunlerim;
        private TextView txtKategoriAd, txtUrunAd, txtSatisFiyat, txtStokDurumu;


        public cardViewHolder(@NonNull View itemView) {
            super(itemView);

            cardViewUrunlerim = itemView.findViewById(R.id.cardViewUrunlerim);
            txtKategoriAd = itemView.findViewById(R.id.txtKategoriAd);
            txtUrunAd = itemView.findViewById(R.id.txtUrunAd);
            txtSatisFiyat = itemView.findViewById(R.id.txtSatisFiyat);
            txtStokDurumu = itemView.findViewById(R.id.txtStokDurumu);
        }
    }


}


