package geometry;

import com.jme3.scene.Geometry;
import java.util.List;

//==============================================================================
public class Sphere extends Threedimensional_Shape {

    private double r;
    private double[] x;

    //==========================================================================
    public Sphere() {
        x = new double[3];
    }

    //==========================================================================
    public Sphere(int i, int i0, float f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //==========================================================================
    @Override
    public double getVolume() {
        return ((Math.PI * this.r * this.r * this.r * 4) / 3.0);
    }

    //==========================================================================
    @Override
    public void setDimensions(List<Double> dimenList) {
        r = dimenList.get(0);
    }

    //==========================================================================
    @Override
    public double area() {
        return 0;
    }

    //==========================================================================
    @Override
    public String Coordinates() {
        return String.format("The x-coordinate of the Sphere is:" + x[0] + ", the y coordinate of the Sphere is:" + x[1]
                + " and the z coordinate of the Sphere is :" + x[2]);
    }

    //==========================================================================
    public String[] getLabels() {
        String[] n = {"Radius", "x-coordinate", "y-coordinate", "z-coordinate"};
        return n;
    }

    //==========================================================================
    @Override
    public void setParameters(double[] parameters) {
        r = parameters[0];
        x[0] = parameters[1];
        x[1] = parameters[2];
        x[2] = parameters[3];
    }

    //==========================================================================
    @Override
    public double[] returnvalues() {
        double d[] = {r, x[0], x[1], x[2]};
        return d;

    }

    //==========================================================================
    @Override
    public Geometry draw() {
        com.jme3.scene.shape.Sphere sphereMesh = new com.jme3.scene.shape.Sphere(50, 50, (float) r);
        Geometry sphere = new Geometry("Shiny rock", sphereMesh);
        sphere.setLocalTranslation((float) x[0], (float) x[1], (float) x[2]);
        return sphere;
    }

    //==========================================================================
    @Override
    public void dimension(double[] num) {
        x[0] = num[0];
        x[1] = num[1];
        x[2] = num[2];
    }
}
