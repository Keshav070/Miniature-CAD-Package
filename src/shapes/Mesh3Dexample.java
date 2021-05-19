/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;
import com.jme3.util.BufferUtils;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author akumar
 */
public class Mesh3Dexample extends SimpleApplication {

    protected final Canvas cnvs;
    private CustomCam customCam = null;
    private final AmbientLight ambient = new AmbientLight();
    private final DirectionalLight light1 = new DirectionalLight();

    //==========================================================================
    public Mesh3Dexample() {
        Logger.getLogger("").setLevel(Level.SEVERE);
        AppSettings newSetting = new AppSettings(true);
        newSetting.setFrameRate(30);
        setSettings(newSetting);
        createCanvas(); // create canvas!
        startCanvas();
        JmeCanvasContext ctx = (JmeCanvasContext) getContext();
        ctx.setSystemListener(this);
        cnvs = ctx.getCanvas();
        setPauseOnLostFocus(false);
    } // end of Constructor

    //==========================================================================
    @Override
    public void simpleInitApp() {
        customCam = new CustomCam(cam);
        if (customCam != null) {
            flyCam.setEnabled(false);
            customCam.registerWithInput(inputManager);
            customCam.setZoomSpeed(10f);
            customCam.setRotationSpeed(10f);
            customCam.setMoveSpeed(3f);
            customCam.setDistance(50.0f);
        }
        setDisplayStatView(false); // to hide the statistics
        setDisplayFps(false);

        ambient.setColor(new ColorRGBA(0.7f, 0.7f, 0.8f, 1.0f));
        rootNode.addLight(ambient);
        // Set up the directional light
        light1.setColor(ColorRGBA.White);
        rootNode.addLight(light1);

        createSceneGraph();

    }

    //==========================================================================
    /* Use the main event loop to trigger repeating actions. */
//    private final Quaternion camQ = new Quaternion();

    @Override
    public void simpleUpdate(float tpf) {
        light1.setDirection(
                new Vector3f(
                        cam.getDirection().x,
                        cam.getDirection().y,
                        cam.getDirection().z));
//        camQ.lookAt(cam.getLocation(), cam.getUp());
    } // simpleUpdate()

    //==========================================================================
    private void createSceneGraph() {
        float angle = (float) (60 * Math.PI / 180);
        Mesh m = new Mesh();

        // Vertex positions in space
        Vector3f[] vertices = new Vector3f[12];
        vertices[0] = new Vector3f(0, (float) (3* Math.sin(angle)), 0);
        vertices[1] = new Vector3f(3, 0, 0);
        vertices[2] = new Vector3f(0, 0, 0);

        vertices[3] = new Vector3f(3, 0, 0);
        vertices[4] = new Vector3f(0, 3, 0);
        vertices[5] = new Vector3f(1, 1, 3);

        vertices[6] = new Vector3f(0, 0, 0);
        vertices[7] = new Vector3f(3, 0, 0);
        vertices[8] = new Vector3f(1, 1, 3);

        vertices[9] = new Vector3f(0, 0, 0);
        vertices[10] = new Vector3f(1, 1, 3);
        vertices[11] = new Vector3f(0, 3, 0);
        
        Vector3f[] normals = new Vector3f[12];
        normals[0] = new Vector3f(0, 0, -1);
        normals[1] = new Vector3f(0, 0, -1);
        normals[2] = new Vector3f(0, 0, -1);
        
        Vector3f v34 = vertices[4].subtract(vertices[3]);
        Vector3f v35 = vertices[5].subtract(vertices[3]);
        normals[3] = v34.cross(v35);
        normals[4] = normals[3] ;
        normals[5] = normals[3] ;
        
        Vector3f v67 = vertices[7].subtract(vertices[6]);
        Vector3f v68 = vertices[8].subtract(vertices[6]);
        normals[6] = v67.cross(v68);
        normals[7] = v67.cross(v68);
        normals[8] = v67.cross(v68);
        
        Vector3f v911 = vertices[11].subtract(vertices[9]);
        Vector3f v910 = vertices[10].subtract(vertices[9]);
        normals[9] = v910.cross(v911);
        normals[10] = v910.cross(v911);
        normals[11] = v910.cross(v911);
 

        // Setting buffers
        m.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        m.setBuffer(Type.Normal, 3, BufferUtils.createFloatBuffer(normals));
        m.updateBound();

        // *************************************************************************
        // First mesh uses one solid color
        // *************************************************************************
        // Creating a geometry, and apply a single color material to it
//        ColorRGBA color = ColorRGBA.Blue;
        Geometry geom = new Geometry("OurMesh", m);
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
//        mat.setBoolean("UseMaterialColors", true);
//        mat.setColor("Ambient", color);
//        mat.setColor("Specular", color);
//        mat.setColor("Diffuse", color);
//        mat.setFloat("Shininess", 64f);
        geom.setMaterial(mat);

        // Attaching our geometry to the root node.
        rootNode.attachChild(geom);

        // *************************************************************************
        // Third mesh will use a wireframe shader to show wireframe
        // *************************************************************************
        Mesh wfMesh = m.clone();
        Geometry wfGeom = new Geometry("wireframeGeometry", wfMesh);
        Material matWireframe = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matWireframe.setColor("Color", ColorRGBA.Green);
        matWireframe.getAdditionalRenderState().setWireframe(true);
        wfGeom.setMaterial(matWireframe);
        wfGeom.setLocalTranslation(4, 4, 0);
        rootNode.attachChild(wfGeom);
        
        com.jme3.math.Vector3f lookAt = new com.jme3.math.Vector3f(4, 4, 1);
        customCam.setLookAt(lookAt);
        customCam.setXYView(true);
    }
    
     public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Mesh3Dexample app = new Mesh3Dexample();
                JFrame jframe = new JFrame();
                jframe.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                jframe.setSize(800, 600);
                jframe.setTitle("HelloJME");
                
                JPanel jpanel = new JPanel();
                jpanel.setLayout(new BorderLayout());
                jpanel.add(app.cnvs, BorderLayout.CENTER);
                
                jframe.add(jpanel);
                jframe.setVisible(true);
            }
        });
    }

}
