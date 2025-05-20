package com.xp.springboot07.service;

import com.xp.springboot07.entity.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User create(User user);
    User update(User user);
    boolean delete(Long id);
} 