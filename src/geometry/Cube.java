package geometry;

import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import java.util.List;

//==============================================================================
public class Cube extends Threedimensional_Shape {

    private double r;
    private double[] x;

    //==========================================================================
    public Cube() {
        x = new double[3];
    }

    //==========================================================================
    @Override
    public double getVolume() {
        return (this.r * this.r * this.r);
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
        return String.format("The x-coordinate of the cube is:" + x[0] + ", the y coordinate of the cube is:" + x[1]
                + " and the z coordinate of the cube is :" + x[2]);
    }

    //==========================================================================
    @Override
    public String[] getLabels() {
        String[] n = {"Length", "x-coordinate", "y-coordinate", "z-coordinate"};
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
    //Method to create the geometry
    @Override
    public Geometry draw() {
        Box b = new Box((float) r, (float) r, (float) r);
        Geometry box = new Geometry("Shiny rock", b);
        box.setLocalTranslation((float) x[0], (float) x[1], (float) x[2]);
        return box;
    }

    //==========================================================================
    @Override
    public void dimension(double[] num) {
        x[0] = num[0];
        x[1] = num[1];
        x[2] = num[2];
    }
}
