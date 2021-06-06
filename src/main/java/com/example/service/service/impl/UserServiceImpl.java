package com.example.service.service.impl;

import com.example.service.core.entity.User;
import com.example.service.core.repository.UserRepository;
import com.example.service.dto.UserDTO;
import com.example.service.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO getUserById(Long id) {
        final User user = userRepository.getById(id);
        return mapToDTO(user);
    }

    private UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }
}
