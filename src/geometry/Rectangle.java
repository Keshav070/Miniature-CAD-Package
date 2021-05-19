package geometry;

import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Quad;
import java.util.List;

//==============================================================================
public class Rectangle extends Twodimensional_Shape {

    private double l;
    private double b;
    private double[] x;

    //==========================================================================
    public Rectangle() {
        x = new double[2];
    }

    //==========================================================================
    @Override
    public double area() {
        return this.l * this.b;
    }

    //==========================================================================
    @Override
    public void setDimensions(List<Double> dimenList) {
        l = dimenList.get(0);
        b = dimenList.get(1);
    }

    //==========================================================================
    @Override
    public double getVolume() {
        return 0;
    }

    //==========================================================================
    @Override
    public String Coordinates() {
        return String.format("The x-coordinate of the Rectangle is:" + x[0] + " and the y coordinate of the Rectangle is:" + x[1]);
    }

    //==========================================================================
    @Override
    public String[] getLabels() {
        String[] n = {"Length", "Width", "x-coordinate", "y-coordinate"};
        return n;
    }

    //==========================================================================
    @Override
    public void setParameters(double[] parameters) {
        l = parameters[0];
        b = parameters[1];
        x[0] = parameters[2];
        x[1] = parameters[3];
    }

    //==========================================================================
    @Override
    public double[] returnvalues() {
        double d[] = {l, b, x[0], x[1]};
        return d;
    }

    //==========================================================================
    @Override
    public Geometry draw() {
        Quad m = new Quad((float) l, (float) b);
        Geometry rectangle = new Geometry("Shiny rock", m);
        rectangle.setLocalTranslation((float) x[0], (float) x[1], 0);
        return rectangle;
    }

    //==========================================================================
    @Override
    public void dimension(double[] num) {
        x[0] = num[0];
        x[1] = num[1];
    }

}
