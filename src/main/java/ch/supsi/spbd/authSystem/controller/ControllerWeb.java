package ch.supsi.spbd.authSystem.controller;

import ch.supsi.spbd.authSystem.model.Customer;
import ch.supsi.spbd.authSystem.service.CustomerService;
import org.apache.http.HttpStatus;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@Controller
@Component
@RequestMapping("/api/*")
public class ControllerWeb {
    private final HttpServletRequest request;

    @Autowired
    public ControllerWeb(HttpServletRequest request) {
        this.request = request;
    }
    @Autowired
    CustomerService service;

    @GetMapping("/")
    public String getCustomers(Model model){

        model.addAttribute("customers",service.getCustomers());
        return "index";
    }
    @GetMapping("/add")
    public String addGetCustomers(){
        return "add";
    }
    @PostMapping("/add")
    public String addCustomer(Customer customer){
        service.addCustomer(customer);
        return "redirect:/";
    }
    @GetMapping("/logout")

    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/";
    }


}
