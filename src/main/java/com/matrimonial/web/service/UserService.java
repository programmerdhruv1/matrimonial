package com.matrimonial.web.service;

import com.matrimonial.web.entity.User;
import com.matrimonial.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public User save(User user){
        return userRepo.save(user);
    }

    public User findById(Integer id){
        return userRepo.findById(id).get();
    }

    public User findByEmail(String email){
        return userRepo.findByEmail(email);
    }

    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }

    public void changePassword(Integer id ,String password){
        User user = userRepo.findById(id).get();
        user.setPassword(encodePassword(password));
        userRepo.save(user);
    }

    private static String encodePassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

}
