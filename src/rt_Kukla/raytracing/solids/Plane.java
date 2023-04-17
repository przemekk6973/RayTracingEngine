package rt_Kukla.raytracing.solids;

import rt_Kukla.raytracing.math.Ray;
import rt_Kukla.raytracing.math.Vector3;
import rt_Kukla.raytracing.pixeldata.Color;

public class Plane extends Solid {
    private boolean checkerPattern;

    public Plane(float height, Color color, boolean checkerPattern, float reflectivity, float emission) {
        super(new Vector3(0, height, 0), color, reflectivity, emission);
        this.checkerPattern = checkerPattern;
    }

    @Override
    public Vector3 calculateIntersection(Ray ray) {
        float t = -(ray.getOrigin().getY()-position.getY()) / ray.getDirection().getY();
        if (t > 0 && Float.isFinite(t))
        {
            return ray.getOrigin().add(ray.getDirection().multiply(t));
        }

        return null;
    }

    @Override
    public Vector3 getNormalAt(Vector3 point) {
        return new Vector3(0, 1, 0);
    }

    @Override
    public Color getTextureColor(Vector3 point) {
        if (checkerPattern) {
            // w pierwszej lub trzeciej ćwiartce szachownicye
            if (((point.getX() > 0) & (point.getZ() > 0)) || ((point.getX() < 0) & (point.getZ() < 0))) {
                if ((int)point.getX() % 2 == 0 ^ (int)point.getZ() % 2 != 0) {
                    return Color.GRAY;
                } else {
                    return Color.DARK_GRAY;
                }
            } else {
                // w drugiej lub czwartej ćwiartce szachownicye
                if ((int)point.getX() % 2 == 0 ^ (int)point.getZ() % 2 != 0) {
                    return Color.DARK_GRAY;
                } else {
                    return Color.GRAY;
                }
            }
        } else {
            return getColor();
        }
    }

}
