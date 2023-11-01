package com.nextravel.hotelserviceapi.api;

import com.nextravel.hotelserviceapi.dto.HotelDTO;
import com.nextravel.hotelserviceapi.dto.PricesDTO;
import com.nextravel.hotelserviceapi.exception.DeleteFailException;
import com.nextravel.hotelserviceapi.exception.NotFoundException;
import com.nextravel.hotelserviceapi.exception.SaveFailException;
import com.nextravel.hotelserviceapi.exception.UpdateFailException;
import com.nextravel.hotelserviceapi.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/hotel")
@CrossOrigin
public class HotelController {
    private HotelService hotelService;

    public HotelController(HotelService hotelService){
        this.hotelService = hotelService;
    }


    @PostMapping()
    public ResponseEntity saveHotel(@RequestParam("files") ArrayList<MultipartFile> files,
                                      @RequestParam("name") String name,
                                      @RequestParam("category") String category,
                                      @RequestParam("petAllowed") String petAllowed,
                                      @RequestParam("mapLink") String mapLink,
                                      @RequestParam("address") String address,
                                      @RequestParam("phone") ArrayList<String> phone,
                                      @RequestParam("email") String email,
                                      @RequestParam("prices") ArrayList<PricesDTO> prices,
                                      @RequestParam("remarks") String remarks) {

        ArrayList<byte[]> bytes = new ArrayList<>();
        files.forEach(file -> {
            try {
                bytes.add(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setName(name);

        hotelDTO.setCategory(category);
        hotelDTO.setPetAllowed(petAllowed);
        hotelDTO.setMapLink(mapLink);
        hotelDTO.setAddress(address);
        hotelDTO.setPhone(phone);
        hotelDTO.setEmail(email);
        hotelDTO.setPrices(prices);
        hotelDTO.setRemarks(remarks);
        hotelDTO.setImages(bytes);

        try {
            int save = hotelService.save(hotelDTO);
            return new ResponseEntity(save, HttpStatus.CREATED);
        } catch (SaveFailException e) {
            e.printStackTrace();
            return new ResponseEntity("Request Fail", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable int id,
                                 @RequestParam("files") ArrayList<MultipartFile> files,
                                 @RequestParam("name") String name,
                                 @RequestParam("category") String category,
                                 @RequestParam("petAllowed") String petAllowed,
                                 @RequestParam("mapLink") String mapLink,
                                 @RequestParam("address") String address,
                                 @RequestParam("phone") ArrayList<String> phone,
                                 @RequestParam("email") String email,
                                 @RequestParam("prices") ArrayList<PricesDTO> prices,
                                 @RequestParam("remarks") String remarks) {
        HotelDTO hotelDTO = new HotelDTO();
        ArrayList<byte[]> bytes = new ArrayList<>();
        files.forEach(file -> {
            try {
                bytes.add(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        hotelDTO.setId(id);
        hotelDTO.setName(name);
        hotelDTO.setCategory(category);
        hotelDTO.setPetAllowed(petAllowed);
        hotelDTO.setMapLink(mapLink);
        hotelDTO.setAddress(address);
        hotelDTO.setPhone(phone);
        hotelDTO.setEmail(email);
        hotelDTO.setPrices(prices);
        hotelDTO.setRemarks(remarks);
        hotelDTO.setImages(bytes);
        try {
            hotelService.update(hotelDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UpdateFailException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        try {
            hotelService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DeleteFailException | NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{starRate:^Star-[2-5]$}")
    public ResponseEntity getByStarRate(@PathVariable String starRate){
//        int star = Integer.parseInt((starRate.split("-"))[1]);
        try {
            List<HotelDTO> byStarRate = hotelService.findByStarRate(starRate);
            return new ResponseEntity<>(byStarRate, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id)  {
        try {
            HotelDTO search = hotelService.search(id);
            return new ResponseEntity<>(search, HttpStatus.OK);
        }catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

}
