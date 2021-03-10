package cn.kidand.projects.user.service;

import cn.kidand.projects.user.domain.User;
import cn.kidand.projects.user.repository.DatabaseUserRepository;
import cn.kidand.projects.user.repository.UserRepository;

import java.util.Collection;

/**
 * 用户服务
 */
public class UserServiceImpl implements UserService {

    private UserRepository userRepository = new DatabaseUserRepository();

    @Override
    public boolean register(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean deregister(User user) {
        return userRepository.deleteById(user.getId());
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
    public User queryUserByName(String name) {
        return userRepository.getByName(name);
    }

    @Override
    public Collection<User> queryAll() {
        Collection<User> userList = userRepository.getAll();
        if (userList != null){
            for (User user : userList) {
                user.setPassword("******");
            }
        }
        return userRepository.getAll();
    }
}
