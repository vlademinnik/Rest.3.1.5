package org.example.service;

import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://91.241.64.178:7081/api/users";
    private String cookie;

    public List<User> getAllUsers(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity(URL, String.class);
        cookie = forEntity.getHeaders().getFirst("Set-Cookie");
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<User>>() {});
        List<User> allUsers = responseEntity.getBody();
        return allUsers;
    }

    public void saveUser(User user){
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Cookie", cookie);
    HttpEntity httpEntity = new HttpEntity(user, httpHeaders);
    ResponseEntity response = restTemplate.exchange(URL, HttpMethod.POST, httpEntity, String.class);
        System.out.println(response.getBody());
    }

    public void update(User user){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", cookie);
        HttpEntity httpEntity = new HttpEntity(user, httpHeaders);
        ResponseEntity response = restTemplate.exchange(URL, HttpMethod.PUT, httpEntity, String.class);
        System.out.println(response.getBody());

    }
    public void deleteUser(Long id){
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Cookie", cookie);
    HttpEntity httpEntity = new HttpEntity(id, httpHeaders);
    ResponseEntity response = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, httpEntity, String.class);
        System.out.println(response.getBody());
    }
}
