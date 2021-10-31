package kg.itacademy.securitylesson.service;

import kg.itacademy.securitylesson.entity.User;
import kg.itacademy.securitylesson.model.UserAuthModel;

import java.util.List;

public interface UserService {
    User create(User user);

    List<User> getAll();

    User getCurrentUser();

    User getByUsername(String username);

    String getBasicAuthHeaderByAuthModel(UserAuthModel userAuthModel);
}
