package kg.itacademy.securitylesson.boot;

import kg.itacademy.securitylesson.entity.User;
import kg.itacademy.securitylesson.entity.UserRole;
import kg.itacademy.securitylesson.repository.UserRoleRepository;
import kg.itacademy.securitylesson.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartRunner implements CommandLineRunner {

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("1234");
        admin.setUserInfo("Администратор");
        admin.setIsActive(1L);
        userService.create(admin);
//        User admin = new User();
//        admin.setUsername("admin");
//        admin.setPassword("1234");
//        admin.setUserInfo("Администратор");
//        admin.setIsActive(1L);
//        userService.create(admin);
    }
}
