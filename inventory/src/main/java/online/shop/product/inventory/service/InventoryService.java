package online.shop.product.inventory.service;

import jakarta.transaction.Transactional;
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

    public void initInventory() {
        for (int i = 1; i <= 10; i++) {
            InventoryEntity entity = InventoryEntity.builder()
                    .inventoryId("IVN" + i)
                    .productId("PRD" + i)
                    .stock(100)
                    .build();
            inventoryRepository.save(entity);
        }
    }

    public ProductDetailResponse getProductDetail(String id) {
        InventoryEntity inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found in inventory"));
        return mapToProductDetailResponse(inventory);
    }

    @Transactional
    public Boolean subtractStock(SubstractStockRequest request) {
        InventoryEntity inventory = inventoryRepository.findByProductId(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Inventory not found for product: " + request.getProductId()));

        if (inventory.getStock() < request.getQuantity()) {
            return false;
        }

        inventory.setStock(inventory.getStock() - request.getQuantity());
        inventoryRepository.save(inventory);
        return true;
    }

    private ProductDetailResponse mapToProductDetailResponse(InventoryEntity inventory) {
        return ProductDetailResponse.builder()
                .id(inventory.getProductId())
                .quantity(inventory.getStock())
                .build();
    }
}
