package com.nextravel.usertravelpackageapi.service;

import lk.ijse.gdse63.spring_final.user_travel_package_micro_service.dto.UserTravelPackageDTO;
import lk.ijse.gdse63.spring_final.user_travel_package_micro_service.exception.SaveFailException;


public interface UserTravelPackageService {
    int save(UserTravelPackageDTO ob) throws SaveFailException;

}
