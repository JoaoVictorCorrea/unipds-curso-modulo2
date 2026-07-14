package br.com.projectspringauth.service;

import br.com.projectspringauth.model.User;

public interface IUserService {
    User addUser(User user);
    User getByUsername(String username);
}
