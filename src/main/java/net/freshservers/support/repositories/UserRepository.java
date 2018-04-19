package net.freshservers.support.repositories;

import net.freshservers.support.domain.User;

import java.util.Optional;

public interface UserRepository extends ReadOnlyRepository<User, Long> {
    Optional<User> findByUserName(String username);
}
