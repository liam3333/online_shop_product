package online.shop.product.product.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddCartRequest {
    private String userId;
    private String productId;
    private int quantity;
}