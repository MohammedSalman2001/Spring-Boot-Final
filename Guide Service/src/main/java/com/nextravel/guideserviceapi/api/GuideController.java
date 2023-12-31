package com.nextravel.guideserviceapi.api;

import com.nextravel.guideserviceapi.dto.GuideDTO;
import com.nextravel.guideserviceapi.exception.SaveFailException;
import com.nextravel.guideserviceapi.exception.SearchFailException;
import com.nextravel.guideserviceapi.exception.UpdateFailException;
import com.nextravel.guideserviceapi.service.GuidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/guide")
@CrossOrigin
public class GuideController {
    private final GuidService guidService;

    @Autowired
    public GuideController(GuidService guidService) {
        this.guidService = guidService;
    }

    @PostMapping
    public ResponseEntity saveGuide(@RequestParam("guideName")String guideName,
                                    @RequestParam("guideAddress")String guideAddress,
                                    @RequestParam("guideContact") String guideContact,
                                    @RequestParam("guideBirthDate") String guideBirthDate,
                                    @RequestParam("guideManDayValue") double guideManDayValue,
                                    @RequestParam("guideExperience") String guideExperience,
                                    @RequestPart("guideIdFront") byte[] guideIdFront,
                                    @RequestPart("guideIdRear") byte[] guideIdRear,
                                    @RequestPart("guideNicFront") byte[] guideNicFront,
                                    @RequestPart("guideNicRear") byte[] guideNicRear,
                                    @RequestPart("guideProfilePic") byte[] guideProfilePic) {

        GuideDTO guideDTO = new GuideDTO();
        guideDTO.setName(guideName);
        guideDTO.setAddress(guideAddress);
        guideDTO.setContact(guideContact);
        guideDTO.setBirthDate(guideBirthDate);
        guideDTO.setManDayValue(guideManDayValue);
        guideDTO.setExperience(guideExperience);
        guideDTO.setGuideIdFront(guideIdFront);
        guideDTO.setGuideIdRear(guideIdRear);
        guideDTO.setNicFront(guideNicFront);
        guideDTO.setNicRear(guideNicRear);
        guideDTO.setProfilePic(guideProfilePic);
        try {
            int i = guidService.saveGuide(guideDTO);
            return new ResponseEntity<>(i, HttpStatus.CREATED);
        } catch (SaveFailException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateGuide(@PathVariable int id,
                                      @RequestParam("guideName")String guideName,
                                      @RequestParam("guideAddress")String guideAddress,
                                      @RequestParam("guideContact") String guideContact,
                                      @RequestParam("guideBirthDate") String guideBirthDate,
                                      @RequestParam("guideManDayValue") double guideManDayValue,
                                      @RequestParam("guideExperience") String guideExperience,
                                      @RequestPart("guideIdFront") byte[] guideIdFront,
                                      @RequestPart("guideIdRear") byte[] guideIdRear,
                                      @RequestPart("guideNicFront") byte[] guideNicFront,
                                      @RequestPart("guideNicRear") byte[] guideNicRear,
                                      @RequestPart("guideProfilePic") byte[] guideProfilePic


                                    ) {

        GuideDTO guideDTO = new GuideDTO();
        guideDTO.setId(id);
        guideDTO.setName(guideName);
        guideDTO.setAddress(guideAddress);
        guideDTO.setContact(guideContact);
        guideDTO.setBirthDate(guideBirthDate);
        guideDTO.setManDayValue(guideManDayValue);
        guideDTO.setExperience(guideExperience);
        guideDTO.setGuideIdFront(guideIdFront);
        guideDTO.setGuideIdRear(guideIdRear);
        guideDTO.setNicFront(guideNicFront);
        guideDTO.setNicRear(guideNicRear);
        guideDTO.setProfilePic(guideProfilePic);

        try {
            guidService.updateGuide(guideDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UpdateFailException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteGuide(@PathVariable int id) {
        try {
            guidService.deleteGuide(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getGuide(@PathVariable int id) {

        try {
            GuideDTO guide = guidService.getGuide(id);
            return new ResponseEntity<>(guide, HttpStatus.OK);
        } catch (SearchFailException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }



}
