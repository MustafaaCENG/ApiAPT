package com.example.asansorapijava.service.impl;

import com.example.asansorapijava.dao.ApartmentRepository;
import com.example.asansorapijava.dao.entitiy.Apartment;
import com.example.asansorapijava.dto.ApartmentDto;
import com.example.asansorapijava.service.ApartmentService;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ApartmentDto save(ApartmentDto apartmentDto) {

        Apartment apt = modelMapper.map(apartmentDto, Apartment.class);
        apt = apartmentRepository.save(apt);
        apartmentDto.setId(apt.getId());
        return apartmentDto;
    }


    @Override
    public ApartmentDto getById(Long id) {
        Apartment apt = apartmentRepository.getOne(id);
        return modelMapper.map(apt, ApartmentDto.class);
    }

    @Override
    public List<ApartmentDto> getApartments() {
        List<Apartment> data = apartmentRepository.findAll();
        return Arrays.asList(modelMapper.map(data, ApartmentDto[].class));
    }

    @Override
    public Boolean delete(Long id) {
        apartmentRepository.deleteById(id);
        return true;
    }

    @Transactional
    public ApartmentDto update(Long id, ApartmentDto apartmentDto) {
        Apartment aptDb = apartmentRepository.getOne(id);
        if (aptDb == null)
            throw new IllegalArgumentException("Apartments does not exist ID :" + id);
        aptDb.setColor(apartmentDto.getColor());
        aptDb.setControlDate(apartmentDto.getControlDate());
        aptDb.setAptName(apartmentDto.getAptName());
        aptDb.setAdress(apartmentDto.getAdress());
        aptDb.setRegion(apartmentDto.getRegion());
        aptDb.setMail(apartmentDto.getMail());
        aptDb.setAdminName(apartmentDto.getAdminName());
        aptDb.setNumber(apartmentDto.getNumber());
        aptDb.setTax(apartmentDto.getTax());
        aptDb.setSozlesme(apartmentDto.getSozlesme());
        aptDb.setDescription(apartmentDto.getDescription());
        apartmentRepository.save(aptDb);
        return modelMapper.map(aptDb, ApartmentDto.class);
    }
}
