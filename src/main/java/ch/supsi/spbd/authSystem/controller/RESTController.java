package ch.supsi.spbd.authSystem.controller;

import ch.supsi.spbd.authSystem.model.Justification;
import ch.supsi.spbd.authSystem.service.JustificationService;
import org.keycloak.crypto.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.security.interfaces.RSAPublicKey;
import java.util.List;


@RestController
public class RESTController {
    @Autowired
    private JustificationService service;



    @RequestMapping(value = "/api/", method = RequestMethod.GET)

    public ResponseEntity<List<Justification>> get(String authHeader) {

            return ResponseEntity.ok(service.getAllJustifications());



    }
    @RequestMapping(value = "/api/{id}", method = RequestMethod.GET)
    public ResponseEntity<Justification> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.getJustification(id));
    }
    @RequestMapping(value = "/api/create", method = RequestMethod.POST)
    public ResponseEntity<Justification> _addJustification(Justification justification) {
        return ResponseEntity.ok(service.addJustification(justification));
    }
    //delete justification by id response json with true or false

    @RequestMapping(value = "/api/delete/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyRole('admin_app')")

    public ResponseEntity<Boolean> _deleteJustification(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteJustification(id));
    }
    //edit justification give id response with updated justification if not exist return 404  not found
    @RequestMapping(value = "/api/update/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('admin_app')")

    public ResponseEntity<Justification> _editJustification(@PathVariable long id, Justification justification) {
        return ResponseEntity.ok(service.editJustification(id, justification));
    }


}
