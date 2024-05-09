package ru.msu.cmc.webdev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.msu.cmc.webdev.DAO.CustomersDAO;
import ru.msu.cmc.webdev.DAO.OrdersDAO;
import ru.msu.cmc.webdev.DAO.implementations.CustomersDAO_Implementation;
import ru.msu.cmc.webdev.DAO.implementations.OrdersDAO_Implementation;
import ru.msu.cmc.webdev.models.Customers;

import java.util.List;
import java.util.Objects;

@Controller
public class CustomerController {

    @Autowired
    private final CustomersDAO customersDAO = new CustomersDAO_Implementation();

    @Autowired
    private final OrdersDAO ordersDAO = new OrdersDAO_Implementation();

    @GetMapping("/customers")
    public String customersListPage(Model model) {
        List<Customers> customers = (List<Customers>) customersDAO.getAllCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("customerService", customersDAO);
        return "customers";
    }

    @GetMapping("/customer")
    public String customerPage(@RequestParam(name = "customerId") Long customerId, Model model) {
        Customers customer = customersDAO.getById(customerId);

        if (customer == null) {
            model.addAttribute("error_msg", "В базе нет заказчика с ID = " + customerId);
            return "errorPage";
        }

        model.addAttribute("customer", customer);
        model.addAttribute("customerService", customersDAO);
        model.addAttribute("ordersService", ordersDAO);
        return "customer";
    }

    @GetMapping("/editCustomer")
    public String editCustomerPage(@RequestParam(name = "customerId", required = false) Long customerId, Model model) {
        if (customerId == null) {
            model.addAttribute("customer", new Customers());
            model.addAttribute("customerService", customersDAO);
            return "editCustomer";
        }

        Customers customer = customersDAO.getById(customerId);

        if (customer == null) {
            model.addAttribute("error_msg", "В базе нет человека с ID = " + customerId);
            return "errorPage";
        }

        model.addAttribute("customer", customer);
        model.addAttribute("customerService", customersDAO);
        return "editCustomer";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomerPage(@RequestParam(name = "customerId") Long customerId,
                                @RequestParam(name = "customerName") String name,
                                @RequestParam(name = "customerPhone", required = false) String phone,
                                @RequestParam(name = "customerEmail", required = false) String email,
                                @RequestParam(name = "customerAddress", required = false) String address,
                                @RequestParam(name = "customerDescription", required = false) String info,
                                Model model) {
        List<Customers> customers = customersDAO.getAllCustomers();
        boolean changeIsSuccessful = false;
        Customers customer = null;
        if (customerId != 0) {
            customer = customersDAO.getById(customerId);
        }
        if (customerId != 0 && customer != null) {
            customer.setName(name);
            if (phone!= null) {
                customer.setPhone(phone);
            }
            if (email!= null) {
                customer.setEmail(email);
            }
            if (address!= null) {
                customer.setAddress(address);
            }
            if (info!= null) {
                customer.setDescription(info);
            }
            customersDAO.update(customer);
        } else {
            customer = new Customers();
            customer.setName(name);
            if (phone!= null) {
                customer.setPhone(phone);
            }
            if (email!= null) {
                customer.setEmail(email);
            }
            if (address!= null) {
                customer.setAddress(address);
            }
            if (info!= null) {
                customer.setDescription(info);
            }
            customersDAO.save(customer);
        }
        return "index";
    }


    @GetMapping("/searchCustomer")
    public String searchEmployee(@RequestParam(required = true) String name,
                                 @RequestParam(required = false) String sortingId,
                                 @RequestParam(required = false) String asc,
                                 Model model) {

        List<Customers> customers;
        if (sortingId.equals("Имя")) {
            if (Objects.equals(asc, "по убыванию")) {
                customers = customersDAO.getCustomersSortedByNameDESC(name);
            } else {
                customers = customersDAO.getCustomersSortedByNameASC(name);
            }
        } else if (sortingId.equals("Количество поставок за последний год")) {
            if (Objects.equals(asc, "по убыванию")) {
                customers = customersDAO.getCustomersSortedByOrderPerYearDESC(name);
            } else {
                customers = customersDAO.getCustomersSortedByOrderPerYearASC(name);
            }
        } else if (sortingId.equals("Общее количество поставок")) {
            if (Objects.equals(asc, "по убыванию")) {
                customers = customersDAO.getCustomersSortedByNumberOfOrdersDESC(name);
            } else {
                customers = customersDAO.getCustomersSortedByNumberOfOrdersASC(name);
            }
        } else {
            customers = customersDAO.getAllCustomersByName(name);
        }

        model.addAttribute("customers", customers);
        model.addAttribute("customersDAO", customersDAO);
        return "customers";
    }

    @PostMapping("/removeCustomer")
    public String removeCustomerPage(@RequestParam(name = "customerId") Long customerId) {
        customersDAO.deleteById(customerId);
        return "redirect:/customers";
    }
}