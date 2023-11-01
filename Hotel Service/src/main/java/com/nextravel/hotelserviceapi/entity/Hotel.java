package com.nextravel.hotelserviceapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String category;
    private String petAllowed;
    private String mapLink;
    private String address;
    @Column(columnDefinition = "TEXT")
    private String phone;
    private String email;
    @Column(columnDefinition = "TEXT")
    private String prices;
    @Column(columnDefinition = "TEXT")
    private String images;
    private String remarks;
}
