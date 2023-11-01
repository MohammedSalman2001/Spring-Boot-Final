package com.nextravel.vehicleserviceapi.dto.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDto {
    private int id;
    private String driverName;
    private String driverContact;
    private String driverNic;
    private String driverRemarks;
    private byte[] licenseImageFront;
    private byte[] licenseImageRear;
}
