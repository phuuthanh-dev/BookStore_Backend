package vn.bookstore.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bookstore.backend.model.User;
import vn.bookstore.backend.repository.UserRepository;


@Service
public class UserService  {
    @Autowired
    private  UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

}
