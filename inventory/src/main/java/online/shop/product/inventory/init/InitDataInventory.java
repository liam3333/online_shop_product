package online.shop.product.inventory.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import online.shop.product.inventory.service.InventoryService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDataInventory {
    private final InventoryService inventoryService;

    @PostConstruct
    public void initialize(){
        inventoryService.initInventory();
    }
}
