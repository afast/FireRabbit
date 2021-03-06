package com.narwhalware.platarestante;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AgregarCategoria extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_categoria);
    }

    public void guardarCategoria(View view){
        CategoriasDbAdapter dbAdapter = new CategoriasDbAdapter(this);
        EditText editText = (EditText)findViewById(R.id.nombre_categoria);
        
        String nombreCategoria = editText.getText().toString();
        
        nombreCategoria = nombreCategoria.toLowerCase();
        nombreCategoria = nombreCategoria.trim();

        dbAdapter.abrir();
        dbAdapter.guardarCategoria(nombreCategoria);

        finish();
    }
}