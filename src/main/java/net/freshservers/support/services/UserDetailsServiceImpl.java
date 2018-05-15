package net.freshservers.support.services;

import net.freshservers.support.domain.User;
import net.freshservers.support.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserService userService;

    public UserDetailsServiceImpl(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.getByUserName(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailsImpl(user.get());
    }
}