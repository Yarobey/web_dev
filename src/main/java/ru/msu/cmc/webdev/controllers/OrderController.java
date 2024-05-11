package ru.msu.cmc.webdev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.msu.cmc.webdev.DAO.*;
import ru.msu.cmc.webdev.DAO.implementations.*;
import ru.msu.cmc.webdev.models.Orders;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class OrderController {

    @Autowired
    private final OrdersDAO ordersDAO = new OrdersDAO_Implementation();

    @Autowired
    private final CustomersDAO customersDAO = new CustomersDAO_Implementation();

    @Autowired
    private final Products_orderedDAO productsDAO = new Products_orderedDAO_Implementation();

    @Autowired
    private final ProductsDAO allProductsDAO = new ProductsDAO_Implementation();

    @Autowired
    private final Warehouse_spacesDAO wcService = new Warehouse_spacesDAO_Implementation();

    @GetMapping("/orders")
    public String ordersListPage(Model model) {
        List<Orders> orders = (List<Orders>) ordersDAO.getAllOrders();
        model.addAttribute("orders", orders);
        model.addAttribute("orderService", ordersDAO);
        return "orders";
    }

    @GetMapping("/order")
    public String orderPage(@RequestParam(name = "orderId") Long orderId, Model model) {
        Orders order = ordersDAO.getById(orderId);

        if (order == null) {
            model.addAttribute("error_msg", "В базе нет заказа с ID = " + orderId);
            return "errorPage";
        }

        model.addAttribute("order", order);
        model.addAttribute("orderService", ordersDAO);
        model.addAttribute("productsService", productsDAO);
        model.addAttribute("allProductsService", allProductsDAO);
        return "order";
    }

    @GetMapping("/editOrder")
    public String editOrderPage(@RequestParam(name = "orderId", required = false) Long orderId, Model model) {
        if (orderId == null) {
            model.addAttribute("order", new Orders());
            model.addAttribute("orderService", ordersDAO);
            return "editOrder";
        }

        Orders order = ordersDAO.getById(orderId);

        if (order == null) {
            model.addAttribute("error_msg", "В базе нет заказа с ID = " + orderId);
            return "errorPage";
        }

        model.addAttribute("order", order);
        model.addAttribute("orderService", ordersDAO);
        model.addAttribute("wcService", wcService);
        return "editOrder";
    }

    @PostMapping("/saveOrder")
    public String saveOrderPage(@RequestParam(name = "orderId") Long orderId,
                                   @RequestParam(name = "orderDateTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                   @RequestParam(name = "orderComment", required = false) String info,
                                   @RequestParam(name = "orderCustomerName", required = true) String customerName,
                                   Model model) {
        Orders order = ordersDAO.getById(orderId);
        List<Orders> orders = ordersDAO.getAllOrders();
        boolean changeIsSuccessful = false;

        if (order != null) {
            order.setShip_date(date);
            if (info!= null) {
                order.setComment(info);
            }
            if (customerName!= null) {
                if (customersDAO.getAllCustomersByName(customerName) != null && customersDAO.getAllCustomersByName(customerName).size() == 1) {
                    order.setCustomer(customersDAO.getAllCustomersByName(customerName).get(0));
                }
                order.setCustomer_name(customerName);
            }
            ordersDAO.update(order);
        } else {
            order = new Orders(orderId, customersDAO.getAllCustomersByName(customerName).get(0), date, info, customerName);
            ordersDAO.save(order);
        }
        return "index";
    }


    @GetMapping("/searchOrder")
    public String searchEmployee(@RequestParam(name = "date1", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,
                                 @RequestParam(name = "date2", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2,
                                 @RequestParam(name = "sortingId", required = false) String sortingId,
                                 @RequestParam(name = "asc", required = false) String asc,
                                 Model model) {

        List<Orders> orders = null;
        if (Objects.equals(sortingId, "Дата")) {
            if (Objects.equals(asc, "по убыванию")) {
                orders = ordersDAO.getOrdersSortedByPeriodDESC(date1, date2);
            } else {
                orders = ordersDAO.getOrdersSortedByPeriodASC(date1, date2);
            }
        } else if (Objects.equals(sortingId, "Количество товаров")) {
            if (Objects.equals(asc, "по убыванию")) {
                orders = ordersDAO.getOrdersSortedByNumberOfProductsInPeriodDESC(date1, date2);
            } else {
                orders = ordersDAO.getOrdersSortedByNumberOfProductsInPeriodASC(date1, date2);
            }
        } else if (Objects.equals(sortingId, "Имя заказчика")) {
            if (Objects.equals(asc, "по убыванию")) {
                orders = ordersDAO.getOrdersSortedByCustomerNameInPeriodDESC(date1, date2);
            } else {
                orders = ordersDAO.getOrdersSortedByCustomerNameInPeriodASC(date1, date2);
            }
        } else {
            orders = ordersDAO.getAllOrdersByPeriod(date1, date2);
        }
        model.addAttribute("orders", orders);
        model.addAttribute("ordersDAO", ordersDAO);
        return "orders";
    }

    @PostMapping("/removeOrder")
    public String removeOrderPage(@RequestParam(name = "orderId") Long orderId) {
        ordersDAO.deleteById(orderId);
        return "redirect:/orders";
    }
}