//Brennan Cheatwood
//CSD420 - Advanced Java
//3/21/26
//Assignment 1.3 Cards Program



import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;


public class RandomCards extends Application {
    private final ImageView[] cardViews = new ImageView[4];

    @Override
    public void start(Stage primaryStage) {
        HBox cardBox = new HBox(10);
        cardBox.setAlignment(Pos.CENTER);

        for (int i = 0; i < 4; i++) {
            cardViews[i] = new ImageView();
            cardViews[i].setFitWidth(100);
            cardViews[i].setPreserveRatio(true);
            cardBox.getChildren().add(cardViews[i]);
        }

        displayRandomCards();

        Button refreshButton = new Button("Refresh");

        //lambda expression
        refreshButton.setOnAction(e -> displayRandomCards());

        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(cardBox, refreshButton);

        Scene scene = new Scene(root, 500, 300);
        primaryStage.setTitle("Random Cards");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void displayRandomCards() {
        ArrayList<Integer> deck = new ArrayList<>();

        for (int i = 1; i <=52; i++) {
            deck.add(i);
        }

        Collections.shuffle(deck);

        for (int i = 0; i < 4; i++) {
            try {
                String fileName = "cards/" + deck.get(i) + ".png";
                Image image = new Image(new FileInputStream(fileName));
                cardViews[i].setImage(image);
            } catch (FileNotFoundException e) {
                System.out.println("Couldn't find image.");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
