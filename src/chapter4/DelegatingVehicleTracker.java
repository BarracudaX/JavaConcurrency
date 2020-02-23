package chapter4;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DelegatingVehicleTracker {

    private final Map<String,Point> unmodifiableMap;
    private final Map<String,Point> locations;


    public DelegatingVehicleTracker(Map<String, Point> points) {
        this.locations = new ConcurrentHashMap<>(points);
        this.unmodifiableMap = Collections.unmodifiableMap(this.locations);
    }

    public Map<String,Point> getLocations(){
        return unmodifiableMap;
    }

    public  Point getLocation(String id) {
        return this.locations.get(id);
    }

    public void setLocation(String id, int x, int y) {

        if ( locations.replace(id,new Point(x,y)) == null ) {
            throw new IllegalArgumentException("Vehicle not found");
        }
    }

}
