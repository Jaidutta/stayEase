package com.learntocode.projects.stayEase.repository;

import com.learntocode.projects.stayEase.entity.Inventory;
import com.learntocode.projects.stayEase.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    void deleteByDateAfterAndRoom(LocalDate date, Room room);
}
