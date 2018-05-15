package net.freshservers.support.services;

import net.freshservers.support.domain.Store;
import net.freshservers.support.domain.User;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    Optional<User> getByUserName(String userName);

    Set<Store> getStores(User user);
}
