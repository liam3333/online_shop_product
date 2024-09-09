package online.shop.product.inventory.model.dto;

import lombok.Data;

@Data
public class AddCartResponse {
    private String cartId;
    private String message;
}