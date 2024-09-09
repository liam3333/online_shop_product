package online.shop.product.product.model.dto;

import lombok.Data;

@Data
public class InventoryClientResponse {
    private String id;
    private int quantity;
}