package me.freelee.betterday.service;

import me.freelee.betterday.model.User;

public interface UserService {
    int selectCountByName(String name);

    int insertUser(User user);

    User selectUserByName(String username);

    User selectUserById(Integer userId);

    void updateUser(User user);
}
