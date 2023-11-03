package com.nextravel.travelpackageserviceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelPackageDTO{
    private String id;
    private int hotelCount;
    private int areaCount;
    private double estimatedPrice;
    private String category;
    private int dayCount;
}
