package online.shop.product.user.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubstractStockRequest {
    private String productId;
    private int quantity;
}
