package com.learntocode.projects.stayEase.service;

import com.learntocode.projects.stayEase.entity.Room;

public interface InventoryService {
    void initializeRoomForAYear(Room room);
    void deleteFutureInventoriesForRoom(Room room);
}
