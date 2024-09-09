package online.shop.product.product.controller;

import lombok.RequiredArgsConstructor;
import online.shop.product.product.model.dto.ViewCartResponse;
import online.shop.product.product.model.dto.AddCartRequest;
import online.shop.product.product.model.dto.AddCartResponse;
import online.shop.product.product.model.dto.ProductDetailResponse;
import online.shop.product.product.model.dto.ProductListResponse;
import online.shop.product.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;

    @GetMapping("/list")
    public List<ProductListResponse> getProductList() {
        return productService.getProductList();
    }

    @GetMapping("/detail/{id}")
    public ProductDetailResponse getProductDetail(@PathVariable String id) {
        return productService.getProductDetail(id);
    }

    @PostMapping("/cart")
    public AddCartResponse addToCart(@RequestBody AddCartRequest request) {
        return productService.addCart(request);
    }

    @GetMapping("/cart/{userId}")
    public ViewCartResponse viewCart(@PathVariable Long userId) {
        return productService.viewCart(userId);
    }
}