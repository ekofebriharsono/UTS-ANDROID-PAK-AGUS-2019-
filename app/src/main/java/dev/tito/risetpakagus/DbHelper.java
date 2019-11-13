package dev.tito.risetpakagus;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    static final String DATABASE_NAME = "risetPakAgus1.db";

    public static final String TABLE_SQLite = "barang";

    public static final String COLUMN_KODE = "KODE";
    public static final String COLUMN_NAMA = "NAMA";
    public static final String COLUMN_UNIT = "UNIT";
    public static final String COLUMN_HARGA_JUAL = "HARGA_JUAL";
    public static final String COLUMN_HARGA_BELI = "HARGA_BELI";
    public static final String COLUMN_DISKON = "DISKON";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_SQLite + " (" +
                COLUMN_KODE + " TEXT PRIMARY KEY, " +
                COLUMN_NAMA + " TEXT NOT NULL, " +
                COLUMN_UNIT + " TEXT NOT NULL," +
                COLUMN_HARGA_JUAL + " TEXT NOT NULL," +
                COLUMN_HARGA_BELI + " TEXT NOT NULL," +
                COLUMN_DISKON + " TEXT NOT NULL" +
                " )";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SQLite);
        onCreate(db);
    }

    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_SQLite;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(COLUMN_KODE, cursor.getString(0));
                map.put(COLUMN_NAMA, cursor.getString(1));
                map.put(COLUMN_UNIT, cursor.getString(2));
                map.put(COLUMN_HARGA_JUAL, cursor.getString(3));
                map.put(COLUMN_HARGA_BELI, cursor.getString(4));
                map.put(COLUMN_DISKON, cursor.getString(5));
                wordList.add(map);
            } while (cursor.moveToNext());
        }

        Log.e("select sqlite ", "" + wordList);

        database.close();
        return wordList;
    }

    public void insert(String kode, String nama, String unit, String hargaJual, String hargaBeli, String diskon) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_SQLite + " " + "("
                + COLUMN_KODE + ","
                + COLUMN_NAMA + ","
                + COLUMN_UNIT + ","
                + COLUMN_HARGA_JUAL + ","
                + COLUMN_HARGA_BELI + ","
                + COLUMN_DISKON + ") " +
                "VALUES ('"
                + kode + "', '"
                + nama + "', '"
                + unit + "', '"
                + hargaJual + "', '"
                + hargaBeli + "', '"
                + diskon + "')";

        Log.e("insert sqlite ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void update(String kode, String nama, String unit, String hargaJual, String hargaBeli, String diskon) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_SQLite + " SET "
                + COLUMN_NAMA + "='" + nama + "', "
                + COLUMN_UNIT + "='" + unit + "',"
                + COLUMN_HARGA_JUAL + "='" + hargaJual + "',"
                + COLUMN_HARGA_BELI + "='" + hargaBeli + "',"
                + COLUMN_DISKON + "='" + diskon + "'"
                + " WHERE " + COLUMN_KODE + "=" + "'" + kode + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete(String kode) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " + TABLE_SQLite + " WHERE " + COLUMN_KODE + "=" + "'" + kode + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

}