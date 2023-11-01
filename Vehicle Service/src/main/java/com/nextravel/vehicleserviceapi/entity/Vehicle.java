package com.nextravel.vehicleserviceapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    private String vehicleId;
    private String name;
    private double priceFor1Km;
    private String category;
    private String vehicleType;
    private double priceFor100Km;
    private String fuelType;
    private int seatCapacity;
    private double fuelUsage;
    private String Hybrid;
    private String transmission;
    @Column(columnDefinition = "TEXT")
    private String images;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Driver driver;
}
