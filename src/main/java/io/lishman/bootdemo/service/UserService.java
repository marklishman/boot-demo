package io.lishman.bootdemo.service;

import io.lishman.bootdemo.entity.User;
import io.lishman.bootdemo.exception.UserResourceNotFoundException;
import io.lishman.bootdemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        LOGGER.info("Get All Users");
        return userRepository.findAll();
    }

    public User getUserById(final Long id) {
        LOGGER.info("Get User {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new UserResourceNotFoundException(id));
    }

    public User createUser(final User user) {
        LOGGER.info("Get User {}", user.getUserName());
        return this.userRepository.save(user);
    }

    public User updateUser(final Long id, final User user) {
        LOGGER.info("Update User {}", id);
        final var userWithId = user.cloneWithNewId(id);
        return this.createUser(userWithId);
    }

    public void deleteUser(final Long id) {
        LOGGER.info("Delete User {}", id);
        userRepository.deleteById(id);
    }
}
