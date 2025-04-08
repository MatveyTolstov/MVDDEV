package com.example.mvd_dev.service;

import com.example.mvd_dev.model.UserEntity;
import com.example.mvd_dev.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private UserDetailsRepository userDetailsRepository;

    @Autowired
    public UserServiceImpl(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userDetailsRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с именем " + login + " не найден"));
    }

    public boolean existsByUsername(String login) {
        UserEntity user = userDetailsRepository.findByLogin(login).orElse(null);
        return user != null;
    }
}
