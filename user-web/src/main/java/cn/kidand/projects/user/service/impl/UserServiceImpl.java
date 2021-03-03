package cn.kidand.projects.user.service.impl;

import cn.kidand.projects.user.domain.User;
import cn.kidand.projects.user.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public boolean register(User user) {
        return false;
    }

    @Override
    public boolean deregister(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User queryUserById(Long id) {
        return null;
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        return null;
    }

    @Override
    public List<User> queryAll() {
        return null;
    }
}
