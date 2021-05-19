package geometry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Geometry {

    public static void main(String[] args) throws IOException {


        File f = new File("myfile.txt");
        ArrayList<Shape> shapeList = null;
        shapeList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String s = null;
            Shape shape = null;

            while ((s = br.readLine()) != null) {
                if (s.length() == 0) {
                    continue;
                }
                String[] arr;
                arr = s.split("[\\s]+");
                int n = arr.length;

                String type = arr[0];
                try {
                    Class cl = Class.forName("geometry." + type);
                    shape = (Shape) cl.newInstance();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }

                List<Double> dimenList = new ArrayList<>();
                double[] num = new double[3];
                if (shape instanceof Twodimensional_Shape) {
                    for (int i = 1; i < n - 2; i++) {
                        dimenList.add(Double.valueOf(arr[i]));
                    }
                    num[0] = Double.valueOf(arr[n - 2]);
                    num[1] = Double.valueOf(arr[n - 1]);
                } else {
                    for (int i = 1; i < n - 3; i++) {
                        dimenList.add(Double.valueOf(arr[i]));
                    }
                    num[0] = Double.valueOf(arr[n - 3]);
                    num[1] = Double.valueOf(arr[n - 2]);
                    num[2] = Double.valueOf(arr[n - 1]);
                }
                shape.dimension(num);
                shape.setDimensions(dimenList);
                shapeList.add(shape);
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        try (PrintWriter wt = new PrintWriter(new File("myfile2.txt"))) {
            for (Shape shape1 : shapeList) {
                wt.println(shape1);
            }
            wt.flush();
        } catch (Exception e) {
        }

    }
}
