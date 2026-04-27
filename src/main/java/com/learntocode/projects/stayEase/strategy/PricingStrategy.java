package com.learntocode.projects.stayEase.strategy;

import com.learntocode.projects.stayEase.entity.Inventory;
import java.math.BigDecimal;


public interface PricingStrategy {

    BigDecimal calculatePrice(Inventory inventory);
}
