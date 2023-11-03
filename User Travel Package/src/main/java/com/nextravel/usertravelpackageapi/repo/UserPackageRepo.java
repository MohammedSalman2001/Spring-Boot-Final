package com.nextravel.usertravelpackageapi.repo;

import com.nextravel.usertravelpackageapi.entity.UserTravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPackageRepo extends JpaRepository<UserTravelPackage,String> {
}
