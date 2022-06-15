/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.teachJava5.teachJava5.service;

import com.teachJava5.teachJava5.dto.Mail;
import java.io.IOException;
import javax.mail.MessagingException;

/**
 *
 * @author tomnyson
 */
public interface MailService {

    void sendEmail(Mail mail) throws MessagingException, IOException;
    
}
