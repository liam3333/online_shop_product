package online.shop.product.gateway.util;

import online.shop.product.gateway.model.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthUtil {

    @Autowired
    private RestTemplate restTemplate;

    public String getToken(String userName, String role) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("userName",userName);
        headers.set("role",role);
        HttpEntity<Credential> request = new HttpEntity<>(
                new Credential("hugo", "admin"),headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:5002/login", HttpMethod.POST,request,String.class);
        System.out.println("token:"+response.getBody());
        return response.getBody();
    }
}
