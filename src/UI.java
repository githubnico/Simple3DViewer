import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.stage.Stage;

/**
 * Created by Deviltech on 06.12.2015.
 */
public class UI extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane myPane = new Pane();

        // create Scene with depth buffer

        Group root = new Group();

        Scene scene = new Scene(root, 600, 800, true);

        Camera camera = new PerspectiveCamera(true);

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

        myCylinder.setMaterial(greenMaterial);
        myCylinder.setTranslateX(0);
        myCylinder.setTranslateY(0);
        myCylinder.setTranslateZ(0);
        Tooltip.install(
                myCylinder,
                new Tooltip("Cylinder")
        );

        myBox1.setMaterial(blackMaterial);
        myBox1.setTranslateX(0);
        myBox1.setTranslateY(-100);
        myBox1.setTranslateZ(0);
        Tooltip.install(
                myBox1,
                new Tooltip("Box1")
        );

        myBox2.setMaterial(blueMaterial);
        myBox2.setTranslateX(0);
        myBox2.setTranslateY(100);
        myBox2.setTranslateZ(0);
        Tooltip.install(
                myBox2,
                new Tooltip("Box2")
        );


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


}
