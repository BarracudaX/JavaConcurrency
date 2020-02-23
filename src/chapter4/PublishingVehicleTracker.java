package chapter4;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PublishingVehicleTracker {


    private final Map<String,SafePoint> unmodifiableMap;

    private final Map<String,SafePoint> locations;


    public PublishingVehicleTracker(Map<String, SafePoint> points) {
        this.locations = new ConcurrentHashMap<>(points);
        this.unmodifiableMap = Collections.unmodifiableMap(this.locations);
    }

    public Map<String,SafePoint> getLocations(){
        return unmodifiableMap;
    }

    public  SafePoint getLocation(String id) {
        return this.locations.get(id);
    }

    public void setLocation(String id, int x, int y) {

        if ( !locations.containsKey(id)) {
            throw new IllegalArgumentException("Vehicle not found");
        }

        locations.get(id).set(x,y);
    }

}
