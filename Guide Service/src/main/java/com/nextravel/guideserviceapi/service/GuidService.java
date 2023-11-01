package com.nextravel.guideserviceapi.service;


import com.nextravel.guideserviceapi.dto.GuideDTO;
import com.nextravel.guideserviceapi.exception.SaveFailException;
import com.nextravel.guideserviceapi.exception.UpdateFailException;

public interface GuidService {
    int saveGuide(GuideDTO guideDTO) throws SaveFailException;
    void updateGuide(GuideDTO guideDTO) throws UpdateFailException;
    void deleteGuide(int id);
    GuideDTO getGuide(int id);
}
