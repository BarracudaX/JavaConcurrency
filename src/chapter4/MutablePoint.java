package chapter4;

public class MutablePoint {

    public int x,y;

    public MutablePoint(){
        this.x = 0;
        this.y = 0;
    }

    public MutablePoint(MutablePoint point) {
        this.x = point.x;
        this.y = point.y;
    }
}
