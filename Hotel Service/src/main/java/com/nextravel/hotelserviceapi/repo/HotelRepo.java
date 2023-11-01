package com.nextravel.hotelserviceapi.repo;

;

import com.nextravel.hotelserviceapi.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepo extends JpaRepository<Hotel,Integer> {
    List<Hotel> findByCategory(String star);
}
