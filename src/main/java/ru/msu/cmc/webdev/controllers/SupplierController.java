package ru.msu.cmc.webdev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.msu.cmc.webdev.DAO.SuppliersDAO;
import ru.msu.cmc.webdev.DAO.SuppliesDAO;
import ru.msu.cmc.webdev.DAO.implementations.SuppliersDAO_Implementation;
import ru.msu.cmc.webdev.DAO.implementations.SuppliesDAO_Implementation;
import ru.msu.cmc.webdev.models.Suppliers;

import java.util.List;
import java.util.Objects;

@Controller
public class SupplierController {

    @Autowired
    private final SuppliersDAO suppliersDAO = new SuppliersDAO_Implementation();

    @Autowired
    private final SuppliesDAO suppliesDAO = new SuppliesDAO_Implementation();

    @GetMapping("/suppliers")
    public String suppliersListPage(Model model) {
        List<Suppliers> suppliers = (List<Suppliers>) suppliersDAO.getAll();
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("supplierService", suppliersDAO);
        return "suppliers";
    }

    @GetMapping("/supplier")
    public String supplierPage(@RequestParam(name = "supplierId") Long supplierId, Model model) {
        Suppliers supplier = suppliersDAO.getById(supplierId);

        if (supplier == null) {
            model.addAttribute("error_msg", "В базе нет поставщика с ID = " + supplierId);
            return "errorPage";
        }

        model.addAttribute("supplier", supplier);
        model.addAttribute("supplierService", suppliersDAO);
        model.addAttribute("suppliesService", suppliesDAO);
        return "supplier";
    }

    @GetMapping("/editSupplier")
    public String editSupplierPage(@RequestParam(name = "supplierId", required = false) Long supplierId, Model model) {
        if (supplierId == null) {
            model.addAttribute("supplier", new Suppliers());
            model.addAttribute("supplierService", suppliersDAO);
            return "editSupplier";
        }

        Suppliers supplier = suppliersDAO.getById(supplierId);

        if (supplier == null) {
            model.addAttribute("error_msg", "В базе нет поставщика с ID = " + supplierId);
            return "errorPage";
        }

        model.addAttribute("supplier", supplier);
        model.addAttribute("supplierService", suppliersDAO);
        return "editSupplier";
    }

    @PostMapping("/saveSupplier")
    public String saveSupplierPage(@RequestParam(name = "supplierId") Long supplierId,
                                 @RequestParam(name = "supplierName") String name,
                                 @RequestParam(name = "supplierPhone", required = false) String phone,
                                 @RequestParam(name = "supplierEmail", required = false) String email,
                                 @RequestParam(name = "supplierAddress", required = false) String address,
                                 @RequestParam(name = "supplierDescription", required = false) String info,
                                 Model model) {
        int num_suppliers = suppliersDAO.getAllSuppliers().size();
        Suppliers supplier = null;
        if (supplierId != 0 && supplierId <= num_suppliers) {
            supplier = suppliersDAO.getById(supplierId);
        }
        if (supplierId != 0 && supplier != null) {
            supplier.setName(name);
            if (phone!= null) {
                supplier.setPhone(phone);
            }
            if (email!= null) {
                supplier.setEmail(email);
            }
            if (address!= null) {
                supplier.setAddress(address);
            }
            if (info!= null) {
                supplier.setDescription(info);
            }
            suppliersDAO.update(supplier);
        } else {
            supplier = new Suppliers();
            supplier.setId(supplierId);
            supplier.setName(name);
            if (phone!= null) {
                supplier.setPhone(phone);
            }
            if (email!= null) {
                supplier.setEmail(email);
            }
            if (address!= null) {
                supplier.setAddress(address);
            }
            if (info!= null) {
                supplier.setDescription(info);
            }
            suppliersDAO.save(supplier);
        }
        return "index";
    }


    @GetMapping("/searchSupplier")
    public String searchEmployee(@RequestParam(required = true) String name,
                                 @RequestParam(required = false) String sortingId,
                                 @RequestParam(required = false) String asc,
                                 Model model) {

        List<Suppliers> suppliers;
        if (sortingId.equals("Имя")) {
            if (Objects.equals(asc, "по убыванию")) {
                suppliers = suppliersDAO.getSuppliersSortedByNameDESC(name);
            } else {
                suppliers = suppliersDAO.getSuppliersSortedByNameASC(name);
            }
        } else if (sortingId.equals("Количество поставок за последний год")) {
            if (Objects.equals(asc, "по убыванию")) {
                suppliers = suppliersDAO.getSuppliersSortedBySuppliesPerYearDESC(name);
            } else {
                suppliers = suppliersDAO.getSuppliersSortedBySuppliesPerYearASC(name);
            }
        } else if (sortingId.equals("Общее количество поставок")) {
            if (Objects.equals(asc, "по убыванию")) {
                suppliers = suppliersDAO.getSuppliersSortedByNumberOfSuppliesDESC(name);
            } else {
                suppliers = suppliersDAO.getSuppliersSortedByNumberOfSuppliesASC(name);
            }
        } else {
            suppliers = suppliersDAO.getAllSuppliersByName(name);
        }

        model.addAttribute("suppliers", suppliers);
        model.addAttribute("suppliersDAO", suppliersDAO);
        return "suppliers";
    }

    @PostMapping("/removeSupplier")
    public String removeSupplierPage(@RequestParam(name = "supplierId") Long supplierId) {
        suppliersDAO.deleteById(supplierId);
        return "redirect:/suppliers";
    }
}