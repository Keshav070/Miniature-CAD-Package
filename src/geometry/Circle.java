package geometry;

import java.util.List;
import com.jme3.scene.shape.Cylinder;
import com.jme3.scene.Geometry;

//==============================================================================

public class Circle extends Twodimensional_Shape {

    private double r;
    private double[] x;

//==============================================================================
    //Constructor
    public Circle() {
        x = new double[2];
    }

//==============================================================================
    
    @Override
    public double getVolume() {
        return 0;
    }
    
//==============================================================================
    //Calculating the area of the circle
    @Override
    public double area() {
        return Math.PI * this.r * this.r;
    }

//==============================================================================
    
    @Override
    public void setDimensions(List<Double> dimenList) {
        r = dimenList.get(0);
    }

//==============================================================================
    
    @Override
    public String[] getLabels() {
        String[] n = {"Radius", "x-coordinate", "y-coordinate"};
        return n;
    }

//==============================================================================
    
    @Override
    public void setParameters(double[] parameters) {
        r = parameters[0];
        x[0] = parameters[1];
        x[1] = parameters[2];
    }

//==============================================================================
    
    @Override
    public String Coordinates() {
        return String.format("The x-coordinate of the circle is:" + x[0] + " and the y coordinate of the circle is:" + x[1]);
    }

//==============================================================================
    
    @Override
    public double[] returnvalues() {
        double d[] = {r, x[0], x[1]};
        return d;
    }

//==============================================================================
    //Method to create the geometry
    @Override
    public com.jme3.scene.Geometry draw() {
        Cylinder c = new Cylinder(50, 50, (float) r, 0, true, false);
        Geometry circle = new Geometry("Shiny rock", c);
        circle.setLocalTranslation((float) x[0], (float) x[1], 0);
        return circle;
    }
//==============================================================================
    @Override
    public void dimension(double[] num) {
        x[0] = num[0];
        x[1] = num[1];
    }

}
