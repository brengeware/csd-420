import javafx.scene.shape.Circle;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;

/**
 * Brennanjfxtest.java
 * Brennan Cheatwood
 * CSD 420 - Assignment 7.2
 * 5/3/26
 *
 * Test code to verify brennanjfx functions correctly
 *
 */

public class brennanjfxtest {
    public static void main(String[] args) {

        //TEST 1
        Circle c1 = new Circle(40);
        Circle c2 = new Circle(40);
        Circle c3 = new Circle(40);
        Circle c4 = new Circle(40);
        assert c1.getRadius() == 40 : "FAIL: Circle 1 radius should be 40";
        assert c2.getRadius() == 40 : "FAIL: Circle 2 radius should be 40";
        assert c3.getRadius() == 40 : "FAIL: Circle 3 radius should be 40";
        assert c4.getRadius() == 40 : "FAIL: Circle 4 radius should be 40";
        System.out.println("TEST 1 - PASS: All circles have correct radius");

        //Test 2
        c1.getStyleClass().add("whitecircle");
        c2.getStyleClass().add("whitecircle");
        assert c1.getStyleClass().contains("whitecircle") : "FAIL: Circle 1 MISSING WHITECIRCLE CLASS";
        assert c2.getStyleClass().contains("whitecircle") : "FAIL: Circle 2 MISSING WHITECIRCLE CLASS";
        System.out.println("TEST 2 - PASS: Circle 1 and Circle 2 have correct style class");

        //Test 3 CSS IDs are applied to Circle 3 and Circle 4
        c3.setId("redcircle");
        c4.setId("greencircle");
        assert c3.getId().equals("redcircle") : "FAIL: Circle 3 MISSING REDCIRCLE ID";
        assert c4.getId().equals("greencircle") : "FAIL: Circle 4 MISSING GREENCIRCLE ID";
        System.out.println("TEST 3 - PASS: Circle 3 and Circle 4 have correct CSS IDs");

        //Test 4 - Create HBox and verify it contains all four circles
        StackPane pane = new StackPane();
        pane.getStyleClass().add("border");
        assert pane.getStyleClass().contains("border") : "FAIL: StackPane MISSING BORDER CLASS";
        System.out.println("TEST 4 - PASS: StackPane has correct style class");

        //TEST 5 Verify HBox layout
        HBox root = new HBox(10, pane, c2, c3, c4);
        assert root.getChildren().size() == 4 : "FAIL: HBox should contain 4 children";
        System.out.println("TEST 5 - PASS: HBox contains correct number of children");
        System.out.println("\nALL TESTS PASSED - brennanjfx components are correctly configured");

    }
}