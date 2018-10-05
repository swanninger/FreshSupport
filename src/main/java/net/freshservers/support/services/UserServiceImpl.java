package net.freshservers.support.services;

import lombok.extern.slf4j.Slf4j;
import net.freshservers.support.domain.Store;
import net.freshservers.support.domain.User;
import net.freshservers.support.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

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

    /**
     * Returns a set of all locations the current user has access to
     * @param user
     * @return
     */
    @Override
    public Set<String> getStoresNames(User user) {
        Set<String> stores = new TreeSet<>();
        for(Store s : user.getStores()){
            String displayName = s.getDisplay_name();
            if (displayName != null && !displayName.isEmpty()){
                stores.add(displayName);
            }else{
                stores.add(s.getName());
            }
        }
        return stores;
    }
    /**
     * Returns a set of all concepts the current user has access to
     * @param user
     * @return
     */
    @Override
    public Set<String> getAllConceptCodes(User user){
        Set<String> concepts = new TreeSet<>();
        for (Store s: user.getStores()) {
            concepts.add(s.getConcept().getName());
        }
        return concepts;
    }
}
