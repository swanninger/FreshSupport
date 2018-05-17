package net.freshservers.support.services;

import lombok.extern.slf4j.Slf4j;
import net.freshservers.support.domain.Store;
import net.freshservers.support.domain.User;
import net.freshservers.support.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getByUserName(String userName) {
        return userRepository.getByUserName(userName);
    }

    @Override
    public Set<Store> getStores(User user) {
        return user.getStores();
    }
}
