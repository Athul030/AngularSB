package com.example.backendspringang.services;

import com.example.backendspringang.entity.User;
import com.example.backendspringang.entity.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {

    User createUser(User user, Set<UserRole> userRoles) throws Exception;

    User getUser(String username);
    List<User> getAllUsers();
    User getUser(long id);
    void deleteUser(String userName);

    void updateImage(User user, String fileName);

    User updateUser(User user);


}
