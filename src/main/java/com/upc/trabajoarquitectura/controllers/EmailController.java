package com.upc.trabajoarquitectura.controllers;
import com.upc.trabajoarquitectura.dtos.manageinfo.EmailDTO;
import com.upc.trabajoarquitectura.interfaces.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmailController {
    @Autowired
    private IEmailService emailService;

    @GetMapping("/enviar-correo")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String enviarCorreo(@RequestBody EmailDTO emailDTO) {
        emailService.enviarCorreo(emailDTO);
        return "Correo enviado con éxito";
    }
}
