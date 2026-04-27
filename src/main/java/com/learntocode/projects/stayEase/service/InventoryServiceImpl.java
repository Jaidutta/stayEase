package com.learntocode.projects.stayEase.service;

import com.learntocode.projects.stayEase.dto.HotelDto;
import com.learntocode.projects.stayEase.dto.HotelPriceDto;
import com.learntocode.projects.stayEase.dto.HotelSearchRequest;
import com.learntocode.projects.stayEase.entity.Hotel;
import com.learntocode.projects.stayEase.entity.Inventory;
import com.learntocode.projects.stayEase.entity.Room;
import com.learntocode.projects.stayEase.repository.HotelMinPriceRepository;
import com.learntocode.projects.stayEase.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService{

    private final ModelMapper modelMapper;
    private final InventoryRepository inventoryRepository;
    private final HotelMinPriceRepository hotelMinPriceRepository;
    @Override
    public void initializeRoomForAYear(Room room) {
        LocalDate today = LocalDate.now();
        LocalDate endDate  = today.plusYears(1);
        for(; !today.isAfter(endDate); today = today.plusDays(1)) {
            Inventory inventory = Inventory.builder()
                    .room(room)
                    .hotel(room.getHotel())
                    .bookedCount(0)
                    .reservedCount(0)
                    .city(room.getHotel().getCity())
                    .date(today)
                    .price(room.getBasePrice())
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .build();
            inventoryRepository.save(inventory);
        }
    }

    @Override
    public void deleteAllInventories(Room room) {
        inventoryRepository.deleteByRoom(room);
    }

    @Override
    public Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest) {
        log.info("Searching hotels for {} city, from {} to {}", hotelSearchRequest.getCity(), hotelSearchRequest.getStartDate(), hotelSearchRequest.getEndDate());
        Pageable pageable = PageRequest.of(hotelSearchRequest.getPage(), hotelSearchRequest.getSize());
        long dateCount =
                ChronoUnit.DAYS.between(hotelSearchRequest.getStartDate(), hotelSearchRequest.getEndDate()) + 1;

        // business logic - 90 days
        Page<HotelPriceDto> hotelPage =
                hotelMinPriceRepository.findHotelsWithAvailableInventory(hotelSearchRequest.getCity(),
                        hotelSearchRequest.getStartDate(), hotelSearchRequest.getEndDate(), hotelSearchRequest.getRoomsCount(),
                        dateCount, pageable);

        return hotelPage;
    }
}
