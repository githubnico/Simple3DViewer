import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 * Created by Deviltech on 06.12.2015.
 */
public class UI extends Application {

    double originX, originY;

    boolean isShiftPressed;

    Camera camera;

    Group root;


    @Override
    public void start(Stage primaryStage) throws Exception {

        root = new Group();

        // create scene with depth buffer
        Scene scene = new Scene(root, 600, 800, true);

        scene.setOnMousePressed(sceneOnMousePressedEventHandler);
        scene.setOnMouseDragged(sceneOnMouseDraggedEventHandler);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SHIFT) {
                isShiftPressed = true;
            }
        });

        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.SHIFT) {
                isShiftPressed = false;
            }
        });

        camera = new PerspectiveCamera(true);

        Box myBox1 = new Box(100, 100, 100);
        Box myBox2 = new Box(150, 20, 50);

        Cylinder myCylinder = new Cylinder(10, 200);


        PhongMaterial blackMaterial = new PhongMaterial();
        blackMaterial.setDiffuseColor(Color.BLACK);
        blackMaterial.setSpecularColor(Color.DARKGRAY);

        PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        setShapeProperties(myCylinder, 0, 0, 0, greenMaterial, "Cylinder");

        setShapeProperties(myBox1, 0, -100, 0, blackMaterial, "Box1");

        setShapeProperties(myBox2, 0, 100, 0, blueMaterial, "Box2");



        root.getChildren().addAll(myBox1, myBox2, myCylinder);

        camera.setNearClip(0.1);
        camera.setFarClip(10000);
        camera.setTranslateX(0);
        camera.setTranslateY(0);
        camera.setTranslateZ(-500);

        scene.setCamera(camera);


        primaryStage.setScene(scene);
        primaryStage.setTitle("3D");

        // show scene
        primaryStage.show();
    }

    /**
     * Sets the properties of the shapes
     * @param myShape
     * @param x
     * @param y
     * @param z
     * @param material
     * @param toolTipText
     * @return
     */
    private void setShapeProperties(Shape3D myShape, double x, double y, double z, PhongMaterial material, String toolTipText ){
        myShape.setMaterial(material);
        myShape.setTranslateX(x);
        myShape.setTranslateY(y);
        myShape.setTranslateZ(z);
        Tooltip.install(
                myShape,
                new Tooltip(toolTipText)
        );
    }


    /**
     * Eventhandler for mouse pressed for circle drag
     */
    EventHandler<MouseEvent> sceneOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    // set origin coordinates
                    originX = t.getSceneX();
                    originY = t.getSceneY();
                }
            };

    /**
     * Eventhandler for mouse follow on drag
     */
    EventHandler<MouseEvent> sceneOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    // calculate offset
                    double offsetX = t.getSceneX() - originX;
                    double offsetY = t.getSceneY() - originY;
                    // follow mouse
                    // move left and right
                    if (isShiftPressed) {
                        camera.setTranslateZ(camera.getTranslateZ() + (offsetX + offsetY) / 2);
                    } else {
                        root.getTransforms().add(new Rotate(offsetX, 0, 0, 0, Rotate.Y_AXIS));
                        root.getTransforms().add(new Rotate(offsetY, 0, 0, 0, Rotate.Z_AXIS));
                    }

                    originX += offsetX;
                    originY += offsetY;

                }
            };


}
