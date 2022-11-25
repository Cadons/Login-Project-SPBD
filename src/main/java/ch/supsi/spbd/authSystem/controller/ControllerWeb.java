package ch.supsi.spbd.authSystem.controller;

import ch.supsi.spbd.authSystem.model.Justification;
import ch.supsi.spbd.authSystem.service.JustificationService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getCustomers(Model model) {
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
    public String addGetCustomers(Model model) {
        model.addAttribute("item",new Justification());
        model.addAttribute("action","/add");
        model.addAttribute("update",false);
        return "form";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('user_app')")
    public String addCustomer(Justification customer) {
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


}
