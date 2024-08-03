package com.project.Shop.utils;

import com.project.Shop.entity.Account;
import com.project.Shop.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserLoginUtil {
    public static Account getCurrentLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            return customUserDetails.getAccount();
        }

        // Handle the case where the principal is not a CustomUserDetails
        return null; // or throw an exception, depending on your use case
    }
}
