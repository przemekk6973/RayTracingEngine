package rt_Kukla.raytracing.solids;

import rt_Kukla.raytracing.math.Ray;
import rt_Kukla.raytracing.math.Vector3;
import rt_Kukla.raytracing.pixeldata.Color;

public abstract class Solid {
    protected Vector3 position;
    protected Color color;
    protected float reflectivity;
    protected float emission;

    public Solid(Vector3 position, Color color, float reflectivity, float emission) {
        this.position = position;
        this.color = color;
        this.reflectivity = reflectivity;
        this.emission = emission;
    }

    public abstract Vector3 calculateIntersection(Ray ray);
    public abstract Vector3 getNormalAt(Vector3 point);

    public Vector3 getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public Color getTextureColor(Vector3 point) {
        return getColor();
    }

    public float getReflectivity() {
        return reflectivity;
    }

    public float getEmission() {
        return emission;
    }
}
