package com.raryanda.hc.service;

import com.raryanda.hc.model.User;
import com.raryanda.hc.dto.UserDto;
import com.raryanda.hc.dto.converter.UserDtoConverter;
import com.raryanda.hc.exception.DataNotFoundException;
import com.raryanda.hc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    UserDtoConverter converter;

    @Override
    public UserDto getUserDetail(Long userId) {
        User user = repository.findById(userId).orElseThrow(() -> new DataNotFoundException("User", "id", userId));

        return converter.convertToDTO(user);
    }
}
