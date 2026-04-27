package com.learntocode.projects.stayEase.dto;

import com.learntocode.projects.stayEase.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelPriceDto {
    private Hotel hotel;
    private Double price;
}
