package online.shop.product.user.controller;

import online.shop.product.user.dto.request.LoginRequestDto;
import online.shop.product.user.dto.response.UserResponseDto;
import online.shop.product.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("init-user")
//    public void initUser() {
//        userService.initUser();
//    }

    @PostMapping("/login")
    public UserResponseDto login(@RequestBody LoginRequestDto request) {
        return userService.login(request);
    }
}
