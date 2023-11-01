package com.nextravel.vehicleserviceapi.repo;



import com.nextravel.vehicleserviceapi.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DriverRepo extends JpaRepository<Driver,Integer> {
}
