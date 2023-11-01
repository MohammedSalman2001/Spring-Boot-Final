package com.nextravel.vehicleserviceapi.repo;




import com.nextravel.vehicleserviceapi.entity.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface VehicleRepo extends CrudRepository<Vehicle,Integer> {
  List<Vehicle> findByCategory(String category);
}
