package online.shop.product.product.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;
    private String inventoryId;
    private String name;
    private String description;
    private double price;
}