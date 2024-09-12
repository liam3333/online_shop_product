package online.shop.product.product.service;

import lombok.RequiredArgsConstructor;
import online.shop.product.product.feign.CartClient;
import online.shop.product.product.feign.InventoryClient;
import online.shop.product.product.model.dto.*;
import online.shop.product.product.model.ProductEntity;
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

    public void initProduct() {
        for (int i = 1; i <= 10; i++) {
            ProductEntity entity = ProductEntity.builder()
                    .productId("PRD" + i)
                    .inventoryId("IVN" + i)
                    .name("Product Name " + i)
                    .description("Product Description " + i)
                    .price(10000 * i)
                    .build();
            productRepository.save(entity);
        }
    }

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

    public AddCartResponse addCart(CartRequestDto request, HeaderRequestDto header) {
        return cartClient.addCart(request, header);
    }

    public ViewCartResponse getCarts(HeaderRequestDto header) {
        return cartClient.getCarts(header);
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
