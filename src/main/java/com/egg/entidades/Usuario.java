package com.egg.entidades;

import org.hibernate.annotations.GenericGenerator;

import com.egg.enumeraciones.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column(unique = true)
    private String nombreUsuario;
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    private boolean activo;
    public Usuario() {
    }
    public Usuario(String id, String nombreUsuario, String password, Rol rol, boolean activo) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.rol = rol;
        this.activo = activo;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Rol getRol() {
        return rol;
    }
    public void setRol(Rol rol) {
        this.rol = rol;
    }
    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombreUsuario=" + nombreUsuario + ", password=" + password + ", rol=" + rol
                + ", activo=" + activo + "]";
    }

    
    
}
