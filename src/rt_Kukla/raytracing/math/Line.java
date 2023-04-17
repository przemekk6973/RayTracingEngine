package rt_Kukla.raytracing.math;

public class Line {

    //punkty A i B reprezentujące linię

    public Vector3 pointA;
    public Vector3 pointB;

    //konstruktor linii 

    public  Line(Vector3 pointA, Vector3 pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }

    //zwraca ray znormalizowany, jednostkowy

    public Ray asRay() {
        return new Ray(pointA, pointB.subtract(pointA).normalize());
    }
}
