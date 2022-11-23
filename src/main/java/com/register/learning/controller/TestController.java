package com.register.learning.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.register.learning.entity.BaseUser;
import com.register.learning.entity.RegistrationUser;
import com.register.learning.entity.ResponseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String REGISTRATION_URL = "http://localhost:8080/user/register";
    private static final String AUTHENTICATION_URL = "http://localhost:8080/authenticate";
    private static final String HELLO_URL = "http://localhost:8080/hello";


    @GetMapping("/getResponse")
    public String getResponse() throws JsonProcessingException {
        String response = null;
        RegistrationUser registrationUser = getRegistrationUser();
        String registrationBody = getBody(registrationUser);
        HttpHeaders registrationHeaders = getHeaders();
        HttpEntity<String> registrationEntity = new HttpEntity<String>(registrationBody, registrationHeaders);
        try {
            ResponseEntity<String> registrationResponse = restTemplate.exchange(REGISTRATION_URL, HttpMethod.POST
            , registrationEntity, String.class);
            if(registrationResponse.getStatusCode().equals(HttpStatus.OK)) {
                BaseUser authenticationUser = getAuthenticationUser();
                String authenticationBody = getBody(authenticationUser);
                HttpHeaders authenticationHeaders = getHeaders();
                HttpEntity<String> authenticationEntity = new HttpEntity<String>(authenticationBody, authenticationHeaders);

                ResponseEntity<ResponseToken> authenticationResponse = restTemplate.exchange(AUTHENTICATION_URL,
                        HttpMethod.POST, authenticationEntity, ResponseToken.class);

                if(authenticationResponse.getStatusCode().equals(HttpStatus.OK)) {
                    String token = "Bearer " + authenticationResponse.getBody().getToken();
                    HttpHeaders headers = getHeaders();
                    headers.set("Authorization", token);
                    HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);

                    ResponseEntity<String> hello = restTemplate.exchange(HELLO_URL, HttpMethod.GET, jwtEntity, String.class);
                    if(hello.getStatusCode().equals(HttpStatus.OK)) {
                        response = hello.getBody();
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return response;
    }

    private RegistrationUser getRegistrationUser() {
        RegistrationUser user = new RegistrationUser();
        user.setEmail("Vibhuti@yopmail.com");
        user.setPassword("Vibhuti@123");
        user.setFirstName("Vibhuti");
        user.setRole("ROLE_ADMIN");
        return user;
    }

    private BaseUser getAuthenticationUser(){
        BaseUser user = new BaseUser();
        user.setEmail("Vibhuti@yopmail.com");
        user.setPassword("Vibhuti@123");
        return user;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    private String getBody(final BaseUser baseUser) throws JsonProcessingException{
        return new ObjectMapper().writeValueAsString(baseUser);
    }

}
