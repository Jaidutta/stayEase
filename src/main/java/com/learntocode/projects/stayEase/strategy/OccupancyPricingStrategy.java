package com.learntocode.projects.stayEase.strategy;

import com.learntocode.projects.stayEase.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@RequiredArgsConstructor
public class OccupancyPricingStrategy implements PricingStrategy{

    private final PricingStrategy wrapped;
    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = wrapped.calculatePrice(inventory);
        double occupancyRate = (double) inventory.getBookedCount() / inventory.getTotalCount();
        if(occupancyRate > 0.8) {
            // occupancy more than 80%
            price = price.multiply(BigDecimal.valueOf(1.2));
        }
        return price;
    }
}
