package menu;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Menu {

    private VBox vPane;
    private AnchorPane sidePane;
    private GridPane root;
    private Stage stage;
    private Scene scene;
    private ArrayList<Button> buttons;
    private DelayNode delay;
    //Skal holde panes fra andre aktiviteter
    private Pane searchPane, deletePane;

    public Menu() {
        buttons = new ArrayList<>();
        this.makeButtons();

        delay = new DelayNode();

        root = new GridPane();
        this.buildRootPane();

        sidePane = new AnchorPane();
        vPane = new VBox();
        this.buildSidePane();

        root.add(sidePane, 0, 0);
        Pane testPane = new Pane();
        testPane.setMinSize(800, 600);
        testPane.setPrefSize(1080, 720);
        GridPane.setHgrow(testPane, Priority.ALWAYS);
        GridPane.setVgrow(testPane, Priority.ALWAYS);

        setActivityPane(testPane);
        scene = new Scene(root, 1280, 720, Color.WHITE);
        
        //To move the window without borders
        //Need pressed/released also
        //Maximizing? :s
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            System.out.println("Vindu: " + scene.getWindow().getX());
            System.out.println(event.getX());
            //scene.getWindow().setX(event.getX());
            //scene.getWindow().setY(event.getY());
        }
    });

    }

    private void buildRootPane() {
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(5, 5, 5, 5));
        root.setGridLinesVisible(true);


    }

    private void buildSidePane() {
        vPane.setAlignment(Pos.TOP_RIGHT);
        VBox.setVgrow(vPane, Priority.ALWAYS);
        vPane.setSpacing(10);
        vPane.getChildren().addAll(buttons);
        vPane.setMinWidth(200);

        sidePane.setMinWidth(200);
        sidePane.getChildren().add(vPane);
        AnchorPane.setTopAnchor(vPane, 5.0);
        sidePane.getChildren().add(delay);
        AnchorPane.setBottomAnchor(delay, 10.0);
    }

    private void makeButtons() {
        Button btnSearch = new Button("Hente Bilder");
        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //setActivityPane(searchPane);
                stage.setTitle("Emil er teit!");
            }
        });
        Button btnDelete = new Button("Slette Bilder");
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //setActivityPane(deletePane);
                stage.setTitle("Neida");
            }
        });
        buttons.add(btnSearch);
        buttons.add(btnDelete);
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setPrefSize(190, 40);
            buttons.get(i).setTranslateY(10);
        }
    }

    private void setActivityPane(Pane activityPane) {
        if (root.getChildren().size() > 2) {
            root.getChildren().remove(1);
        }
        root.add(activityPane, 1, 0);
    }

    public void generateStage() {
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("PhotoApp");
        stage.setMinHeight(620);
        stage.setMinWidth(1020);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
}
