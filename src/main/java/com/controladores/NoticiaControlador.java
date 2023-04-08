package com.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excepciones.MiException;
import com.servicios.NoticiaServicio;

@Controller
@RequestMapping("/noticia")
public class NoticiaControlador {
   @Autowired (required=false)
    private NoticiaServicio notiService= new NoticiaServicio();

    @GetMapping("/nueva")
    public String nueva() {
        return "noticia_form.html";
    }

    @PostMapping("/cargar")
    public String cargar(@RequestParam String titulo, @RequestParam String cuerpo) {
        try {
            notiService.crearNoticia(titulo, cuerpo);
        } catch (MiException e) {           
            e.printStackTrace();
            return "index.html";
        }
        return "noticia_form.html";
    }

}
