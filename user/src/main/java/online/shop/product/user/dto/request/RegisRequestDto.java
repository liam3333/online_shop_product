package online.shop.product.user.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class RegisRequestDto {
    private String name;
    private String email;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private int roleId;
}
