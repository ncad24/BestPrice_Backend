package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.dtos.manageinfo.EmailDTO;

public interface IEmailService {
    public void enviarCorreo(EmailDTO emailDTO);
}
