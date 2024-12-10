package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.daos.AdministradorRepository;
import com.tecsup.demo.modelo.entidades.Administrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // Para encriptar contraseñas

    @Override
    @Transactional(readOnly = true)
    public List<Administrador> listar() {
        return administradorRepository.findAll();
    }

    @Override
    @Transactional
    public void grabar(Administrador administrador) {
        // Encriptar contraseña antes de guardar
        administrador.setContrasena(passwordEncoder.encode(administrador.getContrasena()));
        administradorRepository.save(administrador);
    }

    @Override
    @Transactional(readOnly = true)
    public Administrador buscar(Integer id) {
        return administradorRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        administradorRepository.deleteById(id);
    }
}
