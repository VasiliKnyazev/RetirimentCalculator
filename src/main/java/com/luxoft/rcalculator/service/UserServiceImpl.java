package com.luxoft.rcalculator.service;

import com.luxoft.rcalculator.dao.RoleRepository;
import com.luxoft.rcalculator.dao.UserRepository;
import com.luxoft.rcalculator.model.Role;
import com.luxoft.rcalculator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserRepository setUserRepository(UserRepository userRepository){
        return this.userRepository = userRepository;
    }

    @Autowired
    public RoleRepository setUserRepository(RoleRepository roleRepository){
        return this.roleRepository = roleRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("The user with id = {" + id + "} does not exist"));
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public Role getRole(String role) {
        return roleRepository.getByRole(role);
    }

    @Override
    public void deleteById(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public void edit(User user) {
        if(user.getPassword().equals(findById(user.getId()).getPassword())) {
            userRepository.save(user);
        } else {
            userRepository.save(user);
        }
    }
}
