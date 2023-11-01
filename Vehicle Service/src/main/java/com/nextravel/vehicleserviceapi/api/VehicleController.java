package com.nextravel.vehicleserviceapi.api;

import com.nextravel.vehicleserviceapi.dto.core.DriverDto;
import com.nextravel.vehicleserviceapi.dto.core.VehicleDto;
import com.nextravel.vehicleserviceapi.exception.SaveFailException;
import com.nextravel.vehicleserviceapi.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/vehicle")
@CrossOrigin
public class VehicleController {

    VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity addVehicle(@RequestParam String vehicleName,
                                     @RequestParam String fuelType,
                                     @RequestParam String isHybrid,
                                     @RequestParam ArrayList<MultipartFile> files,
                                     @RequestParam double priceFor1Km,
                                     @RequestParam double fuelUsage,
                                     @RequestParam double priceFor100Km,
                                     @RequestParam int noOfSeats,
                                     @RequestParam String vehicleType,
                                     @RequestParam String category,
                                     @RequestParam String transmission,
                                     @RequestParam String driverName,
                                     @RequestParam String driverNicNo,
                                     @RequestParam String driverContactNO,
                                     @RequestPart byte[] licenceImageFront,
                                     @RequestPart byte[] licenceImageRear,
                                     @RequestParam String driverRemarks) {

        VehicleDto vehicleDTO = new VehicleDto();
        DriverDto driverDTO = new DriverDto();
        ArrayList<byte[]> objects = new ArrayList<>();
        files.stream().forEach(file -> {
            try {
                objects.add(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        vehicleDTO.setDriverDTO(driverDTO);
        vehicleDTO.setVehicleName(vehicleName);
        vehicleDTO.setFuelType(fuelType);
        vehicleDTO.setHybrid(isHybrid);
        vehicleDTO.setPriceFor1Km(priceFor1Km);
        vehicleDTO.setFuelUsage(fuelUsage);
        vehicleDTO.setPriceFor100Km(priceFor100Km);
        vehicleDTO.setSeatCapacity(noOfSeats);
        vehicleDTO.setVehicleType(vehicleType);
        vehicleDTO.setCategory(category);
        vehicleDTO.setTransmission(transmission);
        vehicleDTO.setImages(objects);

        driverDTO.setDriverName(driverName);
        driverDTO.setDriverNic(driverNicNo);
        driverDTO.setDriverContact(driverContactNO);
        driverDTO.setLicenseImageFront(licenceImageFront);
        driverDTO.setLicenseImageRear(licenceImageRear);
        driverDTO.setDriverRemarks(driverRemarks);

        int i = 0;
        try {
            i = vehicleService.saveVehicle(vehicleDTO);
            return new ResponseEntity(i, HttpStatus.CREATED);
        } catch (SaveFailException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
