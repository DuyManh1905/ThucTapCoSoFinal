package com.project.Shop.controller.admin;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.Shop.entity.Color;
import com.project.Shop.entity.Product;
import com.project.Shop.entity.Size;
import com.project.Shop.service.ColorService;
import com.project.Shop.service.ProductService;
import com.project.Shop.service.SizeService;

@Controller
public class ProductDetailController {



    @Autowired
    private ProductService productService;

    @Autowired
    private SizeService sizeService;
    @Autowired
    private ColorService colorService;



    @GetMapping("/admin/chi-tiet-san-pham/{code}")
    public String getProductDetailPage(@PathVariable String code, Model model) {
        Product product = productService.getProductByCode(code);
        if(product != null) {
            model.addAttribute("product", product);
            model.addAttribute("productDetails", product.getProductDetails());
            return "admin/product-detail";
        }

        return "error/404";
    }

    @ModelAttribute("listSize")
    public List<Size> getSize() {
        return sizeService.getAll();
    }

    @ModelAttribute("listColor")
    public List<Color> getColor() {
        return colorService.findAll();
    }
}
