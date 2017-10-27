package com.hitachi.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@Service
public class ClientService {

    @Autowired
    private KeycloakRestTemplate template;

    private static String userEndpoint = "http://localhost:8180/service/user/";

    private static String adminEndpoint = "http://localhost:8180/service/admin/";

    public List<String> executeUserApi() {
        try {
            ResponseEntity<String[]> response = template.getForEntity(userEndpoint, String[].class);
            return Arrays.asList(response.getBody());
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }

    public List<String> executeAdminApi() {
        try {
            ResponseEntity<String[]> response = template.getForEntity(adminEndpoint, String[].class);
            return Arrays.asList(response.getBody());
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return new ArrayList<String>();
        
    }
}
