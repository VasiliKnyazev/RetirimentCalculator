package com.luxoft.rcalculator.service;

import com.luxoft.rcalculator.dao.RoleRepository;
import com.luxoft.rcalculator.dao.UserRepository;
import com.luxoft.rcalculator.model.Role;
import com.luxoft.rcalculator.model.User;
import com.luxoft.rcalculator.model.dto.RetirementResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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
    public RetirementResultDTO calculateUserCanRetirementYear(RetirementResultDTO retirementResultDTO) {
        int presentYear = LocalDate.now().getYear();
        int ageDiff = retirementResultDTO.getUserRetirementAge() - retirementResultDTO.getUserAge();
        retirementResultDTO.setPresentYear(presentYear);
        if(ageDiff > 0) {
            int canRetireYear = presentYear + ageDiff;
            retirementResultDTO.setUserCanRetireYear(canRetireYear);
            retirementResultDTO.setCanRetireMessage("You have " + ageDiff + " years left until you can retire. It's " + presentYear + ". So you can retire in " + canRetireYear + ".");
        } else {
            retirementResultDTO.setUserCanRetireYear(presentYear);
            retirementResultDTO.setCanRetireMessage("It's " + presentYear + ". You can retire this year.");
        }
        System.out.println(retirementResultDTO.getCanRetireMessage());
        return retirementResultDTO;
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
        userRepository.save(user);
    }
}
