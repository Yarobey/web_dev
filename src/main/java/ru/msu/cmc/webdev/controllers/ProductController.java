package ru.msu.cmc.webdev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.msu.cmc.webdev.DAO.ProductsDAO;
import ru.msu.cmc.webdev.DAO.Warehouse_spacesDAO;
import ru.msu.cmc.webdev.DAO.implementations.ProductsDAO_Implementation;
import ru.msu.cmc.webdev.DAO.implementations.Warehouse_spacesDAO_Implementation;
import ru.msu.cmc.webdev.models.Products;

import java.util.List;
import java.util.Objects;
import java.util.Date;

@Controller
public class ProductController {

    @Autowired
    private final ProductsDAO productsDAO = new ProductsDAO_Implementation();

    @Autowired
    private final Warehouse_spacesDAO wcDAO = new Warehouse_spacesDAO_Implementation();

    @GetMapping("/products")
    public String productsListPage(Model model) {
        List<Products> products = (List<Products>) productsDAO.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("productService", productsDAO);
        return "products";
    }

    @GetMapping("/product")
    public String productPage(@RequestParam(name = "productId") Long productId, Model model) {
        Products product = productsDAO.getById(productId);

        if (product == null) {
            model.addAttribute("error_msg", "В базе нет товара с ID = " + productId);
            return "errorPage";
        }

        model.addAttribute("product", product);
        model.addAttribute("productService", productsDAO);
        model.addAttribute("wcService", wcDAO);
        return "product";
    }

    @GetMapping("/editProduct")
    public String editProductPage(@RequestParam(name = "productId", required = false) Long productId, Model model) {
        if (productId == null) {
            model.addAttribute("product", new Products());
            model.addAttribute("productService", productsDAO);
            return "editProduct";
        }

        Products product = productsDAO.getById(productId);

        if (product == null) {
            model.addAttribute("error_msg", "В базе нет товара с ID = " + productId);
            return "errorPage";
        }

        model.addAttribute("product", product);
        model.addAttribute("productService", productsDAO);
        return "editProduct";
    }

    @PostMapping("/saveProduct")
    public String saveProductPage(@RequestParam(name = "productId") Long productId,
                               @RequestParam(name = "productName") String name,
                               @RequestParam(name = "productType", required = true) String type,
                               @RequestParam(name = "productSize1", required = false) Long size1,
                               @RequestParam(name = "productSize2", required = false) Long size2,
                               @RequestParam(name = "productSize3", required = false) Long size3,
                               @RequestParam(name = "productQuantity", required = false) Long quantity,
                               @RequestParam(name = "productDescription", required = false) String info,
                               @RequestParam(name = "productExpiration_date", required = false) Date expiration_date,
                               Model model) {
        List<Products> products = productsDAO.getAllProducts();
        Products product = null;
        if (productId != 0) {
            product = productsDAO.getById(productId);
        }
        if (productId != 0 && product != null) {
            product.setName(name);
            if (type!= null) {
                product.setType(type);
            }
            if (size1!= null) {
                product.setSize1(size1);
            }
            if (size2!= null) {
                product.setSize2(size2);
            }
            if (size3!= null) {
                product.setSize3(size3);
            }
            if (quantity!= null) {
                product.setQuantity(quantity);
            }
            if (info!= null) {
                product.setDescription(info);
            }
            if (expiration_date!= null) {
                product.setExpiration_date(expiration_date);
            }
            productsDAO.update(product);
        } else {
            product = new Products();
            product.setName(name);
            if (type!= null) {
                product.setType(type);
            }
            if (size1!= null) {
                product.setSize1(size1);
            }
            if (size2!= null) {
                product.setSize2(size2);
            }
            if (size3!= null) {
                product.setSize3(size3);
            }
            if (quantity!= null) {
                product.setQuantity(quantity);
            }
            if (info!= null) {
                product.setDescription(info);
            }
            if (expiration_date!= null) {
                product.setExpiration_date(expiration_date);
            }
            productsDAO.save(product);
        }
        return "index";
    }


    @GetMapping("/searchProduct")
    public String searchEmployee(@RequestParam(required = true) String name,
                                 @RequestParam(required = false) String sortingId,
                                 @RequestParam(required = false) String asc,
                                 Model model) {

        List<Products> products;
        if (sortingId.equals("Имя")) {
            if (Objects.equals(asc, "по убыванию")) {
                products = productsDAO.getProductsSortedByNamesDESC(name);
            } else {
                products = productsDAO.getProductsSortedByNamesASC(name);
            }
        } else if (sortingId.equals("Срок хранения")) {
            if (Objects.equals(asc, "по убыванию")) {
                products = productsDAO.getProductsSortedByExpirationDateDESC(name);
            } else {
                products = productsDAO.getProductsSortedByExpirationDateASC(name);
            }
        } else if (sortingId.equals("Наличие")) {
            if (Objects.equals(asc, "по убыванию")) {
                products = productsDAO.getProductsSortedByEmptySpaceDESC(name);
            } else {
                products = productsDAO.getProductsSortedByEmptySpaceASC(name);
            }
        } else {
            products = productsDAO.getAllProductsByName(name);
        }

        model.addAttribute("products", products);
        model.addAttribute("productsDAO", productsDAO);
        return "products";
    }

    @PostMapping("/removeProduct")
    public String removeProductPage(@RequestParam(name = "productId") Long productId) {
        productsDAO.deleteById(productId);
        return "redirect:/products";
    }
}