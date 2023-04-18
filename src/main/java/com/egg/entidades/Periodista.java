package com.egg.entidades;

import java.util.ArrayList;

import com.egg.enumeraciones.Rol;


public class Periodista extends Usuario{
    private ArrayList<Noticia> misNoticias;
    private Integer sueldoMensual;
    public ArrayList<Noticia> getMisNoticias() {
        return misNoticias;
    }
    public void setMisNoticias(ArrayList<Noticia> misNoticias) {
        this.misNoticias = misNoticias;
    }
    public Integer getSueldoMensual() {
        return sueldoMensual;
    }
    public void setSueldoMensual(Integer sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }

    public Periodista(){
    }
    public Periodista(String id, String nombreUsuario, String password, Rol rol, boolean activo, ArrayList<Noticia> misNoticias, Integer sueldoMensual ){
    }
    @Override
    public String toString() {
        return "Periodista [misNoticias=" + misNoticias + ", sueldoMensual=" + sueldoMensual + "]";
    }
    
}
