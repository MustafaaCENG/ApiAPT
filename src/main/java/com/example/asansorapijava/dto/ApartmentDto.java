package com.example.asansorapijava.dto;

import com.example.asansorapijava.enumerate.Color;
import com.example.asansorapijava.enumerate.converter.ColorConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Apartments Data Transfer Object")

public class ApartmentDto {


    private Long id;

    private Color color;

    private Date controlDate;

    private String aptName;

    private String adress;

    private String region;

    private Boolean mail;

    private String adminName;

    private String number;

    private Boolean tax;

    private Boolean sozlesme;

    private String description;

}
