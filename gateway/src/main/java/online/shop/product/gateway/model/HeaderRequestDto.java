package online.shop.product.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HeaderRequestDto {
    private String userId;
    private int roleId;
}
