package com.example.asansorapijava.dto;

import com.example.asansorapijava.enumerate.ManageLevel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "User Data Transfer Object")
public class UserDto {
    private Long id;

    private String name;

    private ManageLevel manageLevel;

    }

