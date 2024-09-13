package online.shop.product.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;

    @PostMapping("/add-cart")
    public CartEntity addCart(@RequestBody CartRequestDto request,
                              @RequestHeader("USER-DETAILS") String header) throws JsonProcessingException {
        HeaderRequestDto headerRequestDto = objectMapper.readValue(header, HeaderRequestDto.class);
        return cartService.addCart(request, headerRequestDto);
    }

    @GetMapping("/get-cart")
    public List<CartEntity> getCarts(@RequestHeader("USER-DETAILS") String header) throws JsonProcessingException {
        HeaderRequestDto headerRequestDto = objectMapper.readValue(header, HeaderRequestDto.class);
        return cartService.getCarts(headerRequestDto);
    }
}
