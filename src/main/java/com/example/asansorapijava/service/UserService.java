package com.example.asansorapijava.service;

import com.example.asansorapijava.dao.entitiy.User;
import com.example.asansorapijava.dto.ApartmentDto;
import com.example.asansorapijava.dto.UserDto;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface UserService {

    UserDto save(UserDto userDto);

    UserDto getById(Long id);

    Boolean hasAccess(User user);
    List<UserDto> getUsers();

    Boolean delete(Long id,User user) throws AccessDeniedException;

    UserDto update(Long id, UserDto userDto,User user) throws AccessDeniedException;
}
