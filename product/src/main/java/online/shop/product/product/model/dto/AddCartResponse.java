package online.shop.product.product.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddCartResponse {
    private Long cartId;
    private String message;
}