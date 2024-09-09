package online.shop.product.product.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ViewCartResponse {
    private List<CartItemResponse> cartItems;
    private int total;
}