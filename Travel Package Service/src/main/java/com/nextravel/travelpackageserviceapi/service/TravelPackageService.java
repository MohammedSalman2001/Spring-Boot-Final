package com.nextravel.travelpackageserviceapi.service;

import com.nextravel.travelpackageserviceapi.dto.TravelPackageDTO;
import com.nextravel.travelpackageserviceapi.entity.TravelPackage;
import com.nextravel.travelpackageserviceapi.exception.DeleteFailException;
import com.nextravel.travelpackageserviceapi.exception.NotFoundException;
import com.nextravel.travelpackageserviceapi.exception.SaveFailException;
import com.nextravel.travelpackageserviceapi.exception.UpdateFailException;


import java.util.List;


public interface TravelPackageService {

    public int save(TravelPackageDTO dto) throws SaveFailException;
    void update(TravelPackageDTO obj) throws UpdateFailException;
    TravelPackageDTO fidById(int id) throws NotFoundException;

    void delete(int id) throws DeleteFailException;

    public List<TravelPackageDTO> findAll();

    List<TravelPackageDTO>  findAllByCategory(String value);
}
