package geometry;

import com.jme3.scene.Geometry;
import java.util.List;
//==============================================================================
public class Cylinder extends Threedimensional_Shape {

    private double r;
    private double h;
    private double[] x;

    //==========================================================================
    public Cylinder() {
        x = new double[3];
    }

    //==========================================================================
    @Override
    public double getVolume() {
        return (Math.PI * this.r * this.r * this.h);
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
        return String.format("The x-coordinate of the Cylinder is:" + x[0] + ", the y coordinate of the Cylinder is:" + x[1]
                + " and the z coordinate of the Cylinder is :" + x[2]);
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
    @Override
    public Geometry draw() {
        com.jme3.scene.shape.Cylinder c = new com.jme3.scene.shape.Cylinder(50, 50, (float) r, (float) h, true, false);
        Geometry cylinder = new Geometry("Shiny rock", c);
        cylinder.setLocalTranslation((float) x[0], (float) x[1], (float) x[2]);
        return cylinder;
    }

    //==========================================================================
    @Override
    public void dimension(double[] num) {
        x[0] = num[0];
        x[1] = num[1];
        x[2] = num[2];
    }

}
