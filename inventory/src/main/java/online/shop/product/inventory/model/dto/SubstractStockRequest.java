package online.shop.product.inventory.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubstractStockRequest {
    private String productId;
    private int quantity;
}
