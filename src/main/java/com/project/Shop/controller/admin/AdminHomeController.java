package com.project.Shop.controller.admin;

import com.project.Shop.dto.Bill.BillDtoInterface;
import com.project.Shop.dto.Product.ProductDto;
import com.project.Shop.repository.BillRepository;
import com.project.Shop.service.AccountService;
import com.project.Shop.service.BillService;
import com.project.Shop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class  AdminHomeController {
    private final BillService billService;
    private final ProductService productService;

    private final BillRepository billRepository;


    public AdminHomeController(BillService billService, ProductService productService, BillRepository billRepository, AccountService accountService) {
        this.billService = billService;
        this.productService = productService;
        this.billRepository = billRepository;
    }

    @GetMapping("/admin")
    public String viewAdminHome(Model model) {
        Page<BillDtoInterface> billDtos = billService.findAll(Pageable.ofSize(10));
        Page<ProductDto> productDtos = productService.getAllProductApi(Pageable.ofSize(10));

        model.addAttribute("billList", billRepository.findAll(PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "createDate"))));
        model.addAttribute("totalBillQuantity", billDtos.getTotalElements());
        model.addAttribute("totalProduct", productDtos.getTotalElements());
        model.addAttribute("revenue", billRepository.calculateTotalRevenue());
        model.addAttribute("totalBillWaiting", billRepository.getTotalBillStatusWaiting());
        return "admin/index";
    }
}
