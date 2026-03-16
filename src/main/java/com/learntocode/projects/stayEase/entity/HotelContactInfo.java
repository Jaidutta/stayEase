package com.learntocode.projects.stayEase.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable // These properties can be embedded into another Table as that table's columns
// It is embedded in Hotel Entity as contactInfo
public class HotelContactInfo {
    private String address;
    private String phoneNumber;
    private String email;
    private String location;

}
