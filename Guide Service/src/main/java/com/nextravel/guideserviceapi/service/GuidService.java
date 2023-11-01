package com.nextravel.guideserviceapi.service;


import com.nextravel.guideserviceapi.dto.GuideDTO;
import com.nextravel.guideserviceapi.exception.SaveFailException;

public interface GuidService {
    int saveGuide(GuideDTO guideDTO) throws SaveFailException;
    void updateGuide(GuideDTO guideDTO);
    void deleteGuide(int id);
    GuideDTO getGuide(int id);
}
