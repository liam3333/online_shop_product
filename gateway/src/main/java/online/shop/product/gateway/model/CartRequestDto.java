package online.shop.product.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartRequestDto {
    private String productId;
    private int quantity;
}
