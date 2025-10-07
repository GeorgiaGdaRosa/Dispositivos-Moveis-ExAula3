package com.example.exaula3.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "revenda.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_CARROS = "carros";
    public static final String COL_ID = "id";
    public static final String COL_MODELO = "modelo";
    public static final String COL_ANO = "ano";
    public static final String COL_VALOR = "valor";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_CARROS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_MODELO + " TEXT, " +
                COL_ANO + " INTEGER, " +
                COL_VALOR + " REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARROS);
        onCreate(db);
    }
}

