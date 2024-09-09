package online.shop.product.inventory.repository;

import online.shop.product.inventory.model.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, String> {
    List<CartEntity> findByUserId(String userId);
}
