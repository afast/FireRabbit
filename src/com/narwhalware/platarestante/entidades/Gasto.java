package com.narwhalware.platarestante.entidades;

import java.util.Date;

public class Gasto {
    int monto;
    Categoria categoria;
    String gasto;
    String fecha;

    public String getFecha() {
        return fecha;
    }

    public Gasto(int monto, Categoria categoria, String gasto) {
        this.monto = monto;
        this.categoria = categoria;
        this.gasto = gasto;

        Date fechaHoy = new Date();
        String fecha = fechaHoy.getYear() + 1900 +
                "-" + (fechaHoy.getMonth() + 1) +
                "-" + fechaHoy.getDate();
        this.fecha = fecha;
    }

    public Gasto(int monto, Categoria categoria, String gasto, String fecha) {
        this.monto = monto;
        this.categoria = categoria;
        this.gasto = gasto;
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

    public String getGasto() {
        return gasto;
    }

    public void setGasto(String gasto) {
        this.gasto = gasto;
    }
}
