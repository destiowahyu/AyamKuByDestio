package com.test.ayamku;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.content.BroadcastReceiver;
import java.util.ArrayList;
import android.content.IntentFilter;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<RecyclerData> recyclerDataArrayList;
    TextView total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.idCourseRV);
        total = findViewById(R.id.total);
        total.setOnClickListener(view -> {
            // START NEW ACTIVITY
        });

        total.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, FormPembayaran.class);
            startActivity(i);
        });


        recyclerDataArrayList = new ArrayList<>();
        recyclerDataArrayList.add(new RecyclerData("Ayam 1", "20000", R.drawable.img));
        recyclerDataArrayList.add(new RecyclerData("Ayam 2", "12000", R.drawable.img2));
        recyclerDataArrayList.add(new RecyclerData("Ayam 3", "18000", R.drawable.img3));
        recyclerDataArrayList.add(new RecyclerData("Ayam 4", "15000", R.drawable.img4));
        recyclerDataArrayList.add(new RecyclerData("Ayam 5", "30000", R.drawable.img5));
        recyclerDataArrayList.add(new RecyclerData("Ayam 6", "25000", R.drawable.img6));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(recyclerDataArrayList, this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));

    }
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String s = intent.getStringExtra("Rhrg");
            total.setText("Total = " + s);
        }
    };

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        if (menu instanceof MenuBuilder) {
            MenuBuilder m = (MenuBuilder) menu;
            //noinspection RestrictedApi
            m.setOptionalIconsVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.call:
                Toast.makeText(this, "Nomor Call Center", Toast.LENGTH_SHORT).show();
                String nomor="+6285929095672";
                Intent panggil=new Intent(Intent.ACTION_DIAL);
                panggil.setData(Uri.fromParts("tel", nomor, null));
                startActivity(panggil);
                break;
            case R.id.map:
                Toast.makeText(this, "Membuka Google Maps", Toast.LENGTH_SHORT).show();
                String url="https://goo.gl/maps/YxhSJp2oHrbUWbNa8";
                Intent bukaAlamat = new Intent(Intent.ACTION_VIEW);
                bukaAlamat.setData(Uri.parse(url));
                startActivity(bukaAlamat);
                break;
            case R.id.upd:

                return true;
            case R.id.logout:

                finish();
                return true;
        }
        return (super.onOptionsItemSelected(item));
    }











}