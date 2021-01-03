package com.luxoft.rcalculator.service;

import com.luxoft.rcalculator.dao.RoleRepository;
import com.luxoft.rcalculator.dao.UserRepository;
import com.luxoft.rcalculator.model.Role;
import com.luxoft.rcalculator.model.User;
import com.luxoft.rcalculator.model.dto.RetirementResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    //async test method
    @Override
    //@Async
    public RetirementResultDTO calculateRetirementYearsLeft(int userAge, int userRetirementYear) {
        //sleep(2);
        RetirementResultDTO retirementResultDTO = new RetirementResultDTO();
        LocalDate localDate = LocalDate.now();
        int presentYear = localDate.getYear();
        int ageDiff = userRetirementYear - userAge;
        if(ageDiff > 0) {
            int canRetireYear = presentYear + ageDiff;
            retirementResultDTO.setUserAge(userAge);
            retirementResultDTO.setPresentYear(presentYear);
            retirementResultDTO.setCanRetireYear(canRetireYear);
            System.out.println("You have" + ageDiff + "years left until you can retire.");
            System.out.println("It's " + presentYear + ". So you can retire in " + canRetireYear + ".");
            return retirementResultDTO;
        } else {
            retirementResultDTO.setUserAge(userAge);
            retirementResultDTO.setPresentYear(presentYear);
            retirementResultDTO.setCanRetireYear(presentYear);
            System.out.println("It's " + presentYear + ".");
            System.out.println("You can retire this year.");
            return retirementResultDTO;
        }
    }

    //for async test method
    private void sleep(int count) {
        try {
            TimeUnit.SECONDS.sleep(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
