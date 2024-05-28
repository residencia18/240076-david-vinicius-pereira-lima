package com.ProvaGrupo.SpringEcommerce.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Class to represent a mail object with the necessary fields. 
 */
@RequiredArgsConstructor
@Getter
@Setter
public class Mail {
    
    /**
     * The email address of the sender.
     */
    private String from;
    
    /**
     * The email address of the recipient.
     */
    private String to;
    
    /**
     * The subject of the email.
     */
    private String subject;
    
    /**
     * The content of the email.
     */
    private String content;
    
}