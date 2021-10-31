package kg.itacademy.securitylesson.service.impl;

import kg.itacademy.securitylesson.entity.User;
import kg.itacademy.securitylesson.entity.UserRole;
import kg.itacademy.securitylesson.model.UserAuthModel;
import kg.itacademy.securitylesson.repository.UserRepository;
import kg.itacademy.securitylesson.repository.UserRoleRepository;
import kg.itacademy.securitylesson.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        System.out.println(encodedPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);

        UserRole userRole = new UserRole();
        userRole.setRoleName("ROLE_USER");
        userRole.setUser(user);
        userRoleRepository.save(userRole);

        return user;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public String getAuthorizationToken(UserAuthModel userAuthModel) {
        User user = userRepository.findByUsername(userAuthModel.getName()).orElseThrow( () -> new IllegalArgumentException("Неверный логин или пароль"));

        boolean isPasswordMatches = passwordEncoder.matches(userAuthModel.getPassword(), user.getPassword());
        if (!isPasswordMatches)
            throw new IllegalArgumentException("Неверный логин или пароль");

        String usernamePasswordPair = userAuthModel.getName() + ":" + userAuthModel.getPassword();
        return "Basic " + new String(Base64.getEncoder().encode(usernamePasswordPair.getBytes()));
    }
}
