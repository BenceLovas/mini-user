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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class InitializerBean {

    private UserRepository userRepository;
    private List<User> users = new ArrayList<User>() {{
        add(new User("Pedro Michalek", BCrypt.hashpw("password", BCrypt.gensalt()), "pedro@gmail.com"));
        add(new User("Michelle Runkle", BCrypt.hashpw("password", BCrypt.gensalt()), "michelle@gmail.com"));
        add(new User("Eleanor Canedy", BCrypt.hashpw("password", BCrypt.gensalt()), "eleanor@gmail.com"));
        add(new User("Georgia Likens", BCrypt.hashpw("password", BCrypt.gensalt()), "georgia@gmail.com"));
        add(new User("Phil Jerman", BCrypt.hashpw("password", BCrypt.gensalt()), "phil@gmail.com"));
        add(new User("Brigitte Hooten", BCrypt.hashpw("password", BCrypt.gensalt()), "brigitte@gmail.com"));
        add(new User("Ricarda Chrisman", BCrypt.hashpw("password", BCrypt.gensalt()), "ricarda@gmail.com"));
        add(new User("Keeley Drakeford", BCrypt.hashpw("password", BCrypt.gensalt()), "keeley@gmail.com"));
        add(new User("Drew Baham", BCrypt.hashpw("password", BCrypt.gensalt()), "drew@gmail.com"));
        add(new User("Rachel Dimond", BCrypt.hashpw("password", BCrypt.gensalt()), "rachel@gmail.com"));
        add(new User("Quinton Mccrea", BCrypt.hashpw("password", BCrypt.gensalt()), "quinton@gmail.com"));
        add(new User("Sharen Risser", BCrypt.hashpw("password", BCrypt.gensalt()), "sharen@gmail.com"));
        add(new User("Theresia Hedgepeth", BCrypt.hashpw("password", BCrypt.gensalt()), "theresia@gmail.com"));
        add(new User("Monnie Darville", BCrypt.hashpw("password", BCrypt.gensalt()), "monnie@gmail.com"));
        add(new User("Dino Ocampo", BCrypt.hashpw("password", BCrypt.gensalt()), "dino@gmail.com"));
        add(new User("Tina Pegram", BCrypt.hashpw("password", BCrypt.gensalt()), "tina@gmail.com"));
        add(new User("Florance Abernethy", BCrypt.hashpw("password", BCrypt.gensalt()), "florance@gmail.com"));
        add(new User("Elly Joe", BCrypt.hashpw("password", BCrypt.gensalt()), "elly@gmail.com"));
        add(new User("Rhiannon Messerly", BCrypt.hashpw("password", BCrypt.gensalt()), "rhiannon@gmail.com"));
        add(new User("Sheryll Overbey", BCrypt.hashpw("password", BCrypt.gensalt()), "sheryll@gmail.com"));
    }};

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
        userRepository.save(users);
    }

}
