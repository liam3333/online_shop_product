package online.shop.product.inventory.service;

import lombok.RequiredArgsConstructor;
import online.shop.product.inventory.model.InventoryEntity;
import online.shop.product.inventory.model.dto.*;
import online.shop.product.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public ProductDetailResponse getProductDetail(String id) {
        InventoryEntity inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found in inventory"));
        return mapToProductDetailResponse(inventory);
    }

    private ProductDetailResponse mapToProductDetailResponse(InventoryEntity inventory) {
        return ProductDetailResponse.builder()
                .id(inventory.getProductId())
                .quantity(inventory.getStock())
                .build();
    }
}
