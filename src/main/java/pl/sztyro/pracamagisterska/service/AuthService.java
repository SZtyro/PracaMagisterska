package pl.sztyro.pracamagisterska.service;

import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.security.Principal;
import java.util.Map;

public class AuthService {

    public static Map<String, String> getPrincipalDetails(Principal principal){
        return (Map<String, String>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
    }

    public static String getPrincipalMail(Principal principal){
        return getPrincipalDetails(principal).get("email");
    }
}
