package online.shop.product.product.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemResponse {
    private String productName;
    private int quantity;
}