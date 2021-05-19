
package geometry;

import java.awt.Graphics2D;

public abstract class Twodimensional_Shape extends Shape{
    
    @Override
    public double area()
    {
        return 0.0;
    }

    @Override
    public String toString() {
        
        return String.format("\n Area of the Two-dimensional object" + getClass() +"is : " + area() + "\n" + Coordinates());
    }


    
}
