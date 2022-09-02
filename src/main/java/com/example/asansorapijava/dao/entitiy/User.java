package com.example.asansorapijava.dao.entitiy;

import com.example.asansorapijava.dao.entitiy.common.BaseEntity;
import com.example.asansorapijava.enumerate.ManageLevel;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Where(clause = "isdeleted='false'")
@SQLDelete(sql = "UPDATE apartments SET isdeleted = true WHERE id = ?")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column (name = "ManageLevel")
    private ManageLevel manageLevel;

}
