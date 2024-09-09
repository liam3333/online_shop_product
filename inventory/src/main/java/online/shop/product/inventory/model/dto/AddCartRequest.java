package online.shop.product.inventory.model.dto;

import lombok.Data;

@Data
public class AddCartRequest {
    private String userId;
    private String productId;
    private int quantity;
}