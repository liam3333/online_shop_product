package online.shop.product.inventory.service;

import lombok.RequiredArgsConstructor;
import online.shop.product.inventory.model.CartEntity;
import online.shop.product.inventory.model.InventoryEntity;
import online.shop.product.inventory.model.dto.*;
import online.shop.product.inventory.repository.CartRepository;
import online.shop.product.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final CartRepository cartRepository;

    public ProductDetailResponse getProductDetail(String id) {
        InventoryEntity inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found in inventory"));
        return mapToProductDetailResponse(inventory);
    }

    public AddCartResponse addCart(AddCartRequest request) {
        InventoryEntity inventory = inventoryRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found in inventory"));

        if (inventory.getStock() < request.getQuantity()) {
            throw new RuntimeException("Insufficient quantity in inventory");
        }

        CartEntity cart = CartEntity.builder()
                .userId(request.getUserId())
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .build();

        cartRepository.save(cart);

        inventory.setStock(inventory.getStock() - request.getQuantity());
        inventoryRepository.save(inventory);

        AddCartResponse response = new AddCartResponse();
        response.setCartId(cart.getCartId());
        response.setMessage("Product added to cart successfully");
        return response;
    }

    private ProductDetailResponse mapToProductDetailResponse(InventoryEntity inventory) {
        return ProductDetailResponse.builder()
                .id(inventory.getProductId())
                .quantity(inventory.getStock())
                .build();
    }

    public ViewCartResponse viewCart(String userId) {
        List<CartEntity> cartItems = cartRepository.findByUserId(userId);

        List<CartItemResponse> cartItemResponses = cartItems.stream()
                .map(this::mapToCartItemResponse)
                .collect(Collectors.toList());

        return ViewCartResponse.builder()
                .cartItems(cartItemResponses)
                .total(cartItemResponses.size())
                .build();
    }

    private CartItemResponse mapToCartItemResponse(CartEntity cart) {
        return CartItemResponse.builder()
                .productName(cart.getProductName())
                .quantity(cart.getQuantity())
                .build();
    }
}
