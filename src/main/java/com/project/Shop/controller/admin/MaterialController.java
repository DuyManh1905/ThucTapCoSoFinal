package com.project.Shop.controller.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Shop.entity.Material;
import com.project.Shop.exception.NotFoundException;
import com.project.Shop.service.MaterialService;

@Controller
@RequestMapping("/admin/")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping("/material-all")
    public String getAllMaterial(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "sort", defaultValue = "id,asc") String sortField) {
        int pageSize = 10; // Number of items per page
        String[] sortParams = sortField.split(",");
        String sortFieldName = sortParams[0];
        Sort.Direction sortDirection = Sort.Direction.ASC;

        if (sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc")) {
            sortDirection = Sort.Direction.DESC;
        }

        Sort sort = Sort.by(sortDirection, sortFieldName);

        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Page<Material> materialPage = materialService.getAllMaterial(pageable);

        model.addAttribute("sortField", sortFieldName);
        model.addAttribute("sortDirection", sortDirection);

        model.addAttribute("items", materialPage);

        return "admin/material";
    }

    @GetMapping("/material-create")
    public String viewAddMaterial(Model model){
        Material material = new Material();
        model.addAttribute("action", "/admin/material-save");
        model.addAttribute("Material", material);
        return "admin/material-create";
    }


    @PostMapping("/material-save")
    public String addMaterial(Model model, @Validated @ModelAttribute("Material") Material material, RedirectAttributes redirectAttributes) {
        try {
            materialService.createMaterial(material);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm chất liệu mới thành công");

        }
        catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/material-create";
        }
        return "redirect:/admin/material-all";
    }

    @PostMapping("/material-update/{id}")
    public String update(@PathVariable("id") Long id,
                         @Validated @ModelAttribute("Material") Material material, RedirectAttributes redirectAttributes) {

            try {
                Material material1 = materialService.updateMaterial(material);
                redirectAttributes.addFlashAttribute("successMessage", "Chất liệu " + material1.getCode()  + " đã được cập nhật thành công");
            }catch (NotFoundException e) {
                return "404";
            }
            catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/admin/material-detail/" + id;
            }
        return "redirect:/admin/material-all";
    }

    @GetMapping("/material-detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Optional<Material> optional = materialService.findById(id);
        if (optional.isPresent()) {
            Material material = optional.get();
            model.addAttribute("Material", material);
            model.addAttribute("action", "/admin/material-update/" + material.getId());
            return "admin/material-create";
        } else {
            return "404";
        }
    }

    @GetMapping("/material-delete/{id}")
    public String delete(@PathVariable("id") Long id, ModelMap modelMap){
        materialService.delete(id);
        return "redirect:/admin/material-all";
    }
}
