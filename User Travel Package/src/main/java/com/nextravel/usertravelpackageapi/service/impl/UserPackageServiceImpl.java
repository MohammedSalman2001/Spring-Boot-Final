package com.nextravel.usertravelpackageapi.service.impl;

import com.nextravel.usertravelpackageapi.dto.UserTravelPackageDto;
import com.nextravel.usertravelpackageapi.service.UserPackageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserPackageServiceImpl implements UserPackageService {
    @Override
    public void saveUserTravelPackage(UserTravelPackageDto dto) {

    }
}
