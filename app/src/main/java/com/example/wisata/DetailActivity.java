package com.example.wisata;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dataHelper = new DataHelper(this);

        int id = getIntent().getIntExtra("EXTRA_ID", -1);

        if (id != -1) {
            Cursor cursor = dataHelper.getTempatById(id);
            if (cursor != null && cursor.moveToFirst()) {
                String nama = cursor.getString(cursor.getColumnIndex(DataHelper.COLUMN_NAMA));
                String deskripsi = cursor.getString(cursor.getColumnIndex(DataHelper.COLUMN_DESKRIPSI));
                String gambar = cursor.getString(cursor.getColumnIndex(DataHelper.COLUMN_GAMBAR));
                String lokasi = cursor.getString(cursor.getColumnIndex(DataHelper.COLUMN_LOKASI));

                TextView txtNama = findViewById(R.id.txtNamaDetail);
                TextView txtDeskripsi = findViewById(R.id.txtDeskripsiDetail);
                TextView txtLokasi = findViewById(R.id.txtLokasiDetail);
                ImageView imgGambar = findViewById(R.id.imgDetail);

                txtNama.setText(nama);
                txtDeskripsi.setText(deskripsi);
                txtLokasi.setText(lokasi);

                int imageResId = getResources().getIdentifier(gambar, "drawable", getPackageName());
                Glide.with(this)
                        .load(imageResId)
                        .placeholder(R.drawable.placeholder_image)
                        .into(imgGambar);

                cursor.close();
            }
        }
    }
}
