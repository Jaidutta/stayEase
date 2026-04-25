package com.learntocode.projects.stayEase.repository;

import com.learntocode.projects.stayEase.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}