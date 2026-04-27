package com.learntocode.projects.stayEase.controller;

import com.learntocode.projects.stayEase.dto.HotelDto;
import com.learntocode.projects.stayEase.dto.HotelInfoDto;
import com.learntocode.projects.stayEase.dto.HotelPriceDto;
import com.learntocode.projects.stayEase.dto.HotelSearchRequest;
import com.learntocode.projects.stayEase.service.HotelService;
import com.learntocode.projects.stayEase.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelBrowseController {

    private final InventoryService inventoryService;
    private final HotelService hotelService;
    @GetMapping("/search")
    public ResponseEntity<Page<HotelPriceDto>> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest) {
       var page = inventoryService.searchHotels(hotelSearchRequest);
       return ResponseEntity.ok(page);
    }

    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId) {
        return ResponseEntity.ok(hotelService.getHotelInfo(hotelId));
    }
}
