package com.learntocode.projects.stayEase.service;

import com.learntocode.projects.stayEase.dto.HotelDto;
import com.learntocode.projects.stayEase.entity.Hotel;

public interface HotelService {

    HotelDto createHotel(HotelDto hotelDto);

    HotelDto getHotelById(Long id);

    HotelDto updateHotelById(Long id, HotelDto hotelDto);

    void deleteHotelById(Long id);

    void activateHotelById(Long id);
}
