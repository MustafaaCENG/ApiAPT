package com.example.asansorapijava.service;

import com.example.asansorapijava.dto.ApartmentDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ApartmentService {

    ApartmentDto save(ApartmentDto defectDto);

    ApartmentDto getById(Long id);

    List<ApartmentDto> getApartments();

    Boolean delete(Long id);

    ApartmentDto update(Long id, ApartmentDto defectDto);


}

