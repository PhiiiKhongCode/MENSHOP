package com.fpoly.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserSys {
    public static String getUserLogin(){
        if(SecurityContextHolder.getContext()!= null){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            return currentPrincipalName;
        }
        return "namtv";
    }
}
