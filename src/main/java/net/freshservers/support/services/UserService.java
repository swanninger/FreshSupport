package net.freshservers.support.services;

import net.freshservers.support.domain.User;
import java.util.Optional;

public interface UserService {

    Optional<User> getByUserName(String userName);
}
