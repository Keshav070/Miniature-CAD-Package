/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import static com.jme3.math.ColorRGBA.Green;
import static com.jme3.math.ColorRGBA.Red;
import static com.jme3.math.ColorRGBA.Yellow;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer;
import com.jme3.scene.control.BillboardControl;
import com.jme3.scene.debug.Arrow;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;
import com.jme3.util.BufferUtils;
import java.awt.Canvas;
import java.util.logging.Level;
import java.util.logging.Logger;
import geometry.Shape;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
//==============================================================================
public class Graphics extends SimpleApplication {

    protected final Canvas cnvs;
    private final Shape shape = null;
    private ArrayList<Shape> shpList = null;
    private final ArrayList<float[]> NORMAL = null;
    private final ArrayList<float[]> Vertex = null;
    private final float[] maxs = new float[3];
    private final float[] mins = new float[3];
    private CustomCam customCam = null;
    private final AmbientLight ambient = new AmbientLight();            //Setting up a new ambient light
    private final DirectionalLight light1 = new DirectionalLight();     //Setting up a new directional light
    private String orientation = "XY";
    protected boolean wireframe = false;
    protected boolean update = false;
    protected boolean isSTLLoaded = false;
//==============================================================================

    public Graphics(ArrayList<Shape> shpe) {
        this.shpList = shpe;
        Logger.getLogger("").setLevel(Level.SEVERE);
        AppSettings newSetting = new AppSettings(true);
        newSetting.setFrameRate(60);
        setSettings(newSetting);
        createCanvas();
        startCanvas();
        JmeCanvasContext ctx = (JmeCanvasContext) getContext();
        ctx.setSystemListener(this);
        cnvs = ctx.getCanvas();
        setPauseOnLostFocus(false);
    }
//==============================================================================

    @Override
    public void simpleInitApp() {
        customCam = new CustomCam(cam);
        if (customCam != null) {
            flyCam.setEnabled(false);
            customCam.registerWithInput(inputManager);
            customCam.setZoomSpeed(30f);                // setting camera zoom speed
            customCam.setRotationSpeed(10f);            // setting camera rotaion speed
            customCam.setMoveSpeed(3f);                 // setting camera move speed
            customCam.setDistance(300.0f);              // setting the distance of the camera from the object
        }
        setDisplayStatView(false);                      // to hide the statistics
        setDisplayFps(false);

        ambient.setColor(new ColorRGBA(1.0f, 1.0f, 1.0f, 1.0f));
        rootNode.addLight(ambient);                     // setting the ambient light

        light1.setColor(ColorRGBA.White);
        rootNode.addLight(light1);                      //Setting the directional light
        createSceneGraph(shpList);                      // calling the SceneGraph method
    }
//==============================================================================
    //======================================================
    //Creating a method to set the orientation of the camera
    //======================================================

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
//==============================================================================

    @Override
    public void simpleUpdate(float tpf) {
        light1.setDirection(
                new Vector3f(
                        cam.getDirection().x,
                        cam.getDirection().y,
                        cam.getDirection().z));
        if (update) {
            createSceneGraph(shpList);
            update = false;
        }
    }
//==============================================================================

    public void createSceneGraph(ArrayList<Shape> shape) {
        rootNode.detachAllChildren();
        if (shape == null) {
            return;
        }

        //================================================
        //Looping through shapes and displaying each shpae
        //================================================
        shape.stream().forEach((shp) -> {
            Geometry geom = shp.draw();
            Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
            mat.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
            geom.setQueueBucket(Bucket.Transparent);
            geom.setMaterial(mat);
            rootNode.attachChild(geom);

            //======================================================
            //Setting the camera rotation to 0 if it is a 2D object
            //======================================================
            if (shp instanceof geometry.Twodimensional_Shape) {
                customCam.setRotationSpeed(0);
            } else {
                customCam.setRotationSpeed(10);
            }
        });

        viewPort.setBackgroundColor(ColorRGBA.Black);
        com.jme3.math.Vector3f lookAt = new com.jme3.math.Vector3f(4, 4, 1);
        customCam.setLookAt(lookAt);
        customCam.setXYView(true);

        //==================================================
        //Displaying the shape in the specified orientation
        //==================================================
        if (orientation.equals("XY")) {

            customCam.setXYView(true);
        }

        if (orientation.equals("YZ")) {

            customCam.setYZView(true);
        }

        if (orientation.equals("XZ")) {

            customCam.setXZView(true);
        }
        coordinateSystem();

        if (isSTLLoaded) {
            STLdata(NORMAL, Vertex);
        }

    }
//==============================================================================    
    //=====================================================================
    //Creating a coordinate system to identify the orientation of the shape
    //=====================================================================

    public void coordinateSystem() {
        Arrow X = new Arrow(new Vector3f(25f, 0.0f, 0.0f));
        Geometry xAxis = new Geometry("X-Axis", X);
        Arrow Y = new Arrow(new Vector3f(0.0f, 25f, 0.0f));
        Geometry yAxis = new Geometry("Y-Axis", Y);
        Arrow Z = new Arrow(new Vector3f(0.0f, 0.0f, 25f));
        Geometry zAxis = new Geometry("Z-Axis", Z);

        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", ColorRGBA.Red);
        xAxis.setMaterial(mat1);
        BitmapFont font = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText text1 = new BitmapText(font, false);
        text1.setSize(1f);
        text1.setColor(Red);
        text1.setLocalTranslation(25f, 0.0f, 0.0f);
        text1.addControl(new BillboardControl());
        text1.setText("X-Axis");

        Material mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat2.setColor("Color", ColorRGBA.Green);
        yAxis.setMaterial(mat2);
        BitmapText text2 = new BitmapText(font, false);
        text2.setSize(1f);
        text2.setColor(Green);
        text2.setLocalTranslation(0.0f, 25f, 0.0f);
        text2.addControl(new BillboardControl());
        text2.setText("Y-Axis");

        Material mat3 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat3.setColor("Color", ColorRGBA.Yellow);
        zAxis.setMaterial(mat3);
        BitmapText text3 = new BitmapText(font, false);
        text3.setSize(1f);
        text3.setColor(Yellow);
        text3.setLocalTranslation(0.0f, 0.0f, 25f);
        text3.addControl(new BillboardControl());
        text3.setText("Z-Axis");

        rootNode.attachChild(text1);
        rootNode.attachChild(text2);
        rootNode.attachChild(text3);
        rootNode.attachChild(xAxis);
        rootNode.attachChild(yAxis);
        rootNode.attachChild(zAxis);
    }
//==============================================================================    
    //=========================================
    //Displaying the shapes form the STL File
    //==========================================

    public void STLdata(ArrayList<float[]> NORMAL, ArrayList<float[]> Vertex) {
        rootNode.detachAllChildren();
        coordinateSystem();

        if (NORMAL == null || Vertex == null) {
            return;
        }
        float normal_indices[] = new float[3];
        float vertex_indices[] = new float[3];

        //==================================================================================
        //Calculating the centroid of the object to set the camera location at the centroid
        //===================================================================================
        float centroid_x = (this.mins[0] + this.maxs[0]) / 2;
        float centroid_y = (this.mins[1] + this.maxs[1]) / 2;
        float centroid_z = (this.mins[2] + this.maxs[2]) / 2;

        com.jme3.math.Vector3f lookAt = new com.jme3.math.Vector3f(centroid_x, centroid_y, centroid_z);
        light1.setDirection(lookAt);
        customCam.setLookAt(lookAt);
        customCam.setXYView(true);

        Mesh m = new Mesh();
        Vector3f[] normal = new Vector3f[NORMAL.size()];
        Vector3f[] vertices = new Vector3f[Vertex.size()];

        for (int i = 0; i < NORMAL.size(); i++) {
            normal_indices = NORMAL.get(i);
            normal[i] = new Vector3f(normal_indices[0], normal_indices[1], normal_indices[2]);

        }

        for (int i = 0; i < Vertex.size(); i++) {
            vertex_indices = Vertex.get(i);
            vertices[i] = new Vector3f(vertex_indices[0], vertex_indices[1], vertex_indices[2]);
        }

        //==========================================================================
        //Meshing the normals and the vertices of the traingle to create the object
        //========================================================================== 
        m.setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        m.setBuffer(VertexBuffer.Type.Normal, 3, BufferUtils.createFloatBuffer(normal));
        m.updateBound();

        if (wireframe) {

            //=============================================================
            // Displaying the wireframe model if boolean wireframe is true
            //=============================================================
            Mesh wfmesh = m.clone();
            Geometry wfgeom = new Geometry("WireframeMesh", wfmesh);
            Material wfmat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            wfmat.setColor("Color", ColorRGBA.White);
            wfmat.getAdditionalRenderState().setWireframe(true);
            wfgeom.setMaterial(wfmat);
            rootNode.attachChild(wfgeom);

        } else {

            //====================================================
            //Displaying solid model if boolean wireframe is false
            //====================================================  
            Geometry geom = new Geometry("Mesh", m);
            //Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
            // mat.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
            //geom.setQueueBucket(Bucket.Transparent);
            Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            mat.setColor("Color", ColorRGBA.Yellow);
            geom.setMaterial(mat);
            rootNode.attachChild(geom);

        }
        isSTLLoaded = false;
        //surfaceFinder(normal, Vertex);
        //System.out.println(flats);
    }

// =============================================================================
    //=======================================================================
    //Calculating the minimum value of the vertices in the x,y,z co-ordinates
    //=======================================================================
    public float[] getMinVertex(ArrayList<float[]> vertices) {

        Arrays.fill(mins, Float.MAX_VALUE);
        for (float[] vertex : vertices) {
            mins[0] = Math.min(mins[0], vertex[0]);
            mins[1] = Math.min(mins[1], vertex[1]);
            mins[2] = Math.min(mins[2], vertex[2]);
        }
        return mins;
    }

// =============================================================================
    //=======================================================================
    //Calculating the maximum value of the vertices in the x,y,z co-ordinates
    //=======================================================================    
    public float[] getMaxVertex(ArrayList<float[]> vertices) {

        Arrays.fill(maxs, Float.MIN_VALUE);
        for (float[] vertex : vertices) {
            maxs[0] = Math.max(maxs[0], vertex[0]);
            maxs[1] = Math.max(maxs[1], vertex[1]);
            maxs[2] = Math.max(maxs[2], vertex[2]);
        }
        return maxs;
    }

//==============================================================================
    //==================================================
    //Code to Identify the Flat surfaces in the geometry
    //=================================================
    HashSet<Integer> completed = new HashSet<>();
    HashMap<Integer, List<Integer>> flats = new HashMap();
    int keys = 0;

    public void Helper(Vector3f[] normal, ArrayList<float[]> Vertex, int prev, int key, boolean start) {
        if (completed.contains(prev)) {
            return;
        }
        if (prev == normal.length - 1) {
            return;
        }
        completed.add(prev);

        // int current = prev + 1;
        for (int current = prev + 1; current < normal.length; current++) {
            if (twoVertexCheck(prev, current, Vertex)) {
                float angle = normal[prev].angleBetween(normal[current]) * (float) (180 / Math.PI);

                if (Math.floor(angle) == 0) {
                    flats.putIfAbsent(key, new ArrayList<Integer>());
                    if (start) {
                        flats.get(key).add(prev);
                    }
                    flats.get(key).add(current);
                    Helper(normal, Vertex, current, key, false);
                }
            }
        }
    }
//////
    public boolean twoVertexCheck(int leftNormalIndex, int rightNormalIndex, ArrayList<float[]> Vertex) {
        int count = 0;
        int startLeft = leftNormalIndex * 3;
        int startRight = rightNormalIndex * 3;
        for (int i = startLeft; i < startLeft + 3; i++) {
            for (int j = startRight; j < startRight + 3; j++) {
                float[] tempLeftVertex = Vertex.get(i);
                float[] tempRightVertex = Vertex.get(j);
                if (tempLeftVertex[0] == tempRightVertex[0] && tempLeftVertex[1] == tempRightVertex[1]
                        && tempLeftVertex[2] == tempRightVertex[2]) {
                    count++;
                }

            }
        }
        if (count == 2) {
            return true;
        }
        return false;
    }

    public void surfaceFinder(Vector3f[] normal, ArrayList<float[]> Vertex) {
        for (int i = 0; i < normal.length; i++) {
            if (!completed.contains(normal[i])) {
                Helper(normal, Vertex, i, keys, true);
                keys++;
            }
        }
    }

//==============================================================================
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
        });
    }

}
