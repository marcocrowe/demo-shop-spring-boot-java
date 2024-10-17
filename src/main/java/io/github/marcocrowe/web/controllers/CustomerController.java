package io.github.marcocrowe.web.controllers;

import io.github.marcocrowe.model.Customer;
import io.github.marcocrowe.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}/details")
    public String getCustomerDetailsView(@PathVariable("customerId") String customerIdText, Model model) {
        int customerId = Integer.parseInt(customerIdText);
        Customer customer = customerService.findCustomerById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id: " + customerId));

        model.addAttribute("customer", customer);

        return TemplatePaths.CUSTOMER_DETAILS;
    }

    @GetMapping("/{customerId}/edit")
    public String getCustomerEditPage(@PathVariable("customerId") int customerId, Model model)
    {
        Customer customer = customerService.findCustomerById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Customer Id: %d".formatted(customerId)));

        model.addAttribute("customer", customer);
        return TemplatePaths.CUSTOMER_EDIT;
    }


    @GetMapping()
    public ModelAndView getCustomersPage(Model model) {
        var customers = customerService.findCustomers();
        var modelAndView = new ModelAndView(TemplatePaths.CUSTOMERS);
        modelAndView.addObject("customers", customers);

        if (model.containsAttribute("message")) {
            modelAndView.addObject("message", model.getAttribute("message"));
        }

        return modelAndView;
    }

    @GetMapping("/{customerId}/delete")
    public String executeDeleteCustomer(@PathVariable("customerId") Integer customerId, RedirectAttributes redirectAttributes) {
        customerService.deleteCustomerById(customerId);

        redirectAttributes.addFlashAttribute("message", "Customer deleted successfully");
        return "redirect:/customers";
    }

    @PostMapping("/{customerId}/edit")
    public String executeUpdateCustomer(@PathVariable("customerId") int customerId, @Valid Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            customer.setCustomerId(customerId);
            return TemplatePaths.CUSTOMER_EDIT;
        }

        customerService.updateCustomer(customer);
        return "redirect:/customers/%d/details".formatted(customerId);
    }
}
