package com.nextravel.hotelserviceapi.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nextravel.hotelserviceapi.dto.HotelDTO;
import com.nextravel.hotelserviceapi.dto.PricesDTO;
import com.nextravel.hotelserviceapi.entity.Hotel;
import com.nextravel.hotelserviceapi.exception.DeleteFailException;
import com.nextravel.hotelserviceapi.exception.NotFoundException;
import com.nextravel.hotelserviceapi.exception.SaveFailException;
import com.nextravel.hotelserviceapi.exception.UpdateFailException;
import com.nextravel.hotelserviceapi.repo.HotelRepo;
import com.nextravel.hotelserviceapi.service.HotelService;
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
public class HotelServiceImpl implements HotelService {

    private final HotelRepo hotelRepo;

    private final ModelMapper modelMapper;

    private final Gson gson;
    public HotelServiceImpl(HotelRepo hotelRepo, ModelMapper modelMapper, Gson gson) {
        this.hotelRepo = hotelRepo;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public int save(HotelDTO hotelDTO) throws SaveFailException {
        try {
            Hotel map = modelMapper.map(hotelDTO, Hotel.class);
            map.setPhone(gson.toJson(hotelDTO.getPhone()));
            map.setPrices(gson.toJson(hotelDTO.getPrices()));
            exportImages(hotelDTO,map);
            return hotelRepo.save(map).getId();
        }catch (Exception e){
            throw new SaveFailException("Save Fail ",e);
        }
    }

    @Override
    public void update(HotelDTO hotelDTO) throws UpdateFailException {
        try {
            Optional<Hotel> byId = hotelRepo.findById(hotelDTO.getId());
            if (byId.isPresent()){
                deleteImages(byId);
                Hotel map = modelMapper.map(hotelDTO, Hotel.class);
                map.setPhone(gson.toJson(hotelDTO.getPhone()));
                map.setPrices(gson.toJson(hotelDTO.getPrices()));
                exportImages(hotelDTO,map);
                hotelRepo.save(map);
            }
        }catch (Exception e){
            throw new UpdateFailException("Update Fail ",e);
        }
    }

    @Override
    public void delete(int id) throws DeleteFailException, NotFoundException {
        try {
            Optional<Hotel> byId = hotelRepo.findById(id);
            if (byId.isPresent()){
                deleteImages(byId);
                hotelRepo.deleteById(id);
            }else {
                throw new NotFoundException("Hotel Not Found");
            }
        }catch (NotFoundException e){
            throw e;
        }catch (Exception e){
            throw new DeleteFailException("Delete Fail ",e);
        }
    }

    @Override
    public HotelDTO search(int id) throws NotFoundException {

        try {
            Optional<Hotel> byId = hotelRepo.findById(id);
            if (byId.isPresent()){
                HotelDTO hotel = modelMapper.map(byId.get(), HotelDTO.class);
                importImages(hotel,byId.get());
                return hotel;
            }else {
                throw new NotFoundException("Hotel Not Found");
            }
        } catch (Exception e) {
            throw new NotFoundException("Error Occurred :(",e);
        }
    }

    @Override
    public List<HotelDTO> findByStarRate(String id) throws NotFoundException {
        try {
            List<Hotel> byStar = hotelRepo.findByCategory(id);
            for (Hotel c: byStar){
                System.out.println(c.getCategory());
                System.out.println(c.getPhone());
            }

            if (!byStar.isEmpty()){
                List<HotelDTO> hotelDTOS = new ArrayList<>();
                for (Hotel hotel : byStar) {
                    HotelDTO hotelDTO = modelMapper.map(hotel, HotelDTO.class);
                    hotelDTO.setPrices(gson.fromJson(hotel.getPrices(),new TypeToken<ArrayList<PricesDTO>>(){}));
                    hotelDTO.setPhone(gson.fromJson(hotel.getPhone(),new TypeToken<ArrayList<String>>(){}));

                    importImages(hotelDTO,hotel);
                    hotelDTOS.add(hotelDTO);
                }
                return hotelDTOS;
            }else {
                throw new NotFoundException("Hotels Not Found");
            }
        } catch (Exception e) {
            throw new NotFoundException("Not Found",e);
        }
    }

    public void exportImages(HotelDTO hotelDTO, Hotel hotel) {
        ArrayList<byte[]> images = hotelDTO.getImages();
        String dt = LocalDate.now().toString().replace("-", "_") + "__"
                + LocalTime.now().toString().replace(":", "_");

        ArrayList<String> pathList = new ArrayList<>();

        for (int i = 0; i < images.size(); i++) {
            {
                try {
                    InputStream is = new ByteArrayInputStream(images.get(i));
                    BufferedImage bi = ImageIO.read(is);
                    File outputfile = new File("images/hotels/" + dt + "_" + i + ".jpg");
                    ImageIO.write(bi, "jpg", outputfile);
                    pathList.add(outputfile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        hotel.setImages(gson.toJson(pathList));


    }



    private void deleteImages(Optional<Hotel> byId) {
        if (byId.isPresent()){
            if (byId.isPresent()){
                Hotel hotel = byId.get();
                String images = hotel.getImages();
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

    public void importImages(HotelDTO hotelDTO,Hotel hotel) throws IOException {
        String images = hotel.getImages();
        hotelDTO.setImages(new ArrayList<>());
        if (images != null){
            ArrayList<String> imageList = gson.fromJson(images, new TypeToken<ArrayList<String>>(){}.getType());
            for (int i = 0; i < imageList.size(); i++) {
                BufferedImage r = ImageIO.read(new File(imageList.get(i)));
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                ImageIO.write(r, "jpg", b);
                byte[] imgData= b.toByteArray();
                hotelDTO.getImages().add(imgData);
            }
        }
    }
}
