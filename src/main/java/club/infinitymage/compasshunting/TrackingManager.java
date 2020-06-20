package club.infinitymage.compasshunting;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TrackingManager {

    public static final Map<UUID, UUID> trackings = new HashMap<UUID, UUID>();

    public static Map<UUID, UUID> getTrackings() {
        return trackings;
    }

    public static void addTracking(UUID p, UUID target) {
        if (trackings.containsKey(p)) {
            trackings.remove(p);
        }
        trackings.put(p, target);
    }

}

