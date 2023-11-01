package com.nextravel.hotelserviceapi.service.impl;

import com.nextravel.hotelserviceapi.dto.HotelDTO;
import com.nextravel.hotelserviceapi.service.HotelService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {
    @Override
    public int save(HotelDTO hotelDTO) {
        return 0;
    }

    @Override
    public void update(HotelDTO hotelDTO) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public HotelDTO search(int id) {
        return null;
    }

    @Override
    public List<HotelDTO> findByStarRate(String id) {
        return null;
    }
}
