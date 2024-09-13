package online.shop.product.product.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
//import online.shop.product.product.feign.CartClient;
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
//    private final CartClient cartClient;
    private final ObjectMapper objectMapper;

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

//    public AddCartResponse addCart(CartRequestDto request, HeaderRequestDto header) throws JsonProcessingException {
//        String headerString = objectMapper.writeValueAsString(header);
//        return cartClient.addCart(request, headerString);
//    }
//
//    public ViewCartResponse getCarts(HeaderRequestDto header) throws JsonProcessingException {
//        String headerString = objectMapper.writeValueAsString(header);
//        return cartClient.getCarts(headerString);
//    }

    private ProductListResponse mapToProductListResponse(ProductEntity product) {
        return ProductListResponse.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(getProductInventoryQuantity(product.getProductId()))
                .build();
    }

    private ProductDetailResponse mapToProductDetailResponse(ProductEntity product) {
        return ProductDetailResponse.builder()
                .productId(product.getProductId())
                .inventoryId(product.getInventoryId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(getProductInventoryQuantity(product.getProductId()))
                .build();
    }

    private int getProductInventoryQuantity(String productId) {
        try {
            InventoryClientResponse response = inventoryClient.getProductStock(productId);
            return response.getQuantity();
        } catch (Exception e) {
            throw new RuntimeException("Inventory detail not found");
        }
    }
}
