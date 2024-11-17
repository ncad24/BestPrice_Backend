package com.upc.trabajoarquitectura.servicies;

import com.upc.trabajoarquitectura.entities.UserApp;
import com.upc.trabajoarquitectura.exceptions.RequestException;
import com.upc.trabajoarquitectura.respositories.UserAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private final UserAppRepository userAppRepository;

    public CustomUserDetailsService(UserAppRepository userAppRepository) {
        this.userAppRepository = userAppRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp userApp = userAppRepository.findByUsername(username)
                .orElseThrow(() -> new RequestException("A001", HttpStatus.NOT_FOUND, "No se encontró el usuario"));

        GrantedAuthority authority = new SimpleGrantedAuthority(userApp.getRole().getRoleName());

        return org.springframework.security.core.userdetails.User
                .withUsername(userApp.getUsername())
                .password(userApp.getPassword())
                .authorities(Collections.singleton(authority))
                .build();
    }
}