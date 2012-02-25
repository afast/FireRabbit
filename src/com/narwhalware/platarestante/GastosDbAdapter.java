package com.narwhalware.platarestante;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.narwhalware.platarestante.entidades.Gasto;

import java.util.ArrayList;
import java.util.List;

public class GastosDbAdapter {
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    Context context;

    public GastosDbAdapter(Context ctx){
        this.context = ctx;
    }

    public GastosDbAdapter abrir() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void cerrar(){
        dbHelper.close();
    }

    public long guardarGasto(Gasto gasto){
        ContentValues initialValues = new ContentValues();
        initialValues.put("fecha", gasto.getFecha());
        initialValues.put("id_categoria", gasto.getCategoria().getId());
        initialValues.put("monto",gasto.getMonto());
        initialValues.put("subcategoria",gasto.getSubcategoria());

        return db.insert("gastos",null,initialValues);
    }

    public Cursor cursorGastos(){
        return db.query("gastos", new String[] {
                "_id", 
                "fecha",
                "id_categoria",
                "monto",
                "subcategoria"
        }, null, null, null, null, null);
    }

    public List<Gasto> obtenerGastos(){
        Cursor cursor = cursorGastos();
        List<Gasto> gastos = new ArrayList<Gasto>();

        CategoriasDbAdapter categoriasDbAdapter = new CategoriasDbAdapter(context);
        categoriasDbAdapter.abrir();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Gasto gasto = new Gasto(cursor.getInt(cursor.getColumnIndex("monto")),
                        categoriasDbAdapter.obtenerCategoria(cursor.getLong(cursor.getColumnIndex("id_categoria"))),
                        cursor.getString(cursor.getColumnIndex("subcategoria")),
                        cursor.getString(cursor.getColumnIndex("fecha")));
                gastos.add(gasto);
            }
            cursor.close();
            categoriasDbAdapter.cerrar();
        }
        return gastos;
    }
}
