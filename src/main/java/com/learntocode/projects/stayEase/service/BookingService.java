package com.learntocode.projects.stayEase.service;

import com.learntocode.projects.stayEase.dto.BookingDto;
import com.learntocode.projects.stayEase.dto.BookingRequest;
import com.learntocode.projects.stayEase.dto.GuestDto;

import java.util.List;

public interface BookingService {

    BookingDto initializeBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);
}
