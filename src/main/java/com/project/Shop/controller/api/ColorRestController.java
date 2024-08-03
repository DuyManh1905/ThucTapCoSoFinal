package com.project.Shop.controller.api;

import com.project.Shop.dto.Color.ColorDto;
import com.project.Shop.entity.Color;
import com.project.Shop.exception.NotFoundException;
import com.project.Shop.service.ColorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ColorRestController {
    private final ColorService colorService;

    public ColorRestController(ColorService colorService) {
        this.colorService = colorService;
    }

    @PostMapping("/api/color")
    public ColorDto createColorApi(@RequestBody ColorDto colorDto) {
        return colorService.createColorApi(colorDto);
    }

    @GetMapping("/colors/{productId}/product")
    public List<Color> getColorsByProductId(@PathVariable Long productId) throws NotFoundException {
        return colorService.getColorByProductId(productId);
    }

    @GetMapping("/colors/{productId}/product/{sizeId}/size")
    public List<Color> getColorsByProductIdAndSizeId(@PathVariable Long productId, @PathVariable Long sizeId) throws NotFoundException {
        return colorService.getColorsByProductIdAndSizeId(productId, sizeId);
    }
}