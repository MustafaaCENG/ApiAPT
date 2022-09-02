package com.example.asansorapijava.service.impl;

import com.example.asansorapijava.dao.UserRepository;
import com.example.asansorapijava.dao.entitiy.User;
import com.example.asansorapijava.dto.ApartmentDto;
import com.example.asansorapijava.dto.UserDto;
import com.example.asansorapijava.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.transaction.Transactional;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto save(UserDto userDto) {
        User newUser = modelMapper.map(userDto, User.class);
        newUser = userRepository.save(newUser);
        userDto.setId(newUser.getId());
        return userDto;
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.getOne(id);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public Boolean hasAccess(User user) {
        Boolean hasAccess = false;
        if (user.getManageLevel().ordinal() == 1) {
            return true;
        }
        return hasAccess;
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> data = userRepository.findAll();
        return Arrays.asList(modelMapper.map(data, UserDto[].class));
    }

    @Override
    public Boolean delete(Long id, User user) throws AccessDeniedException {
        if (hasAccess(user)) {
            userRepository.deleteById(id);
            return true;
        } else throw new AccessDeniedException("You can't delete user");
    }

    @Transactional
    public UserDto update(Long id, UserDto userDto, User user) throws AccessDeniedException {
        if (hasAccess(user)) {
            User userDb = userRepository.getOne(id);
            if (userDb == null) {
                throw new IllegalArgumentException("Can't find");
            }
            userDb.setManageLevel(userDto.getManageLevel());
            userDb.setName(userDto.getName());
            userRepository.save(userDb);
            return modelMapper.map(userDb, UserDto.class);
        } else throw new AccessDeniedException("You can't delete user");
    }
}