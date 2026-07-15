package br.com.projectspringauth.service;

import br.com.projectspringauth.model.User;
import br.com.projectspringauth.repo.UserRepository;
import br.com.projectspringauth.security.MyToken;
import br.com.projectspringauth.security.TokenUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    @Override
    public MyToken userLogin(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            return TokenUtils.encode(existingUser);
        }

        throw new RuntimeException("Unauthorized user");
    }
}
