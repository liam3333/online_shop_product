package online.shop.product.user.init;

import jakarta.annotation.PostConstruct;
import online.shop.product.user.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class InitDataUser {
    private final UserService userService;

    public InitDataUser(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initialize(){
        userService.initUser();
    }
}
