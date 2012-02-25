package com.narwhalware.platarestante.entidades;

import java.util.Date;

public class Gasto {
    int monto;
    Categoria categoria;
    String subcategoria;
    String fecha;

    public String getFecha() {
        return fecha;
    }

    public Gasto(int monto, Categoria categoria, String subcategoria) {
        this.monto = monto;
        this.categoria = categoria;
        this.subcategoria = subcategoria;

        Date fechaHoy = new Date();
        String fecha = fechaHoy.getYear() + 1900 +
                "-" + (fechaHoy.getMonth() + 1) +
                "-" + fechaHoy.getDate();
        this.fecha = fecha;
    }

    public Gasto(int monto, Categoria categoria, String subcategoria, String fecha) {
        this.monto = monto;
        this.categoria = categoria;
        this.subcategoria = subcategoria;
        this.fecha = fecha;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    @Override
    public String toString() {
        return "$" + this.monto + " en " + this.categoria.toString() + " el " + this.fecha;
    }
}
