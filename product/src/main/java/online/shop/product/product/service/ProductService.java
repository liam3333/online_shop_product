package online.shop.product.product.service;

import lombok.RequiredArgsConstructor;
import online.shop.product.product.feign.CartClient;
import online.shop.product.product.feign.InventoryClient;
import online.shop.product.product.model.dto.InventoryClientResponse;
import online.shop.product.product.model.dto.ViewCartResponse;
import online.shop.product.product.model.ProductEntity;
import online.shop.product.product.model.dto.AddCartRequest;
import online.shop.product.product.model.dto.AddCartResponse;
import online.shop.product.product.model.dto.ProductDetailResponse;
import online.shop.product.product.model.dto.ProductListResponse;
import online.shop.product.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final InventoryClient inventoryClient;
    private final CartClient cartClient;

    public List<ProductListResponse> getProductList() {
        List<ProductEntity> products = productRepository.findAll();
        return products.stream()
                .map(this::mapToProductListResponse)
                .collect(Collectors.toList());
    }

    public ProductDetailResponse getProductDetail(String id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToProductDetailResponse(product);
    }

    public AddCartResponse addCart(AddCartRequest request) {
        return cartClient.addCart(request);
    }

    public ViewCartResponse viewCart(Long userId) {
        return cartClient.viewCart(userId);
    }

    private ProductListResponse mapToProductListResponse(ProductEntity product) {
        return ProductListResponse.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(getProductInventoryQuantity(product.getInventoryId()))
                .build();
    }

    private ProductDetailResponse mapToProductDetailResponse(ProductEntity product) {
        return ProductDetailResponse.builder()
                .productId(product.getProductId())
                .inventoryId(product.getInventoryId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(getProductInventoryQuantity(product.getInventoryId()))
                .build();
    }

    private int getProductInventoryQuantity(String inventoryId) {
        try {
            InventoryClientResponse response = inventoryClient.getProductStock(inventoryId);
            return response.getQuantity();
        } catch (Exception e) {
            throw new RuntimeException("Inventory detail not found");
        }
    }
}
