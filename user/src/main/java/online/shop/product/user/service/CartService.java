package online.shop.product.user.service;

import lombok.RequiredArgsConstructor;
import online.shop.product.user.dto.request.CartRequestDto;
import online.shop.product.user.dto.request.HeaderRequestDto;
import online.shop.product.user.dto.response.SubstractStockRequest;
import online.shop.product.user.feign.InventoryClient;
import online.shop.product.user.model.entity.CartEntity;
import online.shop.product.user.model.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final InventoryClient inventoryClient;

    public CartEntity addCart(CartRequestDto cart, HeaderRequestDto header) {
        Optional<CartEntity> cartEntity = cartRepository.findByProductIdAndUserId(cart.getProductId(), header.getUserId());
        CartEntity cartRes;

        if (!userService.isUserExist(header.getUserId())) {
            throw new RuntimeException("User doesn't exist");
        }

        Boolean isStockReady = inventoryClient.subtractStock(SubstractStockRequest.builder()
                .productId(cart.getProductId())
                .quantity(cart.getQuantity())
                .build());

        if (cartEntity.isEmpty()) {
            if (isStockReady) {
                cartRes = CartEntity.builder()
                        .productId(cart.getProductId())
                        .userId(header.getUserId())
                        .quantity(cart.getQuantity())
                        .build();
            } else {
                throw new RuntimeException("Product Stock Not Enough!");
            }
        } else {
            if (isStockReady) {
                cartRes = cartEntity.get();
                cartRes.setQuantity(cartRes.getQuantity()+cart.getQuantity());
            } else {
                throw new RuntimeException("Product Stock Not Enough!");
            }
        }

        cartRepository.save(cartRes);
        return cartRes;
    }

    public List<CartEntity> getCarts(HeaderRequestDto header) {
        List<CartEntity> cartList = cartRepository.findAllByUserId(header.getUserId());
        if (cartList.isEmpty()) {
            throw new RuntimeException("No cart data");
        } else {
            return cartList;
        }
    }
}
