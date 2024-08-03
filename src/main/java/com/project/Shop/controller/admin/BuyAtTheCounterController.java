package com.project.Shop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.Shop.entity.Product;
import com.project.Shop.service.ProductService;

@Controller
public class BuyAtTheCounterController {

    @Autowired
    ProductService productService;

    @GetMapping("/admin/pos")
    public String getIndex(Model model) {
        Pageable pageable1 = PageRequest.of(0, 2);
        Page<Product> productPage = productService.getAllProduct(pageable1);
        model.addAttribute("products", productPage);
        return "admin/BuyAtTheCounter";
    }

}
