package online.shop.product.product.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDetailResponse {
    private String productId;
    private String inventoryId;
    private String name;
    private String description;
    private double price;
    private int quantity;
}
