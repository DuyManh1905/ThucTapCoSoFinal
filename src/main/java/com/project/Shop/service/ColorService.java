package com.project.Shop.service;

import com.project.Shop.dto.Color.ColorDto;
import com.project.Shop.entity.Color;
import com.project.Shop.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ColorService {

    Color updateColor(Color color);

    Color createColor(Color color);

    void delete(Long id);

    List<Color> findAll();

    Optional<Color> findById(Long id);

    boolean existsById(Long id);

    Page<Color> findAll(Integer page, Integer limit);

    Page<Color> findAll(Pageable pageable);

    List<Color> getColorByProductId(Long productId) throws NotFoundException;
    List<Color> getColorsByProductIdAndSizeId(Long productId, Long sizeId) throws NotFoundException;

    ColorDto createColorApi(ColorDto colorDto);
}
