package net.freshservers.support.services;

import net.freshservers.support.domain.Store;
import net.freshservers.support.domain.User;
import net.freshservers.support.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

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

    @Override
    public Set<Store> getStores(User user) {
       return user.getStores();
    }
}
