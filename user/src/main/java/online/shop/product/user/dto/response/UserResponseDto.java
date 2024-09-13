package online.shop.product.user.dto.response;

import io.jsonwebtoken.JwtBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserResponseDto {
    private String token;
}
