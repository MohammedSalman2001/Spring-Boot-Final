package com.nextravel.vehicleserviceapi.service;

import com.nextravel.vehicleserviceapi.dto.core.VehicleDto;
import com.nextravel.vehicleserviceapi.exception.SaveFailException;

public interface VehicleService {

    int saveVehicle(VehicleDto dto) throws SaveFailException;
}
