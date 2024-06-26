package ru.msu.cmc.webdev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.msu.cmc.webdev.DAO.Warehouse_spacesDAO;
import ru.msu.cmc.webdev.DAO.implementations.Warehouse_spacesDAO_Implementation;

@Controller
public class WarehouseController {

    @Autowired
    private final Warehouse_spacesDAO wcService = new Warehouse_spacesDAO_Implementation();

    @GetMapping("/wcMain")
    public String wcMainPage(Model model) {
        model.addAttribute("toysNotFree", wcService.getNotEmptySpacesByType("toys") == null ? 40 : 40 - wcService.getNotEmptySpacesByType("toys").size());
        model.addAttribute("clothesNotFree", wcService.getNotEmptySpacesByType("clothes") == null ? 40 : 40 - wcService.getNotEmptySpacesByType("clothes").size());
        model.addAttribute("foodNotFree", wcService.getNotEmptySpacesByType("food") == null ? 40 : 40 - wcService.getNotEmptySpacesByType("food").size());
        model.addAttribute("electronicsNotFree", wcService.getNotEmptySpacesByType("electronics") == null ? 40 : 40 - wcService.getNotEmptySpacesByType("electronics").size());
        model.addAttribute("wcService", wcService);
        return "wcMain";
    }

    @GetMapping("/forButton")
    public String editSellerPage(Model model) {
        model.addAttribute("toysNotFree", wcService.getNotEmptySpacesByType("toys"));
        model.addAttribute("clothesNotFree", wcService.getNotEmptySpacesByType("clothes"));
        model.addAttribute("foodNotFree", wcService.getNotEmptySpacesByType("food"));
        model.addAttribute("electronicsNotFree", wcService.getNotEmptySpacesByType("electronics"));
        model.addAttribute("wcService", wcService);
        return "wcLookup";
    }
    @PostMapping("/lookupPlace")
    public String lookupPage(@RequestParam(name = "clothes", required = false) Long clothes,
                             @RequestParam(name = "toys", required = false) Long toys,
                             @RequestParam(name = "food", required = false) Long food,
                             @RequestParam(name = "electronics", required = false) Long electronics,
                             Model model) {

//        return "wcSuccess";
        if ((clothes <= (wcService.getNotEmptySpacesByType("clothes") == null ? 40 : 40 - wcService.getNotEmptySpacesByType("clothes").size())) &&
                (toys <= (wcService.getNotEmptySpacesByType("toys") == null ? 40 : 40 - wcService.getNotEmptySpacesByType("toys").size())) &&
                (food <= (wcService.getNotEmptySpacesByType("food") == null ? 40 : 40 - wcService.getNotEmptySpacesByType("food").size())) &&
                (electronics <= (wcService.getNotEmptySpacesByType("electronics") == null ? 40 : 40 - wcService.getNotEmptySpacesByType("electronics").size()))) {
            return "wcSuccess";
        } else {
            return "wcFail";
        }
    }
}