package io.github.marcocrowe.web.controllers;

import io.github.marcocrowe.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public ModelAndView getCustomersPage() {
        var customers = customerService.findCustomers();
        var modelAndView = new ModelAndView(TemplatePaths.CUSTOMERS);
        modelAndView.addObject("customers", customers);

        return modelAndView;
    }
}
