package com.nextravel.vehicleserviceapi.api;

import com.nextravel.vehicleserviceapi.dto.core.DriverDto;
import com.nextravel.vehicleserviceapi.dto.core.VehicleDto;
import com.nextravel.vehicleserviceapi.exception.DeleteFailException;
import com.nextravel.vehicleserviceapi.exception.NotFoundException;
import com.nextravel.vehicleserviceapi.exception.SaveFailException;
import com.nextravel.vehicleserviceapi.exception.UpdatefailException;
import com.nextravel.vehicleserviceapi.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @PutMapping("/{id}/{did}")
    public ResponseEntity updateVehicle(@PathVariable int id,
                                        @RequestParam String vehicleName,
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
                                        @PathVariable int did,
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
        vehicleDTO.setId(id);
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
        driverDTO.setId(did);
        driverDTO.setDriverName(driverName);
        driverDTO.setDriverNic(driverNicNo);
        driverDTO.setDriverContact(driverContactNO);
        driverDTO.setLicenseImageFront(licenceImageFront);
        driverDTO.setLicenseImageRear(licenceImageRear);
        driverDTO.setDriverRemarks(driverRemarks);

        try {
            vehicleService.updateVehicle(vehicleDTO);
            return new ResponseEntity(HttpStatus.OK);
        } catch (UpdatefailException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteVehicle(@PathVariable int id) {

        try {
            vehicleService.deleteVehicle(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (DeleteFailException e) {
            return new ResponseEntity("Operation Fail", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{category:^Regular|Mid-level|Luxury|Super Luxury$}")
    public ResponseEntity getByCategory(@PathVariable String category){
        try {
            List<VehicleDto> list=vehicleService.searchByCategory(category);
            if (list.isEmpty()){
                throw new NotFoundException("Vehicle Not Found");
            }
            return new ResponseEntity(list,HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "{id}")
    public ResponseEntity getById(@PathVariable int id){
        try {
            VehicleDto vehicleDto = vehicleService.searchVehicle(id);
            if (vehicleDto==null){
                throw new NotFoundException("Vehicle Not Found");
            }
            return new ResponseEntity(vehicleDto,HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
