package com.test.ayamku;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Currency;
import com.google.android.material.card.MaterialCardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.text.NumberFormat;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private final ArrayList<RecyclerData> courseDataArrayList;
    private final Context mcontext;

    float hargaTemp = 0, numberFlt;
    String number, harga;
    Intent intent;

    NumberFormat format;

    public RecyclerViewAdapter(ArrayList<RecyclerData> recyclerDataArrayList, Context mcontext) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        RecyclerData recyclerData = courseDataArrayList.get(position);
        holder.namaTV.setText(recyclerData.getTitle());
        holder.hargaTV.setText(recyclerData.getHarga());
        holder.gambarIV.setImageResource(recyclerData.getImgid());

        holder.gambarIV.setOnClickListener(v -> {
            number = holder.hargaTV.getText().toString().replaceAll("[^0-9]", "");
            numberFlt = Float.parseFloat(number);
            hargaTemp += numberFlt;

            format = NumberFormat.getCurrencyInstance();
            format.setMaximumFractionDigits(0);
            format.setCurrency(Currency.getInstance("IDR"));

            harga = format.format(hargaTemp);

            intent = new Intent("custom-message");
            intent.putExtra("Rhrg", harga);
            LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intent);
        });

        holder.text.setOnClickListener(view -> {
            // START NEW ACTIVITY
        });
    }

    @Override
    public int getItemCount() {
        return courseDataArrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private final TextView namaTV, hargaTV;
        private final ImageView gambarIV;
        private final LinearLayout text;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            namaTV = itemView.findViewById(R.id.nama);
            hargaTV = itemView.findViewById(R.id.harga);
            gambarIV = itemView.findViewById(R.id.gambar);
            text = itemView.findViewById(R.id.text);
        }
    }

}