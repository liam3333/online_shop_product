package online.shop.product.user.model.repository;

import online.shop.product.user.model.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, String> {
    Optional<CartEntity> findByProductIdAndUserId(String productId, String userId);
    List<CartEntity> findAllByUserId(String userId);
}
