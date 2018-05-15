package net.freshservers.support.services;

import net.freshservers.support.domain.Store;

import java.util.Set;

public interface StoreService {
    Set<Store> getAllByUserId(Long userId);
}
