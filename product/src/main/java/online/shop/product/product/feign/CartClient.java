//package online.shop.product.product.feign;
//
//import online.shop.product.product.model.dto.CartRequestDto;
//import online.shop.product.product.model.dto.AddCartResponse;
//import online.shop.product.product.model.dto.HeaderRequestDto;
//import online.shop.product.product.model.dto.ViewCartResponse;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.*;
//
//@FeignClient(name = "user-service", url = "${user.service.url}")
//public interface CartClient {
//
//    @PostMapping("/api/cart/add-cart")
//    AddCartResponse addCart(@RequestBody CartRequestDto request,
//                            @RequestHeader("USER-DETAILS") String header);
//
//    @GetMapping("/api/cart/get-cart")
//    ViewCartResponse getCarts(@RequestHeader("USER-DETAILS") String header);
//}