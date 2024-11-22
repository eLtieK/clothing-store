package com.clothing_store.service;

import com.clothing_store.dto.request.UserRequest;
import com.clothing_store.entity.User;
import com.clothing_store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }
    public User createUser(UserRequest request) {
        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());

        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String userID) {
        return userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(String userID, UserRequest request) {
        User user = this.getUser(userID);

        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());

        return userRepository.save(user);
    }

    public void updateUser(String userID, User updatedUser) {
        User user = this.getUser(userID);

        user.setPassword(updatedUser.getPassword());
        user.setEmail(updatedUser.getEmail());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setPhoneNumber(updatedUser.getPhoneNumber());

        userRepository.save(user);
    }

    public void deleteUser(String userID) {
        userRepository.deleteById(userID);
    }
}
