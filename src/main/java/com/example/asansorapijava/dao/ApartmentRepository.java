package com.example.asansorapijava.dao;


import com.example.asansorapijava.dao.entitiy.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ApartmentRepository extends JpaRepository <Apartment,Long> {
}
