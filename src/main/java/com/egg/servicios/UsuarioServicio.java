package com.egg.servicios;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.entidades.Usuario;
import com.egg.enumeraciones.Rol;
import com.egg.excepciones.MiException;
import com.egg.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio implements UserDetailsService {
    @Autowired
    private UsuarioRepositorio userRepo;

    @Transactional
    public void registrar(String nombreUsuario, String password, Rol rol, boolean activo) throws MiException {
        validar(nombreUsuario, password, password);
        Usuario user = new Usuario();
        user.setNombreUsuario(nombreUsuario);
        user.setPassword(password);
        user.setRol(Rol.USER);
        user.setActivo(true);
        userRepo.save(user);
    }

    public void validar(String nombreUsuario, String password, String password2) throws MiException {
        if (nombreUsuario.isEmpty() || nombreUsuario == null) {
            throw new MiException("Debe incluir un nombre");
        }
        if (password.isEmpty() || password == null || password.length() <= 5) {
            throw new MiException("El password no debe ser nulo y/o debe tener mas de 5 caracteres");
        }
        if (!password2.equals(password)) {
            throw new MiException("Los password deben ser iguales");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuario = userRepo.buscarPorNombre(nombreUsuario);
        if (usuario != null) {
            ArrayList<SimpleGrantedAuthority> permisos = new ArrayList<>();
            SimpleGrantedAuthority p = new SimpleGrantedAuthority ("ROLE_" + usuario.getRol().toString());
            permisos.add(p);
            User user = new User(usuario.getNombreUsuario(), usuario.getPassword(),permisos);
            return user;
        }else{
            return null;
        }
    }

  

}
