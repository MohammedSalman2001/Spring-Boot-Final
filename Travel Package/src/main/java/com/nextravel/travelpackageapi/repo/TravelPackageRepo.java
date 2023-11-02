package com.nextravel.travelpackageapi.repo;



import com.nextravel.travelpackageapi.entity.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelPackageRepo extends JpaRepository<TravelPackage, String> {
    List<TravelPackage> findByCategory(String category);
    List<TravelPackage> findAll();

}
