package com.example.gajikaryawan;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class GajiActivityLily extends AppCompatActivity {

    private static final long UANG_MAKAN_PER_HARI = 10000;
    private static final long UANG_TRANSPORT_PER_HARI = 5000;
    private static final double TARIF_PAJAK = 0.10;

    private EditText etNik, etNama, etGapok, etTunjab, etHari;
    private View cardHasil;
    private TextView tvIdentitas, tvMakan, tvTransport, tvKotor, tvPajak, tvBersih;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaji_lily);
        ActionBar bar = getSupportActionBar();
        if (bar != null) bar.setDisplayHomeAsUpEnabled(true);

        etNik = findViewById(R.id.etNik);
        etNama = findViewById(R.id.etNama);
        etGapok = findViewById(R.id.etGapok);
        etTunjab = findViewById(R.id.etTunjab);
        etHari = findViewById(R.id.etHari);
        cardHasil = findViewById(R.id.cardHasil);
        tvIdentitas = findViewById(R.id.tvIdentitas);
        tvMakan = findViewById(R.id.tvMakan);
        tvTransport = findViewById(R.id.tvTransport);
        tvKotor = findViewById(R.id.tvKotor);
        tvPajak = findViewById(R.id.tvPajak);
        tvBersih = findViewById(R.id.tvBersih);

        findViewById(R.id.btnHitung).setOnClickListener(v -> hitung());
        findViewById(R.id.btnReset).setOnClickListener(v -> reset());
    }

    private void hitung() {
        if (!valid(etNik) | !valid(etNama) | !valid(etGapok) | !valid(etTunjab) | !valid(etHari)) {
            return;
        }
        long gapok, tunjab, hari;
        try {
            gapok = Long.parseLong(etGapok.getText().toString().trim());
            tunjab = Long.parseLong(etTunjab.getText().toString().trim());
            hari = Long.parseLong(etHari.getText().toString().trim());
        } catch (NumberFormatException e) {
            etGapok.setError("Angka tidak valid");
            return;
        }

        long makan = hari * UANG_MAKAN_PER_HARI;
        long transport = hari * UANG_TRANSPORT_PER_HARI;
        long kotor = gapok + tunjab + makan + transport;
        long pajak = Math.round(kotor * TARIF_PAJAK);
        long bersih = kotor - pajak;

        tvIdentitas.setText(etNik.getText().toString().trim() + " - " + etNama.getText().toString().trim());
        tvMakan.setText(rp(makan));
        tvTransport.setText(rp(transport));
        tvKotor.setText(rp(kotor));
        tvPajak.setText(rp(pajak));
        tvBersih.setText(rp(bersih));
        cardHasil.setVisibility(View.VISIBLE);
    }

    private boolean valid(EditText et) {
        if (TextUtils.isEmpty(et.getText().toString().trim())) {
            et.setError("Wajib diisi");
            return false;
        }
        return true;
    }

    private void reset() {
        etNik.setText("");
        etNama.setText("");
        etGapok.setText("");
        etTunjab.setText("");
        etHari.setText("");
        etNik.setError(null);
        cardHasil.setVisibility(View.GONE);
        etNik.requestFocus();
    }

    private String rp(long angka) {
        return NumberFormat.getCurrencyInstance(new Locale("in", "ID")).format(angka);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
