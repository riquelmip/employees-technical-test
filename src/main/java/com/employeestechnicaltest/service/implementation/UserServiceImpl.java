package com.employeestechnicaltest.service.implementation;

import com.employeestechnicaltest.model.RoleEntity;
import com.employeestechnicaltest.model.UserEntity;
import com.employeestechnicaltest.model.dto.AuthCreateUserRequestDTO;
import com.employeestechnicaltest.repository.RoleRepository;
import com.employeestechnicaltest.repository.UserRepository;
import com.employeestechnicaltest.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserEntity createUser(AuthCreateUserRequestDTO authCreateUserRequestDTO) {
        String username = authCreateUserRequestDTO.username();
        String password = authCreateUserRequestDTO.password();
        List<String> rolesRequest = authCreateUserRequestDTO.roles().list();

        Set<RoleEntity> roleEntityList = roleRepository.findByRoleNameIn(rolesRequest).stream().collect(Collectors.toSet());

        if (roleEntityList.isEmpty()) {
            throw new IllegalArgumentException("The roles specified does not exist.");
        }

        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles(roleEntityList)
                .isEnable(true)
                .accountNonLocked(true)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .build();

        return userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        users.forEach(user -> user.setPassword(null));
        return users;
    }

    @Override
    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public boolean deleteUserById(Long userId) {
        boolean isDeleted = false;
        if (userRepository.existsById(userId)) {
            UserEntity userEntity = userRepository.findById(userId).get();
            userEntity.getRoles().clear();
            userRepository.save(userEntity);
            userRepository.deleteById(userId);
            isDeleted = true;
        }
        return isDeleted;
    }


}
