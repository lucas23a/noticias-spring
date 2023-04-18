package com.egg.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.entidades.Noticia;
import com.egg.excepciones.MiException;
import com.egg.servicios.NoticiaServicio;

@Controller
@RequestMapping("/noticia")
public class NoticiaControlador {
   @Autowired 
    private NoticiaServicio notiService= new NoticiaServicio();

    @GetMapping("/nueva")
    public String nueva() {
        return "noticia_form.html";
    }

    @PostMapping("/cargar")
    public String cargar(@RequestParam String titulo, @RequestParam String cuerpo, ModelMap modelo) {
        try {
            notiService.crearNoticia(titulo, cuerpo);
            modelo.put("exito", "La noticia fue cargada con exito");
        } catch (MiException e) {   
            modelo.put("error", e.getMessage());        
            e.printStackTrace();
            return "noticia_form.html";
        }
        return "noticia_form.html";
    }

    
    @GetMapping("/index")
    public String listarNoticias(ModelMap modelo){
        List<Noticia> lista = notiService.listarNoticias();
        modelo.addAttribute("lista", lista);
        return "index.html";
    }


}
