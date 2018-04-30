package com.bencelovas.configuration;

import com.bencelovas.user.User;
import com.bencelovas.user.UserRepository;
import com.bencelovas.user.UserRole;
import com.bencelovas.user.UserService;
import com.bencelovas.user.exception.EmailAlreadyTakenException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializerBean {

    private UserRepository userRepository;

    @Autowired
    public InitializerBean(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void addAdminToDatabase() {
        String hashedPassword = BCrypt.hashpw("admin123", BCrypt.gensalt());
        User admin = new User("admin", hashedPassword, "admin@admin.hu", UserRole.ADMIN);
        try {
            userRepository.save(admin);
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Admin email was already taken");
        }
    }

    @PostConstruct
    public void populateDatabase() {
        //TODO
    }

}
