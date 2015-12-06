import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * animate line
 * Created by huson on 11/24/15.
 */
public class AnimateLine extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Line line=new Line();

        KeyFrame keyFrame1=new KeyFrame(Duration.millis(0),
                new KeyValue(line.startXProperty(),10),
                new KeyValue(line.startYProperty(),60),
                new KeyValue(line.endXProperty(),60),
                new KeyValue(line.endYProperty(),10));

        KeyFrame keyFrame2=new KeyFrame(Duration.millis(1000),
                new KeyValue(line.startXProperty(),100),
                new KeyValue(line.startYProperty(),25),
                new KeyValue(line.endXProperty(),400),
                new KeyValue(line.endYProperty(),25));

        Timeline timeline=new Timeline(keyFrame1,keyFrame2);


        Pane pane=new Pane();
        pane.getChildren().add(line);

        Scene scene=new Scene(pane,500,500);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();

        timeline.setAutoReverse(true);
        timeline.setCycleCount(1000);
        timeline.play();
    }
}
