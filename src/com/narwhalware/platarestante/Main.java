package com.narwhalware.platarestante;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.narwhalware.platarestante.entidades.Categoria;
import com.narwhalware.platarestante.entidades.Gasto;

import java.util.Date;
import java.util.List;

public class Main extends Activity {
    Context context;
    CategoriasDbAdapter categoriasDbAdapter;
    List<Categoria> categorias;
    

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        categoriasDbAdapter = new CategoriasDbAdapter(this);
        categoriasDbAdapter.abrir();

        this.context = this;

        cargarCategorias();
    }

    private void cargarCategorias() {
        categorias = categoriasDbAdapter.obtenerCategorias();
        Spinner spinner = (Spinner) findViewById(R.id.categoria);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.agregar_categoria:
                startActivityForResult(new Intent(this.context, AgregarCategoria.class),0);
                return true;
            case R.id.ver_saldo:
                startActivity(new Intent(this.context, Restante.class));
                return true;
            case R.id.subir_datos:
                return true;
            case R.id.listado_gastos:
                startActivity(new Intent(this.context, ListadoGastos.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        cargarCategorias();
    }

    public void guardarGasto(View view){
        TextView tvMonto = (TextView) findViewById(R.id.monto);
        int monto = Integer.parseInt(tvMonto.getText().toString());
        Spinner spinnerCategoria = (Spinner)findViewById(R.id.categoria);
        Categoria categoria = categorias.get(spinnerCategoria.getSelectedItemPosition());
        TextView tvSubcategoria = (TextView) findViewById(R.id.subcategoria);
        String subcategoria = tvSubcategoria.getText().toString();

        subcategoria = subcategoria.toLowerCase();
        subcategoria = subcategoria.trim();

        Gasto gasto = new Gasto(monto,categoria,subcategoria);
        GastosDbAdapter gastosDbAdapter = new GastosDbAdapter(this);
        gastosDbAdapter.abrir();
        gastosDbAdapter.guardarGasto(gasto);
        gastosDbAdapter.cerrar();
        
        tvMonto.setText("");
        spinnerCategoria.setSelection(0);
        tvSubcategoria.setText("");
        
        tvMonto.clearFocus();

        Toast.makeText(this, "Gasto guardado ", Toast.LENGTH_LONG).show();
    }
}