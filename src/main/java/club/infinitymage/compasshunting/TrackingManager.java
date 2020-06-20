package club.infinitymage.compasshunting;

import java.util.HashMap;
import java.util.Map;

public class TrackingManager {

    public static final Map<String, String> trackings = new HashMap<String, String>();

    public static Map<String, String> getTrackings() {
        return trackings;
    }

    public static void addTracking(String p, String target) {
        if (trackings.containsKey(p)) {
            trackings.remove(p);
        }
        trackings.put(p, target);
    }

    public static void removeTracking(String p) {
        if (trackings.containsKey(p)) {
            trackings.remove(p);
        }
    }

}

