package br.com.projectspringmvc.events.service;

import br.com.projectspringmvc.events.model.User;

import java.util.List;

public interface IUserService {
    User addUser(User user);
    User getUserById(Integer userId);
    User getUserByEmail(String email);
    List<User> getAllUsers();
}
