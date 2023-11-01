package com.nextravel.guideserviceapi.repo;


import com.nextravel.guideserviceapi.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuideRepo extends JpaRepository<Guide, Integer> {
}
