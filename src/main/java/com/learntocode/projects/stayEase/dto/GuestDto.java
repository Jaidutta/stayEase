package com.learntocode.projects.stayEase.dto;

import com.learntocode.projects.stayEase.entity.User;
import com.learntocode.projects.stayEase.entity.enums.Gender;
import lombok.Data;

@Data
public class GuestDto {
    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
}
