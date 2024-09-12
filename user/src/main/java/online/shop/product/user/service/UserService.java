package online.shop.product.user.service;

import online.shop.product.user.dto.request.LoginRequestDto;
import online.shop.product.user.dto.request.RegisRequestDto;
import online.shop.product.user.dto.response.UserResponseDto;
import online.shop.product.user.exception.NotFoundException;
import online.shop.product.user.model.entity.UserEntity;
import online.shop.product.user.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto login(LoginRequestDto request) {
        Optional<UserEntity> user = userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        if(user.isEmpty()) {
            throw new NotFoundException();
        }
        return UserResponseDto.builder()
                .userId(user.get().getUserId())
                .name(user.get().getName())
                .roleId(user.get().getRoleId())
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
