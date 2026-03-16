package com.learntocode.projects.stayEase.service;

import com.learntocode.projects.stayEase.dto.RoomDto;

import java.util.List;

public interface RoomService {
    RoomDto createRoom(Long hotelId,RoomDto roomDto);

    List<RoomDto> getAllRoomsInHotel(Long hotelId);

    RoomDto getRoomById(Long id);

    void deleteRoomById(Long id);

}
