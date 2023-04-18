package com.egg.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.excepciones.MiException;
import com.egg.servicios.UsuarioServicio;

@Controller
@RequestMapping("/")
public class PortalControlador {
    @Autowired
    private UsuarioServicio uServicio;

    @GetMapping("/")
    public String index(){
        return "index.html";
    }

    @GetMapping("/registrar")
    public String registrar(){
        return "registrar.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombreUsuario, @RequestParam String password,
     @RequestParam String password2, ModelMap modelo){

        try {
            uServicio.registrar(nombreUsuario, password, null, false);
            modelo.put("exito", "Se ha registrado el usuario nuevo");
        } catch (MiException e) {
          
            modelo.put("error", e.getMessage());
        }
        return "registrar.html";
    }

}
