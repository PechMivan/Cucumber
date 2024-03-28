package epam.integrationtests.cucumber;

import java.util.HashMap;
import java.util.Map;

//In-Memory Tracker to track needed information for following tests.
@SuppressWarnings("unused")
public class DataTracker {

    private static final Map<String, String> storage = new HashMap<>();

    public static void save(String key, String value) {
        storage.put(key, value);
    }

    public static String get(String key) {
        return storage.get(key);
    }

    public static void remove(String key) {
        storage.remove(key);
    }

    public static boolean containsKey(String key) {
        return storage.containsKey(key);
    }

    public static void clear() {
        storage.clear();
    }

}
