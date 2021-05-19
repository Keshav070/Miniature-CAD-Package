package geometry;

import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Quad;
import java.util.List;

//==============================================================================
public class Square extends Twodimensional_Shape {

    private double a;
    private double[] x;

    //==========================================================================
    public Square() {
        x = new double[2];

    }

    //==========================================================================
    @Override
    public double area() {
        return this.a * this.a;
    }

    //==========================================================================
    @Override
    public void setDimensions(List<Double> dimenList) {
        a = dimenList.get(0);

    }

    //==========================================================================
    @Override
    public double getVolume() {
        return 0;
    }

    //==========================================================================
    @Override
    public String Coordinates() {
        return String.format("The x-coordinate of the Square is:" + x[0] + " and the y coordinate of the Square is:" + x[1]);
    }

    //==========================================================================
    public String[] getLabels() {
        String[] n = {"Length", "x-coordinate", "y-coordinate"};
        return n;
    }

    //==========================================================================
    @Override
    public void setParameters(double[] parameters) {
        a = parameters[0];
        x[0] = parameters[1];
        x[1] = parameters[2];
    }

    //==========================================================================
    @Override
    public double[] returnvalues() {
        double d[] = {a, x[0], x[1]};
        return d;
    }

    //==========================================================================
    @Override
    public Geometry draw() {
        Quad m = new Quad((float) a, (float) a);
        Geometry square = new Geometry("Shiny rock", m);
        square.setLocalTranslation((float) x[0], (float) x[1], 0);
        return square;
    }

    //==========================================================================
    @Override
    public void dimension(double[] num) {
        x[0] = num[0];
        x[1] = num[1];
    }
}
