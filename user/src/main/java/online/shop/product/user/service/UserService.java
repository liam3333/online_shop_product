package online.shop.product.user.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import online.shop.product.user.dto.request.LoginRequestDto;
import online.shop.product.user.dto.response.UserResponseDto;
import online.shop.product.user.exception.NotFoundException;
import online.shop.product.user.model.entity.UserEntity;
import online.shop.product.user.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Value("${key.jwt.secret}")
    private String key;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void initUser() {
        for (int i = 1; i <= 10; i++) {
            UserEntity entity = UserEntity.builder()
                    .name("Name " + i)
                    .email("user_" + i + "mail.com")
                    .username("@username" + i)
                    .password("123456")
                    .roleId(i % 2)
                    .build();
            userRepository.save(entity);
        }
    }

    public UserResponseDto login(LoginRequestDto request) {
        Optional<UserEntity> user = userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        if(user.isEmpty()) {
            throw new NotFoundException();
        }
        return UserResponseDto.builder()
                .token(Jwts.builder()
                        .claim("userId",user.get().getUserId())
                        .claim("roleId",user.get().getRoleId())
                        .setSubject(user.get().getName())
                        .signWith(SignatureAlgorithm.ES256,key).toString())
                .build();
    }

//    public UserResponseDto regis(RegisRequestDto request) {
//        UserEntity entity = UserEntity.builder()
//                .email(request.getEmail())
//                .name(request.getName())
//                .password(request.getPassword())
//                .username(request.getUsername())
//                .roleId(request.getRoleId())
//                .build();
//        entity = userRepository.save(entity);
//        return UserResponseDto.builder()
//                .userId(entity.getUserId())
//                .name(entity.getName())
//                .roleId(entity.getRoleId())
//                .build();
//    }

}
