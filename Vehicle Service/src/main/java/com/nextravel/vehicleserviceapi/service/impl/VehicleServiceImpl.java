package com.nextravel.vehicleserviceapi.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nextravel.vehicleserviceapi.dto.core.DriverDto;
import com.nextravel.vehicleserviceapi.dto.core.VehicleDto;
import com.nextravel.vehicleserviceapi.entity.Driver;
import com.nextravel.vehicleserviceapi.entity.Vehicle;
import com.nextravel.vehicleserviceapi.exception.DeleteFailException;
import com.nextravel.vehicleserviceapi.exception.NotFoundException;
import com.nextravel.vehicleserviceapi.exception.SaveFailException;
import com.nextravel.vehicleserviceapi.exception.UpdatefailException;
import com.nextravel.vehicleserviceapi.repo.DriverRepo;
import com.nextravel.vehicleserviceapi.repo.VehicleRepo;
import com.nextravel.vehicleserviceapi.service.VehicleService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {


    DriverRepo driverRepo;
    ModelMapper modelMapper;
    Gson gson;
    VehicleRepo vehicleRepo;

    public VehicleServiceImpl(DriverRepo driverRepo, VehicleRepo vehicleRepo,
                              ModelMapper modelMapper, Gson gsonr) {
        this.driverRepo = driverRepo;
        this.vehicleRepo = vehicleRepo;
        this.modelMapper = modelMapper;
        this.gson = gsonr;

    }



    @Override
    public int saveVehicle(VehicleDto dto) throws SaveFailException {
        try {
            Vehicle vehicle = modelMapper.map(dto, Vehicle.class);
            Driver driver = modelMapper.map(dto.getDriverDTO(), Driver.class);
            exportImages(dto,driver,vehicle);

            Driver save = driverRepo.save(driver);
            vehicle.setDriver(save);
            return vehicleRepo.save(vehicle).getId();
        }catch (Exception e){
            throw new SaveFailException("Save Fail ",e);
        }



    }

    @Override
    public VehicleDto searchVehicle(int id) throws NotFoundException {
        try {
            Optional<Vehicle> byId = vehicleRepo.findById(id);
            if (byId.isPresent()){
                VehicleDto vehicle = modelMapper.map(byId.get(), VehicleDto.class);
                DriverDto driver = modelMapper.map(byId.get().getDriver(), DriverDto.class);
                vehicle.setDriverDTO(driver);
                importImages(vehicle,byId.get().getDriver(),byId.get());
                return vehicle;
            }else {
                throw new NotFoundException("Vehicle Not Found");
            }
        } catch ( Exception e ) {
            throw new NotFoundException("Vehicle Not Found",e);
        }
    }

    @Override
    public List<VehicleDto> searchByCategory(String category) throws NotFoundException {
        ArrayList<VehicleDto> objects = new ArrayList<>();
        try {
            List<Vehicle> byCategory = vehicleRepo.findByCategory(category);
            for (Vehicle vehicle : byCategory) {
                VehicleDto vehicleDTO = modelMapper.map(vehicle, VehicleDto.class);
                DriverDto driverDTO = modelMapper.map(vehicle.getDriver(), DriverDto.class);
                vehicleDTO.setDriverDTO(driverDTO);
                importImages(vehicleDTO,vehicle.getDriver(),vehicle);
                objects.add(vehicleDTO);
            }
            return objects;
        }catch (Exception e){
            throw new NotFoundException("Vehicles Not Found",e);
        }
    }

    @Override
    public void updateVehicle(VehicleDto dto) throws UpdatefailException {
        try {
            Vehicle vehicle = modelMapper.map(dto, Vehicle.class);
            Driver driver = modelMapper.map(dto.getDriverDTO(), Driver.class);
            Optional<Driver> byId = driverRepo.findById(driver.getId());
            if (byId.isPresent()){
                deleteImages(byId);
                exportImages(dto,driver,vehicle);
                Driver save = driverRepo.save(driver);
                vehicle.setDriver(save);
                vehicleRepo.save(vehicle);
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new UpdatefailException("Update Fail",e);
        }
    }

    @Override
    public void deleteVehicle(int id) throws DeleteFailException {
        try {
            Optional<Driver> byId = driverRepo.findById(id);
            if (byId.isPresent()){
                deleteImages(byId);
                vehicleRepo.deleteById(id);
            }else {
                throw new NotFoundException("Vehicle Not Found");
            }
        }catch (Exception e){
            throw new DeleteFailException("Delete Fail",e);
        }
    }

    private void deleteImages(Optional<Driver> byId) {
        if (byId.isPresent()){
            if (byId.get().getLicenseImageFront() != null) {
                File file = new File(byId.get().getLicenseImageFront());
                boolean delete = file.delete();
                System.out.println("Front " + delete);
            }
            if (byId.get().getLicenseImageRear() != null) {
                File file = new File(byId.get().getLicenseImageRear());
                boolean delete = file.delete();
                System.out.println("Rear " + delete);
            }
            if (byId.isPresent()){
                Driver driver = byId.get();
                String images = driver.getVehicle().getImages();
                if (images != null){
                    ArrayList<String> pathList = gson.fromJson(images, new TypeToken<ArrayList<String>>(){}.getType());
                    for (String path : pathList) {
                        File file = new File(path);
                        boolean delete = file.delete();
                        System.out.println("Images " + delete);
                    }
                }
            }
        }
    }




    public void exportImages(VehicleDto vehicleDTO, Driver driver, Vehicle vehicle) {
        ArrayList<byte[]> images = vehicleDTO.getImages();
        String dt = LocalDate.now().toString().replace("-", "_") + "__"
                + LocalTime.now().toString().replace(":", "_");

        ArrayList<String> pathList = new ArrayList<>();

        for (int i = 0; i < images.size(); i++) {
            {
                try {
                    InputStream is = new ByteArrayInputStream(images.get(i));
                    BufferedImage bi = ImageIO.read(is);
                    File outputfile = new File("images/vehicle/" + dt + "_" + i + ".jpg");
                    ImageIO.write(bi, "jpg", outputfile);
                    pathList.add(outputfile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        vehicle.setImages(gson.toJson(pathList));

        System.out.println(pathList);
        byte[] licenseImageFront = vehicleDTO.getDriverDTO().getLicenseImageFront();
        byte[] licenseImageRear = vehicleDTO.getDriverDTO().getLicenseImageRear();


        try {
            InputStream is = new ByteArrayInputStream(licenseImageFront);
            BufferedImage bi = ImageIO.read(is);
            File outputfile = new File("images/driver/" + dt + "_front"+ ".jpg");
            ImageIO.write(bi, "jpg", outputfile);
            String absolutePath = outputfile.getAbsolutePath();
            driver.setLicenseImageFront(absolutePath);

            InputStream is1 = new ByteArrayInputStream(licenseImageRear);
            BufferedImage bi1 = ImageIO.read(is1);
            File outputfile1 = new File("images/driver/" + dt + "_rear"+ ".jpg");
            ImageIO.write(bi1, "jpg", outputfile1);
            String absolutePath1 = outputfile1.getAbsolutePath();
            driver.setLicenseImageRear(absolutePath1);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void importImages(VehicleDto vehicleDTO, Driver driver, Vehicle vehicle) throws IOException, IOException {
        BufferedImage read = ImageIO.read(new File(driver.getLicenseImageFront()));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(read, "jpg", baos);
        byte[] bytes = baos.toByteArray();
        vehicleDTO.getDriverDTO().setLicenseImageFront(bytes);

        BufferedImage read1 = ImageIO.read(new File(driver.getLicenseImageRear()));
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        ImageIO.write(read1, "jpg", baos1);
        byte[] bytes1 = baos1.toByteArray();
        vehicleDTO.getDriverDTO().setLicenseImageRear(bytes1);


        String images = vehicle.getImages();
        vehicleDTO.setImages(new ArrayList<>());
        ArrayList<String> imageList = gson.fromJson(images, new TypeToken<ArrayList<String>>() {});
        for (int i = 0; i < imageList.size(); i++) {
            BufferedImage r = ImageIO.read(new File(driver.getLicenseImageRear()));
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ImageIO.write(r, "jpg", b);
            byte[] imgData= b.toByteArray();
            vehicleDTO.getImages().add(imgData);
        }

    }
}


