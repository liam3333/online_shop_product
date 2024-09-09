package online.shop.product.product.feign;

import online.shop.product.product.model.dto.AddCartRequest;
import online.shop.product.product.model.dto.AddCartResponse;
import online.shop.product.product.model.dto.ViewCartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service", url = "${inventory.service.url}")
public interface CartClient {

    @PostMapping("/api/inventory/cart")
    AddCartResponse addCart(@RequestBody AddCartRequest request);

    @GetMapping("/api/inventory/cart/{userId}")
    ViewCartResponse viewCart(@PathVariable("userId") Long userId);
}