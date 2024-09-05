package com.upc.trabajoarquitectura.servicies;

import com.upc.trabajoarquitectura.entities.Rol;
import com.upc.trabajoarquitectura.entities.Usuario;
import com.upc.trabajoarquitectura.interfaces.IRolService;
import com.upc.trabajoarquitectura.respository.RolRepository;
import com.upc.trabajoarquitectura.respository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class RolService implements IRolService {
    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }

    @Transactional
    public Rol registrarRol(Rol rol){
        return rolRepository.save(rol);
    }

    @Transactional
    public Rol actualizarRol(Rol rol){
        if (rolRepository.findById(rol.getRolID()).isPresent()){
            return rolRepository.save(rol);
        }
        return null;
    }

    @Transactional
    public void eliminarRol(Long rolID) throws Exception {
        if (rolRepository.existsById(rolID)){
            rolRepository.deleteById(rolID);
        }else {
            throw new Exception("No se puede eliminar el rol");
        }
    }

    @Transactional
    public void asignarRol(Long rolID, Long usuarioID) throws Exception{
        if (usuarioRepository.findById(usuarioID).isPresent()){
            Rol rolPorDefecto = rolRepository.findByNombreRol("Cliente");
            Usuario usuario = usuarioRepository.findById(usuarioID).get();
            usuario.setRol(rolPorDefecto);
            usuarioRepository.save(usuario);
        }else {
            throw new Exception("No se puede asignar el rol");
        }
    }
}
