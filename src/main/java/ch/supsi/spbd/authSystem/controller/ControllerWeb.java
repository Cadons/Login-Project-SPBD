package ch.supsi.spbd.authSystem.controller;

import ch.supsi.spbd.authSystem.model.Justification;
import ch.supsi.spbd.authSystem.model.TokenResponse;
import ch.supsi.spbd.authSystem.service.JustificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@Component

public class ControllerWeb {
    private final HttpServletRequest request;

    @Autowired
    public ControllerWeb(HttpServletRequest request) {
        this.request = request;
    }

    @Autowired
    JustificationService service;

    @GetMapping("/")
    public String getAll(Model model) {
        if (request.isUserInRole("admin_app")){
            model.addAttribute("justifications", service.getAllJustifications());
            model.addAttribute("isAdmin",true);

        }
        else{
            model.addAttribute("justifications", service.getJustification());
            model.addAttribute("isAdmin",false);
        }

        return "index";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAnyRole('user_app','admin_app')")
    public String getAddJustification(Model model) {
        model.addAttribute("item",new Justification());
        model.addAttribute("action","/add");
        model.addAttribute("update",false);
        return "form";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('user_app')")
    public String addNew(Justification customer) {
        service.addJustification(customer);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('admin_app')")
    public String deleteJustification(@PathVariable long id) {
        service.deleteJustification(id);
        return "redirect:/";


    }
    @GetMapping("/update/{id}")
    @PreAuthorize("hasRole('admin_app')")
    public String justifyForm(@PathVariable long id,Model model) {
        model.addAttribute("item",service.getJustification(id));
        model.addAttribute("isAdmin",true);
        model.addAttribute("update",true);
        model.addAttribute("action","/update/"+id);
        return "form";


    }
    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('admin_app')")
    public String justify(@PathVariable long id,Justification justification) {

        service.editJustification(id,justification);
        return "redirect:/";


    }

    @GetMapping("/logout")

    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/";
    }
    @GetMapping("/apiKey")

    public String getApi() {
        return "apiKey";
    }
    @PostMapping("/apiKey")

    public String getApiToken(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("otp") String otp,Model model) throws JsonProcessingException {
        //do a http request to keycloak to get a token
        //first create e restemplate variable

        var resp=service.getToken(username,password,otp);
        model.addAttribute("token",resp.getAccess_token());
        return "apiKey";
    }
}
