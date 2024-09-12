package online.shop.product.inventory.controller;

import lombok.RequiredArgsConstructor;
import online.shop.product.inventory.model.dto.ProductDetailResponse;
import online.shop.product.inventory.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/stock/{id}")
    public ProductDetailResponse getProductDetail(@PathVariable String id) {
        return inventoryService.getProductDetail(id);
    }
}
