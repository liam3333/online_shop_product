package online.shop.product.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HeaderRequestDto {
    private String userId;
    private int roleId;
}