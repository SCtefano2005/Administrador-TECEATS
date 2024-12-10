package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Administrador;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final Administrador administrador;

    public CustomUserDetails(Administrador administrador) {
        this.administrador = administrador;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Asignar un rol fijo, por ejemplo ROLE_ADMIN
        return Collections.singleton(() -> "ROLE_ADMIN");
    }

    @Override
    public String getPassword() {
        return administrador.getContrasena();
    }

    @Override
    public String getUsername() {
        return administrador.getCorreoElectronico();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Administrador getAdministrador() {
        return administrador;
    }
}
