package com.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entidades.Noticia;
import com.excepciones.MiException;
import com.repositorios.NoticiaRepositorio;

@Service
public class NoticiaServicio {

    //@Autowired (required=false)
    private NoticiaRepositorio notiRepositorio;

    @Transactional
    public void crearNoticia(String titulo, String cuerpo) throws MiException {
      validar(titulo, cuerpo);

        Noticia noti = new Noticia();
        noti.setTitulo(titulo);
        noti.setCuerpo(cuerpo);
        noti.setFecha(new Date());
        notiRepositorio.save(noti);
    }

    public List<Noticia> listarNoticias() {
        List<Noticia> lista = new ArrayList<>();
        lista = notiRepositorio.findAll();
        return lista;
    }

    public void modificarNoticia(String titulo, String id, String cuerpo) {
        Optional<Noticia> respuesta = notiRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Noticia noticia = respuesta.get();
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            noticia.setFecha(new Date());
            notiRepositorio.save(noticia);
        }
    }

public void validar(String titulo, String cuerpo) throws MiException{
    if (titulo.isEmpty()) {
        throw new MiException("Debe incluir un titulo");
    }
    if (cuerpo.isEmpty()) {
        throw new MiException("Debe incluir un cuerpo");
    }
}

}
