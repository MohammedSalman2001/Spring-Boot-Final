package com.nextravel.guideserviceapi.service;


import com.nextravel.guideserviceapi.dto.GuideDTO;

public interface GuidService {
    int saveGuide(GuideDTO guideDTO);
    void updateGuide(GuideDTO guideDTO);
    void deleteGuide(int id);
    GuideDTO getGuide(int id);
}
