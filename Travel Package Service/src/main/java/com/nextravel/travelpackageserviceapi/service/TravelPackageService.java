package com.nextravel.travelpackageserviceapi.service;

import com.nextravel.travelpackageserviceapi.dto.TravelPackageDTO;
import com.nextravel.travelpackageserviceapi.exception.DeleteFailException;
import com.nextravel.travelpackageserviceapi.exception.NotFoundException;
import com.nextravel.travelpackageserviceapi.exception.SaveFailException;
import com.nextravel.travelpackageserviceapi.exception.UpdateFailException;


import java.util.List;


public interface TravelPackageService {

    public int save(TravelPackageDTO dto) throws SaveFailException;
    void update(TravelPackageDTO obj) throws UpdateFailException;
    void delete(String id) throws DeleteFailException;
    List<TravelPackageDTO> getPackagesByCategory(String category);
    TravelPackageDTO fidById(String id) throws NotFoundException;
    List<TravelPackageDTO> findByCategory(String category) throws NotFoundException;

}
