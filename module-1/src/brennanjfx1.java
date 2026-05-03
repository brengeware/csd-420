import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

//Brennan Cheatwood
//CSD 420 - Assignment 7.2
//5/3/26

public class brennanjfx extends Application {

    //LAYOUT CONSTANTS:
    private static final double CIRCLE_RADIUS = 40;
    private static final double PANE_WIDTH = 100;
    private static final double PANE_HEIGHT = 250;
    private static final double HBOX_SPACING = 10;
    private static final double SCENE_WIDTH = 400;
    private static final double SCENE_HEIGHT = 300;

    @Override
    public void start(Stage primaryStage) {
        //Circle 1
        Circle circle1 = new Circle(CIRCLE_RADIUS);
        circle1.getStyleClass().add("whitecircle");

        StackPane borderedPane = new StackPane(circle1);
        borderedPane.setPrefSize(PANE_WIDTH, PANE_HEIGHT);
        borderedPane.getStyleClass().add("border");

        //Circle 2
        Circle circle2 = new Circle(CIRCLE_RADIUS);
        circle2.getStyleClass().add("whitecircle");

        //Circle 3
        Circle circle3 = new Circle(CIRCLE_RADIUS);
        circle3.setId("redcircle");

        //Circle 4
        Circle circle4 = new Circle(CIRCLE_RADIUS);
        circle4.setId("greencircle");

        //Layout - arrange all four nodes in a horizontal row
        HBox root = new HBox(HBOX_SPACING, borderedPane, circle2, circle3, circle4);
        root.setStyle("-fx-alignment: center; -fx-padding: 20;");

        //Scene
        Scene scene = new Scene(root, SCENE_WIDTH , SCENE_HEIGHT);

        //Load CSS
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        //Stage
        primaryStage.setTitle("Brennan's JavaFX Assignment");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //main method to launch the application
    public static void main(String[] args) {
        launch(args);
    }
}