package com.hitachi.client;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.IDToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientController {

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    @Autowired
    private ClientService service;

    @RequestMapping("/app")
    public String app(Model model) {
        List<String> users = service.executeUserApi();
        model.addAttribute("users", users);

        List<String> admin = service.executeAdminApi();
        model.addAttribute("admin", admin);

        model.addAttribute("name", getUserName());
        model.addAttribute("roles", getUserRoles().toString());

        return "app/index";
    }

    @RequestMapping("/app/user")
    public String user(Model model) {
        model.addAttribute("message", "hello user");
        return "app/view";
    }

    @RequestMapping("/app/admin")
    public String admin(Model model) {
        model.addAttribute("message", "hello admin");
        return "app/view";
    }

    private String getUserName() {
        IDToken token = getIdToken();
        return token != null ? token.getPreferredUsername() : "anonymous";
    }

    private Set<String> getUserRoles() {
        Authentication auth = getAuthentication();
        if (auth instanceof KeycloakAuthenticationToken) {
            KeycloakAuthenticationToken keycloak = (KeycloakAuthenticationToken) auth;
            return keycloak.getAccount().getRoles();
        }
        return new HashSet<String>();
    }

    private IDToken getIdToken() {
        Authentication auth = getAuthentication();
        if (auth instanceof KeycloakAuthenticationToken) {
            KeycloakAuthenticationToken keycloak = (KeycloakAuthenticationToken) auth;
            return keycloak.getAccount().getKeycloakSecurityContext().getIdToken();
        }
        return null;
    }

    private Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        return context.getAuthentication();
    }
}
