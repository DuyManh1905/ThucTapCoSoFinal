package com.project.Shop.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Shop.entity.Image;
import com.project.Shop.entity.Product;
import com.project.Shop.repository.ImageRepository;
import com.project.Shop.repository.ProductRepository;
import com.project.Shop.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Image> getAllImagesByProductId(Long productId)  {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()) {
            return imageRepository.findAllByProduct(product.get());
        }
        return null;
    }

    @Override
    public void removeImageByIds(List<Long> ids) {
        imageRepository.deleteAllById(ids);
    }
}
