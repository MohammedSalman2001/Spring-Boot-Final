package com.nextravel.guideserviceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuideDTO {
    private int id;
    private String name;
    private String address;
    private String contact;
    private String birthDate;
    private double manDayValue;
    private String experience;
    private byte[] guideIdFront;
    private byte[] guideIdRear;
    private byte[] nicFront;
    private byte[] nicRear;
    private byte[] profilePic;
}
