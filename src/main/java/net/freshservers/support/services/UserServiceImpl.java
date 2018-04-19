package net.freshservers.support.services;

import lombok.extern.slf4j.Slf4j;
import net.freshservers.support.domain.User;
import net.freshservers.support.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
