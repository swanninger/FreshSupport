package net.freshservers.support.services;

import java.util.Set;

public interface AppService {
    Set<String> getAndroidApps();

    Set<String> getIpadApps();
}
