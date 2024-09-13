package online.shop.product.inventory.controller;

import lombok.RequiredArgsConstructor;
import online.shop.product.inventory.model.InventoryEntity;
import online.shop.product.inventory.model.dto.ProductDetailResponse;
import online.shop.product.inventory.model.dto.SubstractStockRequest;
import online.shop.product.inventory.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/init-inventory")
    public void init() {
        inventoryService.initInventory();
    }

    @GetMapping("/stock/{productId}")
    public ProductDetailResponse getProductDetail(@PathVariable("productId") String id) {
        return inventoryService.getProductDetail(id);
    }

    @PostMapping("/substract")
    public Boolean subtractStock(@RequestBody SubstractStockRequest request) {
        return inventoryService.subtractStock(request);
    }
}
