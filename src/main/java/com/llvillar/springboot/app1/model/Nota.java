package com.llvillar.springboot.app1.model;

import java.sql.Date;

public class Nota {
    
    private int codigo; 
    private String titulo;
    private Date fecha;
    private String descripcion;
    
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Nota(int codigo, String titulo, Date fecha, String descripcion) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }
    public Nota() {
    }
    public Nota(int codigo) {
        this.codigo = codigo;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + codigo;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Nota other = (Nota) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    } 
}
