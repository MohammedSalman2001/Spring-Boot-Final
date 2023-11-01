package com.nextravel.guideserviceapi.api;

import com.nextravel.guideserviceapi.service.GuidService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/guide")
@CrossOrigin
public class GuideController {

    private final GuidService guidService;

    public GuideController(GuidService guidService) {
        this.guidService = guidService;
    }
}
