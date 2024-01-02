package com.matrimonial.web.controller;

import com.matrimonial.web.entity.User;
import com.matrimonial.web.repository.UserRepository;
import com.matrimonial.web.security.AuthenticatedUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class BaseController {

    @Autowired
    private UserRepository userRepo;

    public String getIpAddress(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    public User getLoggedInUser() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return userRepo.findByUsername(userName);
    }

    public void authanticateUser(User users, HttpServletRequest request) {

        AuthenticatedUser auth = new AuthenticatedUser(users);
        request.getSession();
        Authentication authentication = new UsernamePasswordAuthenticationToken(auth, null, auth.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
