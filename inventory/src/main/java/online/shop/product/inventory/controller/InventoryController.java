package online.shop.product.inventory.controller;

import lombok.RequiredArgsConstructor;
import online.shop.product.inventory.model.dto.AddCartRequest;
import online.shop.product.inventory.model.dto.AddCartResponse;
import online.shop.product.inventory.model.dto.ProductDetailResponse;
import online.shop.product.inventory.model.dto.ViewCartResponse;
import online.shop.product.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {
    private InventoryService inventoryService;

    @GetMapping("/stock/{id}")
    public ProductDetailResponse getProductDetail(@PathVariable String id) {
        return inventoryService.getProductDetail(id);
    }

    @PostMapping("/cart")
    public AddCartResponse addCart(@RequestBody AddCartRequest request) {
        return inventoryService.addCart(request);
    }

    @GetMapping("/cart-list/{userId}")
    public ViewCartResponse viewCart(@PathVariable String userId) {
        return inventoryService.viewCart(userId);
    }
}
