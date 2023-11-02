package com.nextravel.travelpackageapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelPackage extends SuperEntity{
    @Id
    private String id;
    private int hotelCount;
    private int areaCount;
    private double estimatedPrice;
    private String category;
    private int dayCount;
}
