package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.daos.AdministradorRepository;
import com.tecsup.demo.modelo.entidades.Administrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Override
    public UserDetails loadUserByUsername(String correoElectronico) throws UsernameNotFoundException {
        Administrador admin = administradorRepository.findByCorreoElectronico(correoElectronico)
                .orElseThrow(() -> new UsernameNotFoundException("Administrador no encontrado con correo: " + correoElectronico));
        return new CustomUserDetails(admin);
    }
}
