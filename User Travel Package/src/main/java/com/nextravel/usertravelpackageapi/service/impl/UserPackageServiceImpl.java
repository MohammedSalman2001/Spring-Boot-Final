package com.nextravel.usertravelpackageapi.service.impl;

import com.nextravel.usertravelpackageapi.dto.UserTravelPackageDto;
import com.nextravel.usertravelpackageapi.entity.UserTravelPackage;
import com.nextravel.usertravelpackageapi.repo.UserPackageRepo;
import com.nextravel.usertravelpackageapi.service.UserPackageService;

import com.nextravel.usertravelpackageapi.util.Generator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserPackageServiceImpl implements UserPackageService {



    private final UserPackageRepo userPackageRepo;
    private final ModelMapper modelMapper;

    private final Generator generator;


    @Autowired
    public UserPackageServiceImpl(UserPackageRepo userPackageRepo, ModelMapper modelMapper, Generator generator) {
        this.modelMapper = modelMapper;
        this.userPackageRepo = userPackageRepo;
        this.generator = generator;
    }

    @Override
    public void saveUserTravelPackage(UserTravelPackageDto dto) {
        String generatePrefix = generator.generatePrefix(5, 16);

        String primaryKey = generator.generatePrimaryKey(generatePrefix, "NEXT");

        if(userPackageRepo.existsById(primaryKey)){
            throw new RuntimeException("Duplicate Entry");
        }else {
            UserTravelPackage map = modelMapper.map(dto, UserTravelPackage.class);
            map.setUserTravelPackageId(primaryKey);
            userPackageRepo.save(map);
        }




    }
}
