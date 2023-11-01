package com.nextravel.vehicleserviceapi.repo;



import com.nextravel.vehicleserviceapi.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepo extends JpaRepository<Driver,Integer> {
}
