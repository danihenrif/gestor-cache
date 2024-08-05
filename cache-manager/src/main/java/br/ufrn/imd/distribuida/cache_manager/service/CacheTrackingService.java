package br.ufrn.imd.distribuida.cache_manager.service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class CacheTrackingService {
    private final Set<String> accessedDashboards = new HashSet<>();
    private final Set<String> dashboardTokens = Collections.singleton("dashboards:130321860990926919292642443324377346416");

    private int accessCount = 0;
    private final Random random = new Random();

    /**
     * Tracks dashboard access for cache clearing.
     * To simulate a more realistic scenario where 3 dashboards might access the cache,
     * the behavior for the first two access attempts is randomized.
     */
    public boolean trackAccess(String token) {
        if (dashboardTokens.contains(token)) {
            accessCount++;

            if (accessCount == 3) {
                accessCount = 0;

                // Always add token on the third attempt
                accessedDashboards.add(token);
                return true;
            }
        }
        return false;
    }

    // Check if all dashboards have accessed
    public boolean allDashboardsAccessed() {
        return accessedDashboards.containsAll(dashboardTokens);
    }

    // Clear the cache
    public void clearCache() {
        accessedDashboards.clear();
    }
}
