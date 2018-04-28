package com.bencelovas.user;

import com.bencelovas.user.exception.EmailAlreadyTakenException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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
}
