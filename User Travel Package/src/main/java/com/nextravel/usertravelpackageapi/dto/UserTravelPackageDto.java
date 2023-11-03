package com.nextravel.usertravelpackageapi.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class UserTravelPackageDto {
    private String  userTravelPackageId;
    private String packageCategory;
    private int hotelId;
    private int vehicleId;
    private int guideId;
    private String travelArea;
    private int NoAdults;
    private int NoChildren;
    private int totalHeadcount;
    private int dayCount;
    private double packagePrice;
}
