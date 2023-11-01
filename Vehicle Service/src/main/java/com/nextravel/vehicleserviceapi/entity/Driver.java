package com.nextravel.vehicleserviceapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Driver {

    @Id
    private String driverId;
    private String driverName;
    private String driverContact;
    private String driverNic;
    private String driverRemarks;
    private String licenseImageFront;
    private String licenseImageRear;

    @OneToOne(targetEntity = Vehicle.class , mappedBy = "driver")
    private Vehicle vehicle;
}
