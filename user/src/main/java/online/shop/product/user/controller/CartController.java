package online.shop.product.user.controller;

import lombok.RequiredArgsConstructor;
import online.shop.product.user.dto.request.CartRequestDto;
import online.shop.product.user.dto.request.HeaderRequestDto;
import online.shop.product.user.model.entity.CartEntity;
import online.shop.product.user.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping("/add-cart")
    public CartEntity addCart(@RequestBody CartRequestDto request,
                              @RequestHeader("USER-DETAILS") HeaderRequestDto header) {
        return cartService.addCart(request, header);
    }

    @GetMapping("/get-cart")
    public List<CartEntity> getCarts(@RequestHeader("USER-DETAILS") HeaderRequestDto header) {
        return cartService.getCarts(header);
    }
}
