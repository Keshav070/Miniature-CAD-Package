package geometry;

import java.awt.Graphics;
import java.util.List;
import com.jme3.scene.Geometry;


/**
 *
 * @author HP
 */
public abstract class Shape {

    public abstract double getVolume();

    public abstract double area();

    public abstract void dimension(double num[]);
    
    public abstract String Coordinates();
    
    public abstract String[] getLabels();
    
    public abstract void setParameters(double[] parameters);
    
    public abstract double[] returnvalues();

    public abstract void setDimensions(List<Double> dimenList);
    
    public abstract Geometry draw();


}
