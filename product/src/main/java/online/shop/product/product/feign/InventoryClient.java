package online.shop.product.product.feign;

import online.shop.product.product.model.dto.InventoryClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service", url = "${inventory.service.url}")
public interface InventoryClient {

    @GetMapping("/api/inventory/stock/{inventoryId}")
    InventoryClientResponse getProductStock(@PathVariable("inventoryId") String id);
}