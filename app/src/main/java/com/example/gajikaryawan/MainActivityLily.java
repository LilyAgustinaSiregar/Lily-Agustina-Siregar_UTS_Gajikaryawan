package com.example.gajikaryawan;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityLily extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lily);

        findViewById(R.id.btnInfoGaji).setOnClickListener(v ->
                startActivity(new Intent(this, GajiActivityLily.class)));
        findViewById(R.id.btnPetunjuk).setOnClickListener(v ->
                startActivity(new Intent(this, PetunjukActivityLily.class)));
        findViewById(R.id.btnTentang).setOnClickListener(v ->
                startActivity(new Intent(this, TentangActivityLily.class)));
        findViewById(R.id.btnExit).setOnClickListener(v -> finishAffinity());
    }
}
