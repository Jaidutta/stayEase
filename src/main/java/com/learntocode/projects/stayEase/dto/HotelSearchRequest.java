package com.learntocode.projects.stayEase.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class HotelSearchRequest {
    private String city;
    private LocalDate startDate; // checkin data
    private LocalDate endDate;   // checkout date
    private Integer roomsCount;

    private Integer page = 0;
    private Integer size = 10;
}
