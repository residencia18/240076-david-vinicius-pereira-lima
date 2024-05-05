package org.avaliacao.ap002.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService{

    private JavaMailSender javaMailSender;

}
