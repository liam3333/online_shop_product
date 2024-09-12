package online.shop.product.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartRequestDto {
    private String productId;
    private int quantity;
}
