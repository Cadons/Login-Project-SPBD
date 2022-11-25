package ch.supsi.spbd.authSystem.service;

import ch.supsi.spbd.authSystem.model.Justification;
import ch.supsi.spbd.authSystem.repository.JustificationRepository;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.IDToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
    public Justification getJustification(long id) {


        Optional<Justification> c = repository.findById(id);
        if (c.isPresent())
            return c.get();
        else
            return null;
    }

    public Justification addJustification(Justification customer) {
        customer.setUserID(getUserID());
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
