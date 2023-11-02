package com.nextravel.travelpackageserviceapi.repo;



import com.nextravel.travelpackageserviceapi.entity.TravelPackage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TravelPackageRepo extends CrudRepository<TravelPackage, Integer> {

    @Query(value = "SELECT * FROM travel_package WHERE category=?",nativeQuery = true)
    List<TravelPackage>  findAllByCategory(String value);


}
