package com.example.asansorapijava.dao.entitiy;


import com.example.asansorapijava.dao.entitiy.common.BaseEntity;
import com.example.asansorapijava.enumerate.Color;
import com.example.asansorapijava.enumerate.converter.ColorConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@Table(name = "Apartments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Where(clause = "isdeleted='false'")
@SQLDelete(sql = "UPDATE apartments SET isdeleted = true WHERE id = ?")

public class Apartment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Convert(converter= ColorConverter.class)
    @Column(name = "Color")
    private Color color;

    @JsonFormat(pattern="yyyy-MM-dd" )
    @Column(name = "ControlDate")
    private Date controlDate;

    @Column(name = "AptName")
    private String aptName;

    @Column(name = "Adress")
    private String adress;

    @Column(name = "Region")
    private String region;

    @Column(name = "Mail")
    private Boolean mail;

    @Column(name = "AdminName")
    private String adminName;

    @Column(name = "Number")
    private String number;

    @Column(name = "Tax")
    private Boolean tax;

    @Column(name = "Sozlesme")
    private Boolean sozlesme;

    @Column(name = "Description")
    private String description;
}
