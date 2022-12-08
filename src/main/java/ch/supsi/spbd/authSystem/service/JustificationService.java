package ch.supsi.spbd.authSystem.service;

import ch.supsi.spbd.authSystem.model.Justification;
import ch.supsi.spbd.authSystem.model.TokenResponse;
import ch.supsi.spbd.authSystem.repository.JustificationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.IDToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class JustificationService {
    @Autowired
    JustificationRepository repository;

    public String getUserID() {
        KeycloakAuthenticationToken authentication =
                (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        Principal principal = (Principal) authentication.getPrincipal();
        String userIdByMapper = "";

        if (principal instanceof KeycloakPrincipal) {
            KeycloakPrincipal<KeycloakSecurityContext> kPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
            IDToken token = kPrincipal.getKeycloakSecurityContext().getIdToken();
            userIdByMapper = token.getOtherClaims().get("user_id").toString();
        }
        return userIdByMapper;
    }
    public String getUsername() {
        KeycloakAuthenticationToken authentication =
                (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        Principal principal = (Principal) authentication.getPrincipal();

        return principal.getName();
    }
    public String getRole() {
        KeycloakAuthenticationToken authentication =
                (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        Principal principal = (Principal) authentication.getPrincipal();

        String userIdByMapper = "";

        if (principal instanceof KeycloakPrincipal) {
            KeycloakPrincipal<KeycloakSecurityContext> kPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
            IDToken token = kPrincipal.getKeycloakSecurityContext().getIdToken();
            userIdByMapper = token.getOtherClaims().get("user_id").toString();
        }
        return userIdByMapper;
    }
    public List<Justification> getJustification() {
        return repository.findAllByUserID(getUserID());
    }
    public List<Justification> getAllJustifications() {
        return repository.findAll();
    }
    public TokenResponse getToken(String username, String password, String otp) throws JsonProcessingException {

        RestTemplate restTemplate=new RestTemplate();
//you can create and edit header
        HttpHeaders header= new HttpHeaders();

        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        header.add("Accept", "application/json");

//you can create and edit body to
        MultiValueMap<String, String> body= new LinkedMultiValueMap<String, String>();
        body.add("grant_type", "password");
        body.add("client_secret", "mUeJQzcRqASyZLBZx8ksIHmBntc1HPvU");
        body.add("client_id", "springboot");
        body.add("username", username);
        body.add("password", password);
        body.add("totp",otp );
        HttpEntity<MultiValueMap<String, String>> requeteHttp =new HttpEntity<MultiValueMap<String, String>>(body, header);

//After you can create a request
        ResponseEntity<String> response = restTemplate.postForEntity("http://auth.supsilab.ch/realms/spbd/protocol/openid-connect/token", requeteHttp , String.class);
//if you want to send a get request you can edit postForEntity to get
        String filteredResponse=  response.getBody().replace("not-before-policy","not_before_policy");
        ObjectMapper mapper = new ObjectMapper();
        var resp= mapper.readValue(filteredResponse, TokenResponse.class);
        return resp;
    }
    public Justification getJustification(long id) {


        Optional<Justification> c = repository.findById(id);
        if (c.isPresent())
            return c.get();
        else
            return null;
    }

    public Justification addJustification(Justification customer) {
        customer.setUserID(getUserID());
        customer.setUsername(getUsername());
        System.out.println(getUsername());
        return repository.save(customer);
    }

    public Justification editJustification(long id,Justification newJustification) {
        var customerReq = repository.findById(id);
        if (customerReq.isPresent()) {
            var customer = customerReq.get();
            customer.setStatus(newJustification.getStatus());

            return repository.save(customer);
        }

       return null;
    }

    public boolean deleteJustification(long customer) {
        try {

            repository.deleteById(customer);


            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
