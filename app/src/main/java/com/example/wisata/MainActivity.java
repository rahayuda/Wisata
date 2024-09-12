package com.example.wisata;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TempatAdapter adapter;
    private DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataHelper = new DataHelper(this);
        Cursor cursor = dataHelper.getAllTempatCursor();

        adapter = new TempatAdapter(this, cursor, id -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("EXTRA_ID", id);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }
}
