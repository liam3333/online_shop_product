package online.shop.product.inventory.model;

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
    private String userId;
    private String productId;
    private String productName;
    private int quantity;
}