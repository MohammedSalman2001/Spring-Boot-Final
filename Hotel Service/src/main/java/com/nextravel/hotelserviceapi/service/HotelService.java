package com.nextravel.hotelserviceapi.service;

import java.util.List;

public interface HotelService {
    int save(HotelDTO hotelDTO);
    void update(HotelDTO hotelDTO);
    void delete(int id);
    HotelDTO search(int id);
    List<HotelDTO> findByStarRate(String id);
}
