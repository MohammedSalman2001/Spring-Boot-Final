package com.nextravel.travelpackageserviceapi.service.impl;


import com.nextravel.travelpackageserviceapi.dto.TravelPackageDTO;
import com.nextravel.travelpackageserviceapi.entity.TravelPackage;
import com.nextravel.travelpackageserviceapi.exception.DeleteFailException;
import com.nextravel.travelpackageserviceapi.exception.NotFoundException;
import com.nextravel.travelpackageserviceapi.exception.SaveFailException;
import com.nextravel.travelpackageserviceapi.exception.UpdateFailException;
import com.nextravel.travelpackageserviceapi.repo.TravelPackageRepo;
import com.nextravel.travelpackageserviceapi.service.TravelPackageService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

@Service
public class TravelPackageServiceIMPL implements TravelPackageService {
    TravelPackageRepo travelPackageRepo;
    ModelMapper modelMapper;



    public TravelPackageServiceIMPL(TravelPackageRepo travelPackageRepo,
                                    ModelMapper modelMapper) {
        this.travelPackageRepo = travelPackageRepo;
        this.modelMapper = modelMapper;

    }

    @Override
    public int save(TravelPackageDTO obj) throws SaveFailException {
        try {

            TravelPackage map = modelMapper.map(obj, TravelPackage.class);
            TravelPackage save = travelPackageRepo.save(map);
            return save.getId();
        }catch (Exception e){
            throw new SaveFailException("Operation Fail",e);
        }

    }

    @Override
    public void update(TravelPackageDTO obj) throws UpdateFailException {
        try{
            TravelPackage map = modelMapper.map(obj, TravelPackage.class);
            travelPackageRepo.save(map);
        }catch (Exception e){
            throw new UpdateFailException("Operation Fail",e);
        }

    }

    @Override
    public void delete(String id) throws DeleteFailException {
        try{
            travelPackageRepo.deleteById(id);
        }catch (Exception e){
            throw new DeleteFailException("Operation Fail",e);
        }

    }

    @Override
    public List<TravelPackageDTO> getPackagesByCategory(String category) {
        return null;
    }

    @Override
    public TravelPackageDTO fidById(String id) throws NotFoundException {
        Optional<TravelPackage> byId = travelPackageRepo.findById(id);
        if (byId.isPresent()) {
            return modelMapper.map(byId.get(), TravelPackageDTO.class);
        }else {
            throw new NotFoundException("Not Found");
        }
    }

    @Override
    public List<TravelPackageDTO> findByCategory(String category) throws NotFoundException {
        try{
            List<TravelPackage> byCategory = travelPackageRepo.findByCategory(category);
            ArrayList<TravelPackageDTO> list = modelMapper.map(byCategory, new TypeToken<ArrayList<TravelPackageDTO>>() {
            }.getType());
            if (list.isEmpty()) {
                throw new NotFoundException("Not Found");
            }
            return list;
        }catch (Exception e){
            throw new NotFoundException("Not Found",e);
        }

    }


}
