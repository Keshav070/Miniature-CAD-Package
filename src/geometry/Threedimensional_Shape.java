package geometry;
public abstract class Threedimensional_Shape extends Shape {
    
    
    
    
        @Override
        public String toString() {

        return String.format("\n Volume of the three dimensional object" + getClass() + "is :"+ getVolume() + "\n" + Coordinates());
    }
   
}
