package com.narwhalware.platarestante;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    DatabaseHelper(Context context) {
        super(context, "plata_restante", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table categorias (_id integer primary key autoincrement, "
                + "nombre text not null);");
        db.execSQL("create table gastos(_id integer primary key autoincrement, " +
                "fecha text, " +
                "id_categoria integer, " +
                "monto integer," +
                "nombre text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
