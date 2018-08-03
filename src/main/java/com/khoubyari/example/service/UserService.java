package com.khoubyari.example.service;

import com.khoubyari.example.api.rest.dtos.UserDTO;
import com.khoubyari.example.dao.jpa.UserRepository;
import com.khoubyari.example.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/*
 * Sample service to demonstrate what the API would use to get things done
 */
@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    CounterService counterService;
    @Autowired
    GaugeService gaugeService;
    @Autowired
    private UserRepository userRepository;

    public UserService() {
    }

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User getUser(long id) {
        return userRepository.findOne(id);
    }

    public void updateHotel(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    //http://goo.gl/7fxvVf
    public Page<User> getAllUser(Integer page, Integer size) {
        Page pageOfUsers = userRepository.findAll(new PageRequest(page, size));
        // example of adding to the /metrics
        if (size > 50) {
            counterService.increment("Khoubyari.UserService.getAll.largePayload");
        }
        return pageOfUsers;
    }
}
