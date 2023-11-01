package com.nextravel.vehicleserviceapi.service.impl;

import com.nextravel.vehicleserviceapi.dto.core.VehicleDto;
import com.nextravel.vehicleserviceapi.service.VehicleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Override
    public int saveVehicle(VehicleDto dto) {
        return -1;
    }
}
