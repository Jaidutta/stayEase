package com.learntocode.projects.stayEase.service;

import com.learntocode.projects.stayEase.dto.RoomDto;
import com.learntocode.projects.stayEase.entity.Hotel;
import com.learntocode.projects.stayEase.entity.Room;
import com.learntocode.projects.stayEase.exception.ResourceNotFoundException;
import com.learntocode.projects.stayEase.repository.HotelRepository;
import com.learntocode.projects.stayEase.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;


    @Override
    @Transactional
    public RoomDto createRoom(Long hotelId, RoomDto roomDto) {
        log.info("Creating a room with hotelId: {} ", hotelId);

        
        Room room = modelMapper.map(roomDto, Room.class);

        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + hotelId));

        room.setHotel(hotel);
        room = roomRepository.save(room);

        // TODO: create future inventories for this room(Room type) as soon as the hotel is active
        if(hotel.getActive()) {
            inventoryService.initializeRoomForAYear(room);
        }
        // inventory createdfor the room

        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("Getting all rooms in a hotel with hotelId: {} ", hotelId);
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + hotelId));

        List<Room> rooms = hotel.getRooms();
        List <RoomDto> rooomDtoList = rooms.stream().map(room -> modelMapper.map(room, RoomDto.class))
                .collect(Collectors.toList());
        return rooomDtoList;
    }

    @Override
    public RoomDto getRoomById(Long id) {
        log.info("Getting room with id: {}", id);
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + id));
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    @Transactional
    public void deleteRoomById(Long id) {
        log.info("Deleting room with id: {}", id);

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + id));

       // TODO: delete future inventories for this room(Room type)
        inventoryService.deleteAllInventories(room);

        roomRepository.deleteById(id);
        log.info("Room deleted successfully with id: {}", id);

    }

}
