package ch.supsi.spbd.authSystem.controller;

import ch.supsi.spbd.authSystem.model.Justification;
import ch.supsi.spbd.authSystem.service.JustificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RESTController {
    @Autowired
    private JustificationService service;

    @RequestMapping(value = "/api/", method = RequestMethod.GET)
    private ResponseEntity<List<Justification>> get() {
        return ResponseEntity.ok(service.getAllJustifications());
    }
    @RequestMapping(value = "/api/{id}", method = RequestMethod.GET)
    private ResponseEntity<Justification> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.getJustification(id));
    }
    @RequestMapping(value = "/api/create", method = RequestMethod.POST)
    private ResponseEntity<Justification> _addJustification(Justification justification) {
        return ResponseEntity.ok(service.addJustification(justification));
    }
    //delete justification by id response json with true or false
    @RequestMapping(value = "/api/delete/{id}", method = RequestMethod.DELETE)
    private ResponseEntity<Boolean> _deleteJustification(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteJustification(id));
    }
    //edit justification give id response with updated justification if not exist return 404  not found
    @RequestMapping(value = "/api/update/{id}", method = RequestMethod.PUT)
    private ResponseEntity<Justification> _editJustification(@PathVariable long id, Justification justification) {
        return ResponseEntity.ok(service.editJustification(id, justification));
    }


}
