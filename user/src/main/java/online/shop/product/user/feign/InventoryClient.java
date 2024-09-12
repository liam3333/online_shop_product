package online.shop.product.user.feign;

import online.shop.product.user.dto.response.InventoryClientResponse;
import online.shop.product.user.dto.response.SubstractStockRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service", url = "${inventory.service.url}")
public interface InventoryClient {

    @GetMapping("/api/inventory/stock/{inventoryId}")
    InventoryClientResponse getProductStock(@PathVariable("inventoryId") String id);

    @PostMapping("/api/inventory/substract")
    Boolean subtractStock(@RequestBody SubstractStockRequest request);
}