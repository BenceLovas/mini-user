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
    private List<String> userNames = new ArrayList<String>() {{
        add("Lavinia Kendra");
        add("Silva Pouncey");
        add("Yolanda Pointer");
        add("Leo Hosier");
        add("France Defalco");
        add("Mattie Madera");
        add("Jamee Dipalma");
        add("Jeff Collinson");
        add("Laveta Lazos");
        add("Madge Deschaine");
        add("Steve Kilkenny");
        add("Roland Chalfant");
        add("Latanya Spiker");
        add("Bessie Wohlgemuth");
        add("Sharonda Savidge");
        add("Many Debusk");
        add("Milagro Luiz");
        add("Rosalba Carter");
        add("Joan Appleberry");
        add("Kellie Mcfaddin");
        add("Dusty Rouillard");
        add("Katrice Falk");
        add("Rebekah Dolby");
        add("Russ Barney");
        add("Christina Southward");
        add("Sharyl Boshart");
        add("Floy Streich");
        add("Chante Subia");
        add("Ute Creasy");
        add("Marti Wimsatt");
        add("Mitchel Jerome");
        add("Zoila Thieme");
        add("Marquerite Bassi");
        add("Leola Limbaugh");
        add("Rick Robledo");
        add("Kenny Jasik");
        add("Ozella Schauwecker");
        add("Kasi London");
        add("Marlene Watters");
        add("Saul Lacoste");
        add("Marcelene Shield");
        add("Christen Bise");
        add("Samatha Campisi");
        add("Rolando Parks");
        add("Idella Dishon");
        add("Patience Munos");
        add("Christopher Villalpando");
        add("Marlys Geissler");
        add("Warner Lybarger");
        add("Zena Klug");
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
            System.out.println("Admin email already in use");
        }
    }

    @PostConstruct
    public void populateDatabase() {
        for (String userName : userNames) {
            String hashedPassword = BCrypt.hashpw("password", BCrypt.gensalt());
            String email = userName.split("\\s+")[0].toLowerCase() + "@gmail.com";
            userRepository.save(new User(userName, hashedPassword, email));
        }
    }

}
