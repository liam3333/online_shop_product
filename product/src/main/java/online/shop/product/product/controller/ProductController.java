package online.shop.product.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import online.shop.product.product.model.dto.*;
import online.shop.product.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final ObjectMapper objectMapper;

    @GetMapping("/list")
    public List<ProductListResponse> getProductList() {
        return productService.getProductList();
    }

    @GetMapping("/detail/{id}")
    public ProductDetailResponse getProductDetail(@PathVariable String id) {
        return productService.getProductDetail(id);
    }

//    @PostMapping("/cart")
//    public AddCartResponse addToCart(@RequestBody CartRequestDto request,
//                                     @RequestHeader("USER-DETAILS") String header) throws JsonProcessingException {
//        HeaderRequestDto headerRequestDto = objectMapper.readValue(header, HeaderRequestDto.class);
//        return productService.addCart(request, headerRequestDto);
//    }
//
//    @GetMapping("/cart")
//    public ViewCartResponse viewCart(@RequestHeader("USER-DETAILS") String header) throws JsonProcessingException {
//        HeaderRequestDto headerRequestDto = objectMapper.readValue(header, HeaderRequestDto.class);
//        return productService.getCarts(headerRequestDto);
//    }
}