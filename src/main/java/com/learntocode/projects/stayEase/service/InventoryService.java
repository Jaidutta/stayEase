package com.learntocode.projects.stayEase.service;


import com.learntocode.projects.stayEase.dto.HotelPriceDto;
import com.learntocode.projects.stayEase.dto.HotelSearchRequest;
import com.learntocode.projects.stayEase.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {
    void initializeRoomForAYear(Room room);
    void deleteAllInventories(Room room);

    Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest);
}
