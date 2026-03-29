//this programs demos two JFX features
//Line & Text
// A pane is created to hold the JFX objects
// A Line object is created and positioned across the window
// A Text object is created to show a title above the line
// The text color and font size are set for readability,
// Both objects are added to the Pane
// The pane is placed in a scene and displayed on the Stage.

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class exampleForPost extends Application {

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();

        Line line = new Line(50, 100, 250, 100);

        Text text = new Text(70, 70, "JavaFX Line and Text Example");
        text.setFont(new Font(18));
        text.setFill(Color.DARKBLUE);

        pane.getChildren().addAll(line, text);

        Scene scene = new Scene(pane, 300, 180);
        stage.setTitle("JFX Example");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
