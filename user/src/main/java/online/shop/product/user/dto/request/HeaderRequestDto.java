package online.shop.product.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HeaderRequestDto {
    private String userId;
    private int roleId;
}
