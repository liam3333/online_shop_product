package online.shop.product.user.service;

import online.shop.product.user.dto.request.CartRequestDto;
import online.shop.product.user.dto.request.HeaderRequestDto;
import online.shop.product.user.model.entity.CartEntity;
import online.shop.product.user.model.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CartEntity addCart(CartRequestDto cart, HeaderRequestDto header) {
        Optional<CartEntity> cartEntity = cartRepository.findByProductIdAndUserId(cart.getProductId(), header.getUserId());
        CartEntity cartRes;
        if(cartEntity.isEmpty()) {
            cartRes = CartEntity.builder()
                    .productId(cart.getProductId())
                    .userId(header.getUserId())
                    .quantity(cart.getQuantity())
                    .build();
        }
        else {
            cartRes = cartEntity.get();
            cartRes.setQuantity(cartRes.getQuantity()+cart.getQuantity());
        }

        return cartRepository.save(cartRes);
    }

    public List<CartEntity> getCarts(HeaderRequestDto header) {
        return cartRepository.findAllByUserId(header.getUserId());
    }
}
