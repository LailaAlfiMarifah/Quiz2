package com.example.quiz2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgType, rgTambahan;
    RadioButton rbSelectedType, rbSelectedTambahan;
    TextView etJam;
    Button btnProses;

    // Harga per jam untuk setiap tipe PS
    int hargaPS5 = 10000;
    int hargaPS4 = 8000;
    int hargaPS3 = 5000;
    int hargaPSVR = 2000;

    // Harga untuk setiap tambahan
    int hargaIndomie = 7000;
    int hargaMieAyam = 10000;
    int hargaSiomay = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgType = findViewById(R.id.rgType);
        rgTambahan = findViewById(R.id.rgTambahan);
        etJam = findViewById(R.id.etJam);
        btnProses = findViewById(R.id.btnProses);

        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedTypeId = rgType.getCheckedRadioButtonId();
                int selectedTambahanId = rgTambahan.getCheckedRadioButtonId();

                if (selectedTypeId == -1 || selectedTambahanId == -1 || etJam.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Harap lengkapi semua input", Toast.LENGTH_SHORT).show();
                } else {
                    rbSelectedType = findViewById(selectedTypeId);
                    rbSelectedTambahan = findViewById(selectedTambahanId);
                    String type = rbSelectedType.getText().toString();
                    String tambahan = rbSelectedTambahan.getText().toString();
                    int jam = Integer.parseInt(etJam.getText().toString());

                    int totalType = 0;
                    int totalTambahan = 0;

                    // Hitung total harga berdasarkan type yang dipilih
                    switch (type) {
                        case "PS5":
                            totalType = hargaPS5 * jam;
                            break;
                        case "PS4":
                            totalType = hargaPS4 * jam;
                            break;
                        case "PS3":
                            totalType = hargaPS3 * jam;
                            break;
                        case "PS":
                            totalType = hargaPSVR * jam;
                            break;
                    }

                    // Hitung total harga berdasarkan tambahan yang dipilih
                    switch (tambahan) {
                        case "Indomie":
                            totalTambahan = hargaIndomie;
                            break;
                        case "Mie Ayam":
                            totalTambahan = hargaMieAyam;
                            break;
                        case "Siomay":
                            totalTambahan = hargaSiomay;
                            break;
                    }

                    int totalHarga = totalType + totalTambahan;

                    // Kirim data ke halaman Invoice
                    Intent intent = new Intent(MainActivity.this, InvoiceActivity.class);
                    intent.putExtra("type", type);
                    intent.putExtra("harga_type", totalType);
                    intent.putExtra("tambahan", tambahan);
                    intent.putExtra("harga_tambahan", totalTambahan);
                    intent.putExtra("jam", jam);
                    intent.putExtra("total_harga", totalHarga);
                    startActivityForResult(intent, 1);
                }
            }
        });
    }
}



