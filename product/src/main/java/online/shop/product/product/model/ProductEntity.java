package online.shop.product.product.model;


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
@Table(name = "PRODUCT")
public class ProductEntity {
    @Id
    private String productId;
    private String inventoryId;
    private String name;
    private String description;
    private double price;
}