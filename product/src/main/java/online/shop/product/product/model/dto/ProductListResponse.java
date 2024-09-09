package online.shop.product.product.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductListResponse {
    private String productId;
    private String name;
    private String description;
    private int quantity;
    private double price;
}