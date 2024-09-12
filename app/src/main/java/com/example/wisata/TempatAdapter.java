package com.example.wisata;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class TempatAdapter extends RecyclerView.Adapter<TempatAdapter.TempatViewHolder> {

    private Context context;
    private Cursor cursor;
    private OnItemClickListener onItemClickListener;

    public TempatAdapter(Context context, Cursor cursor, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.cursor = cursor;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public TempatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tempat, parent, false);
        return new TempatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TempatViewHolder holder, int position) {
        if (cursor != null && cursor.moveToPosition(position)) {
            String nama = cursor.getString(cursor.getColumnIndex(DataHelper.COLUMN_NAMA));
            String gambar = cursor.getString(cursor.getColumnIndex(DataHelper.COLUMN_GAMBAR));
            String lokasi = cursor.getString(cursor.getColumnIndex(DataHelper.COLUMN_LOKASI));
            final int id = cursor.getInt(cursor.getColumnIndex(DataHelper.COLUMN_ID));

            holder.txtNama.setText(nama);
            holder.txtLokasi.setText(lokasi);

            int imageResId = context.getResources().getIdentifier(gambar, "drawable", context.getPackageName());
            Glide.with(context)
                    .load(imageResId)
                    .placeholder(R.drawable.placeholder_image)
                    .into(holder.imgTempat);

            holder.itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(id);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return cursor != null ? cursor.getCount() : 0;
    }

    public static class TempatViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTempat;
        TextView txtNama;
        TextView txtLokasi;

        public TempatViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTempat = itemView.findViewById(R.id.imgTempat);
            txtNama = itemView.findViewById(R.id.txtNama);
            txtLokasi = itemView.findViewById(R.id.txtLokasi);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int id);
    }
}
