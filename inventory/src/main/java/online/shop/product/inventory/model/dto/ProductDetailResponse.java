package online.shop.product.inventory.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDetailResponse {
    private String id;
    private int quantity;
}