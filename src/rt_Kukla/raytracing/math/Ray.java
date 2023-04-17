package rt_Kukla.raytracing.math;

public class Ray {

    //położenie początkowe i kierunek promienia

    private Vector3 origin;
    private Vector3 direction;

    //konstruktor, wymusza znormalizowanie

    public  Ray(Vector3 origin, Vector3 direction) {
        this.origin = origin;

        if (direction.length() != 1) {
            direction = direction.normalize();
        }
        this.direction = direction;
    }

    //tworzy linię o podanej długości

    public Line asLine(float length) {
        return new Line(origin, origin.add(direction.multiply(length)));
    }

    //getter startu 

    public Vector3 getOrigin() {
        return origin;
    }

    //getter kierunku
    
    public Vector3 getDirection() {
        return direction;
    }
}
