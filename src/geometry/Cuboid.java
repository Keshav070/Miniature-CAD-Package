package geometry;

import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import java.util.List;
//==============================================================================
public class Cuboid extends Threedimensional_Shape {

    private double q;
    private double w;
    private double e;
    private double[] x;

    //==========================================================================
    public Cuboid() {
        x = new double[3];
    }

    //==========================================================================
    @Override
    public double getVolume() {
        return (this.q * this.w * this.e);
    }

    //==========================================================================
    @Override
    public void setDimensions(List<Double> dimenList) {
        q = dimenList.get(0);
        w = dimenList.get(1);
        e = dimenList.get(2);
    }

    //==========================================================================
    @Override
    public double area() {
        return 0;
    }

    //==========================================================================
    @Override
    public String Coordinates() {
        return String.format("The x-coordinate of the Cuboid is:" + x[0] + ", the y coordinate of the Cuboid is:" + x[1]
                + " and the z coordinate of the Cuboid is :" + x[2]);
    }

    //==========================================================================
    @Override
    public String[] getLabels() {
        String[] n = {"length", "Width", "height", "x-coordinate", "y-coordinate", "z-coordinate"};
        return n;
    }

    //==========================================================================
    @Override
    public void setParameters(double[] parameters) {
        q = parameters[0];
        w = parameters[1];
        e = parameters[2];
        x[0] = parameters[3];
        x[1] = parameters[4];
        x[2] = parameters[5];
    }

    //==========================================================================
    @Override
    public double[] returnvalues() {
        double d[] = {q, w, e, x[0], x[1], x[2]};
        return d;
    }

    //==========================================================================
    @Override
    public Geometry draw() {
        Box m = new Box((float) q, (float) w, (float) e);
        Geometry cuboid = new Geometry("Shiny rock", m);
        cuboid.setLocalTranslation((float) x[0], (float) x[1], (float) x[2]);
        return cuboid;
    }

    //==========================================================================
    @Override
    public void dimension(double[] num) {
        x[0] = num[0];
        x[1] = num[1];
        x[2] = num[2];
    }

}
