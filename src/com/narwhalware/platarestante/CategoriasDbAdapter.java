package com.narwhalware.platarestante;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.narwhalware.platarestante.entidades.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriasDbAdapter {
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    Context context;

    public CategoriasDbAdapter(Context ctx){
        this.context = ctx;
    }

    public CategoriasDbAdapter abrir() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void cerrar(){
        dbHelper.close();
    }

    public long guardarCategoria(String nombre){
        ContentValues initialValues = new ContentValues();
        initialValues.put("nombre", nombre);

        return db.insert("categorias", null, initialValues);
    }

    public Cursor cursorCategorias(){
        return db.query("categorias", new String[] {"_id", "nombre"}, null, null, null, null, null);
    }
    
    public List<Categoria> obtenerCategorias(){
        Cursor cursor = cursorCategorias();
        List<Categoria> categorias = new ArrayList<Categoria>();

        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Categoria categoria = new Categoria(cursor.getLong(cursor.getColumnIndex("_id")),
                                                    cursor.getString(cursor.getColumnIndex("nombre")));
                categorias.add(categoria);
            }
            cursor.close();
        }
        return categorias;
    }



}
