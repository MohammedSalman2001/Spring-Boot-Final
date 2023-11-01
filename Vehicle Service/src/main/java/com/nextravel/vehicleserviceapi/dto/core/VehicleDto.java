package com.nextravel.vehicleserviceapi.dto.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
    private int id;
    private String vehicleName;
    private String fuelType;
    private String Hybrid;
    private ArrayList<byte[]> images;
    private double priceFor1Km;
    private double fuelUsage;
    private double priceFor100Km;
    private int seatCapacity;
    private String vehicleType;
    private String category;
    private String transmission;
    private DriverDto driverDTO;
}
