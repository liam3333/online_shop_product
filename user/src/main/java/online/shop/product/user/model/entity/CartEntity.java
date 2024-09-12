package online.shop.product.user.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String cartId;
    private String productId;
    private int quantity;
    private String userId;
}
