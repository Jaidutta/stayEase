package com.learntocode.projects.stayEase.controller;

import com.learntocode.projects.stayEase.dto.RoomDto;
import com.learntocode.projects.stayEase.service.RoomService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hotels/{hotelId}/rooms")
@RequiredArgsConstructor
public class RoomAdminController {
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@PathVariable Long hotelId, @RequestBody RoomDto roomDto) {
        RoomDto roomDtoObj =  roomService.createRoom(hotelId, roomDto);
        return new ResponseEntity<>(roomDtoObj, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRoomsInHotel(@PathVariable Long hotelId) {
        List<RoomDto> roomDtoList = roomService.getAllRoomsInHotel(hotelId);
        return  ResponseEntity.ok(roomDtoList);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long roomId) {
        RoomDto roomDto = roomService.getRoomById(roomId);
        return ResponseEntity.ok(roomDto);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<RoomDto> deleteRoomById(@PathVariable Long roomId) {
        roomService.deleteRoomById(roomId);
        return ResponseEntity.noContent().build();
    }
}
