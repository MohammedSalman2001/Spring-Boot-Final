package com.nextravel.hotelserviceapi.api;

import com.nextravel.hotelserviceapi.service.HotelService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/hotel")
@CrossOrigin
public class HotelController {
    private HotelService hotelService;

    public HotelController(HotelService hotelService){
        this.hotelService = hotelService;
    }

}
