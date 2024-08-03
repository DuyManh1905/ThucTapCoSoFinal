package com.project.Shop.controller.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.Shop.entity.Bill;
import com.project.Shop.service.BillService;

@Controller
public class OrderStatusController {
    private final BillService billService;

    public OrderStatusController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/cart-status")
    public String viewCartStatus(Model model,
                                 @RequestParam(required = false) String status,
                                 @PageableDefault(size = 5, sort = "createDate", direction = Sort.Direction.DESC)  Pageable pageable) {
    	
    	
        Page<Bill> billPage = null;
        //neu null (lay tat ca)
        if(status == null || status.trim().isEmpty()) {
            billPage = billService.getBillByAccount(pageable);
        }else {
            billPage = billService.getBillByStatus(status, pageable);
            model.addAttribute("status", status);
        }

        model.addAttribute("bills", billPage);
        return "user/cart-status";
    }

    @PostMapping("/cancel-bill/{id}")
    public String cancelBill(@PathVariable Long id) {
        billService.updateStatus("HUY", id);
        return "redirect:/cart-status";
    }

}
