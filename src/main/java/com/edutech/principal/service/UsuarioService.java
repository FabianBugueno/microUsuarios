package com.edutech.principal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.principal.model.Usuario;
import com.edutech.principal.model.dto.UsuarioDto;
import com.edutech.principal.model.entity.UsuarioEntity;
import com.edutech.principal.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public String crearUsuario(Usuario user){
        try {
                Boolean estado = usuarioRepository.existsByCorreo(user.getCorreo());
                if (!estado){
                    UsuarioEntity usuarioNuevo = new UsuarioEntity();
                    usuarioNuevo.setIdUsuario(user.getIdUsuario());
                    usuarioNuevo.setNombre(user.getNombre());
                    usuarioNuevo.setApellidos(user.getApellidos()); 
                    usuarioNuevo.setCorreo(user.getCorreo());
                    usuarioNuevo.setContrasena(user.getContrasena());
                    usuarioRepository.save(usuarioNuevo);
                    return "Usuario creado correctamente";
                } return "El correo ya existe";
        } catch (Exception e) {
            return "Error al crear el usuario: " + e.getMessage(); 
        }
    }  
    
    public Usuario obtenerUsuario (String correo){
        try{
            UsuarioEntity usuario = usuarioRepository.findByCorreo(correo);
            if (usuario != null){ 
             Usuario user = new Usuario(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getApellidos(),
                usuario.getCorreo(),
                usuario.getContrasena()
             );
                return user;
            }return null;
            
        }catch (Exception e) {
            return null;
        }
    }

    public UsuarioDto obtenerUsuarioDto(int id){
     try{
        UsuarioEntity usuario = usuarioRepository.findByIdUsuario(id);
        UsuarioDto nuevoUsuario = new UsuarioDto(
            usuario.getNombre(),
            usuario.getApellidos(),
            usuario.getCorreo()
        );
        return nuevoUsuario;

    }catch (Exception e) {
        return null;
     }

    }

    public String modificarUsuario(int id, Usuario usuario) {
        UsuarioEntity usuarioExistente = usuarioRepository.findByIdUsuario(id);
        if (usuarioExistente == null) {
            return null;
        }
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setApellidos(usuario.getApellidos());
        usuarioExistente.setCorreo(usuario.getCorreo());
        usuarioExistente.setContrasena(usuario.getContrasena());
        usuarioRepository.save(usuarioExistente);
        return "Usuario modificado correctamente";
    }

    public String eliminarUsuario(int id) {
        UsuarioEntity usuario = usuarioRepository.findByIdUsuario(id);
        if (usuario == null) {
            return null;
        }
        usuarioRepository.delete(usuario);
        return "Usuario eliminado correctamente";
    }
}

