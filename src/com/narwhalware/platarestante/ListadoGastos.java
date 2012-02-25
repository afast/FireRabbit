package com.narwhalware.platarestante;

import android.*;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import com.narwhalware.platarestante.entidades.Gasto;

import java.util.List;

public class ListadoGastos extends ListActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listado_gastos);
        
        GastosDbAdapter dbAdapter = new GastosDbAdapter(this);
        dbAdapter.abrir();
        List<Gasto> gastos =  dbAdapter.obtenerGastos();
        dbAdapter.cerrar();
        
        ListAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,gastos);
        setListAdapter(adapter);
    }
}