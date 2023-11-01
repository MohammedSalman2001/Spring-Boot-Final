package com.nextravel.hotelserviceapi.service;

import com.nextravel.hotelserviceapi.dto.HotelDTO;
import com.nextravel.hotelserviceapi.exception.SaveFailException;

import java.util.List;

public interface HotelService {
    int save(HotelDTO hotelDTO) throws SaveFailException;
    void update(HotelDTO hotelDTO);
    void delete(int id);
    HotelDTO search(int id);
    List<HotelDTO> findByStarRate(String id);
}
