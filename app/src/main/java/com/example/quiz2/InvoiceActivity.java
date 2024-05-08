package com.example.quiz2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class InvoiceActivity extends AppCompatActivity {

    TextView tvType, tvTambahan, tvJam, tvTotalHarga;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        tvType = findViewById(R.id.tvType);
        tvTambahan = findViewById(R.id.tvTambahan);
        tvJam = findViewById(R.id.tvWaktu);
        tvTotalHarga = findViewById(R.id.tvTotal);

        // Mengambil data dari Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String type = extras.getString("type");
            int hargaType = extras.getInt("harga_type");
            String tambahan = extras.getString("tambahan");
            int hargaTambahan = extras.getInt("harga_tambahan");
            int jam = extras.getInt("jam");
            int totalHarga = extras.getInt("total_harga");

            // Menampilkan data pada TextView
            tvType.setText("Type: " + type + " (Rp" + formatRupiah(hargaType) + ")");
            tvTambahan.setText("Tambahan: " + tambahan +" (Rp" + formatRupiah(hargaTambahan) +")");
            tvJam.setText("Jam: " + jam + " Jam");
            tvTotalHarga.setText("Total Harga: Rp" + formatRupiah(totalHarga));
        }
    }

    private String formatRupiah(int harga) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(harga);
    }
}

