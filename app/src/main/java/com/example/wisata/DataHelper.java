package com.example.wisata;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "wisata.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "tempat";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_DESKRIPSI = "deskripsi";
    public static final String COLUMN_GAMBAR = "gambar";
    public static final String COLUMN_LOKASI = "lokasi";

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table SQL statement
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAMA + " TEXT, "
                + COLUMN_DESKRIPSI + " TEXT, "
                + COLUMN_GAMBAR + " TEXT, "
                + COLUMN_LOKASI + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);

        // Insert initial data
        insertInitialData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        // Define initial data
        String[] names = {"Tempat A", "Tempat B", "Tempat C"};
        String[] descriptions = {"Deskripsi A", "Deskripsi B", "Deskripsi C"};
        String[] images = {"gambar_a", "gambar_b", "gambar_c"};
        String[] locations = {"Lokasi A", "Lokasi B", "Lokasi C"};

        // Insert data into the table
        for (int i = 0; i < names.length; i++) {
            String insertData = "INSERT INTO " + TABLE_NAME + " ("
                    + COLUMN_NAMA + ", "
                    + COLUMN_DESKRIPSI + ", "
                    + COLUMN_GAMBAR + ", "
                    + COLUMN_LOKASI + ") VALUES ('"
                    + names[i] + "', '"
                    + descriptions[i] + "', '"
                    + images[i] + "', '"
                    + locations[i] + "')";
            db.execSQL(insertData);
        }
    }

    public Cursor getAllTempatCursor() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    public Cursor getTempatById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};
        return db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
    }
}
