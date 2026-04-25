package com.learntocode.projects.stayEase.repository;

import com.learntocode.projects.stayEase.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
