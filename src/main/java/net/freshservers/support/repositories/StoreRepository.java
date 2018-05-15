package net.freshservers.support.repositories;

import net.freshservers.support.domain.Store;
import net.freshservers.support.domain.User;

import java.util.Set;

public interface StoreRepository extends ReadOnlyRepository<Store, Long> {
    Set<Store> getStoresByUsersIn(User user);
}
