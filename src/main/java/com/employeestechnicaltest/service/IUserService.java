package com.employeestechnicaltest.service;

import com.employeestechnicaltest.model.UserEntity;
import com.employeestechnicaltest.model.dto.AuthCreateUserRequestDTO;

import java.util.List;

public interface IUserService {
    UserEntity createUser(AuthCreateUserRequestDTO userEntity);

    List<UserEntity> getAllUsers();

    UserEntity getUserById(Long userId);

    boolean deleteUserById(Long userId);

}
