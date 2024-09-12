package online.shop.product.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class LoginRequestDto {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
