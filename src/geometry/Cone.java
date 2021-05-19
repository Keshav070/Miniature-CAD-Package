package geometry;

import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import java.util.List;
import com.jme3.scene.shape.Dome;

//==============================================================================
public class Cone extends Threedimensional_Shape {

    private double r;
    private double h;
    private double[] x;

    //==========================================================================
    //Constructor
    public Cone() {
        x = new double[3];
    }

    //==========================================================================
    //Caluclating the volume of the cone
    @Override
    public double getVolume() {
        return ((Math.PI * this.r * this.r * this.h) / 3.0);
    }

    //==========================================================================
    @Override
    public void setDimensions(List<Double> dimenList) {
        r = dimenList.get(0);
        h = dimenList.get(1);
    }

    //==========================================================================
    @Override
    public double area() {
        return 0;
    }

    //==========================================================================
    @Override
    public String Coordinates() {
        return String.format("The x-coordinate of the cone is:" + x[0] + ", the y coordinate of the circle is:" + x[1]
                + "and the z coordinate of the cone is :" + x[2]);
    }

    //==========================================================================
    @Override
    public String[] getLabels() {
        String[] n = {"Radius", "Height", "x-coordinate", "y-coordinate", "z-coordinate"};
        return n;
    }

    //==========================================================================
    @Override
    public void setParameters(double[] parameters) {
        r = parameters[0];
        h = parameters[1];
        x[0] = parameters[2];
        x[1] = parameters[3];
        x[2] = parameters[4];
    }

    //==========================================================================
    @Override
    public double[] returnvalues() {
        double d[] = {r, h, x[0], x[1], x[2]};
        return d;
    }

    //==========================================================================
    //Method to create the geometry
    @Override
    public Geometry draw() {
        Dome d = new Dome(Vector3f.ZERO, 2, 150, (float) r);
        Geometry cone = new Geometry("Cone", d);
        cone.setLocalTranslation((float) x[0], (float) x[1], (float) x[2]);
        return cone;
    }

    //==========================================================================
    @Override
    public void dimension(double[] num) {
        x[0] = num[0];
        x[1] = num[1];
        x[2] = num[3];
    }

}
