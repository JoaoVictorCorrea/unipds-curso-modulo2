package br.com.projectspringauth.service;

import br.com.projectspringauth.model.User;
import br.com.projectspringauth.security.MyToken;

public interface IUserService {
    User addUser(User user);
    User getByUsername(String username);
    MyToken userLogin(User user);
}
