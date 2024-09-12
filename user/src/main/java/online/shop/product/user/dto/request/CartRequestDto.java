package online.shop.product.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartRequestDto {
    private String productId;
    private int quantity;
}
