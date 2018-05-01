package com.bencelovas.user;

import com.bencelovas.user.exception.EmailAlreadyTakenException;
import com.bencelovas.user.exception.InvalidCredentialsException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long addUser(User user) throws EmailAlreadyTakenException {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        User userToPersist = new User(user.getName(), hashedPassword, user.getEmail());
        try {
            userRepository.save(userToPersist);
            return userToPersist.getId();
        } catch (DataIntegrityViolationException exception) {
            throw new EmailAlreadyTakenException("Email already in use");
        }
    }

    public User validateUser(User user) throws InvalidCredentialsException {
        User foundUser = userRepository.findUserByEmail(user.getEmail());
        if (foundUser != null && BCrypt.checkpw(user.getPassword(), foundUser.getPassword())) {
            return foundUser;
        } else {
            throw new InvalidCredentialsException("Email or password is invalid");
        }
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<User> deleteUser(Long id) {
        userRepository.delete(id);
        return userRepository.findAll();
    }
}
