package com.upc.trabajoarquitectura.servicies;
import com.upc.trabajoarquitectura.dtos.manageinfo.EmailDTO;
import com.upc.trabajoarquitectura.interfaces.IEmailService;
import com.upc.trabajoarquitectura.respositories.UserAppRepository;
import com.upc.trabajoarquitectura.util.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService implements IEmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private UserAppRepository userAppRepository;

    public void enviarCorreo(EmailDTO emailDTO) {
        validationService.verifyExistsID(userAppRepository.existsByEmail(emailDTO.getEmail()),"Email");
        SimpleMailMessage email = new SimpleMailMessage();
        String subject = "Restablecer contraseña";
        String message = "Este es el link para restablecer su contraseña de la aplicación de BestPrice:";
        email.setTo(emailDTO.getEmail());
        email.setSubject(subject);
        email.setText(message);
        email.setFrom("neeraaranguri@gmail.com");
        mailSender.send(email);
    }
}
