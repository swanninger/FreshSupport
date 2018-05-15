package net.freshservers.support.services;

import net.freshservers.support.domain.Store;
import net.freshservers.support.domain.User;
import net.freshservers.support.repositories.StoreRepository;
import net.freshservers.support.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    public StoreServiceImpl(StoreRepository storeRepository, UserRepository userRepository) {
        this.storeRepository = storeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Set<Store> getAllByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (!userOptional.isPresent()){
            // TODO: 5/15/2018 error handling
        }

        User user = userOptional.get();



        return null;
    }
}
