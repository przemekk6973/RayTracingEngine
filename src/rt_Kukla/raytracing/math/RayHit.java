package rt_Kukla.raytracing.math;

import rt_Kukla.raytracing.solids.Solid;

public class RayHit {

    //informacja o uderzeniu przez promień
    //przechowuje promień, obiekt, z którym się zderzył, pozycję, wektor normalny w miejscu zderzenia
    
    private Ray ray;
    private Solid hitSolid;
    private Vector3 hitPos;
    private Vector3 normal;

    public RayHit(Ray ray, Solid hitSolid, Vector3 hitPos) {
        this.ray = ray;
        this.hitSolid = hitSolid;
        this.hitPos = hitPos;
        this.normal = hitSolid.getNormalAt(hitPos);
    }

    public Ray getRay() {
        return ray;
    }

    public Solid getSolid() {
        return hitSolid;
    }

    public Vector3 getPosition() {
        return hitPos;
    }

    public Vector3 getNormal() {
        return normal;
    }
}
