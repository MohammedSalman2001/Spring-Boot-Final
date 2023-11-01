package com.nextravel.hotelserviceapi.service;

import com.nextravel.hotelserviceapi.dto.HotelDTO;
import com.nextravel.hotelserviceapi.exception.DeleteFailException;
import com.nextravel.hotelserviceapi.exception.NotFoundException;
import com.nextravel.hotelserviceapi.exception.SaveFailException;
import com.nextravel.hotelserviceapi.exception.UpdateFailException;

import java.util.List;

public interface HotelService {
    int save(HotelDTO hotelDTO) throws SaveFailException;
    void update(HotelDTO hotelDTO) throws UpdateFailException;
    void delete(int id) throws DeleteFailException, NotFoundException;
    HotelDTO search(int id) throws NotFoundException;
    List<HotelDTO> findByStarRate(String id) throws NotFoundException;
}
