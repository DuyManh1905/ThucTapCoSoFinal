package com.project.Shop.service;

import com.project.Shop.entity.Account;
import com.project.Shop.entity.VerificationCode;

import javax.mail.MessagingException;

public interface VerificationCodeService {
    VerificationCode createVerificationCode(String email) throws MessagingException;

    Account verifyCode( String code);
}