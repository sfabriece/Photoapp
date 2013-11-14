package slideShow;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import login.LoginWindow;

public class Slideshow extends Application {

    StackPane root = new StackPane();
    SequentialTransition slideshow;
    ImageTransition imageTrans;
    ImageView[] bildeListe;

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Start initiated");

        bildeListe = ListOfImages.getImageViewList();

        System.out.println("Gathered list of images");

        final Button quit = new Button();
        quit.setText("Quit Slideshow");
        quit.setLayoutX(500);
        quit.setLayoutY(500);
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Platform.exit();
            }
        });

        final Button menu = new Button();
        menu.setText("Admin Menu");
        menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                LoginWindow login = new LoginWindow();
                login.generateStage();
            }
        });


        final HBox box = new HBox(20);
        box.setAlignment(Pos.BOTTOM_RIGHT);
        box.setOpacity(0.0);
        box.getChildren().add(quit);
        box.getChildren().add(menu);

        this.root.getChildren().add(box);

        this.root.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), box);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);

                PauseTransition pause = new PauseTransition(Duration.millis(5000));

                FadeTransition fadeOut = new FadeTransition(Duration.millis(1000), box);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);

                SequentialTransition sequence = new SequentialTransition();
                sequence.getChildren().addAll(fadeIn, pause, fadeOut);
                if (box.getOpacity() > 0.1) {
                    //Do nothing
                } else {
                    sequence.play();
                }
            }
        });

        //Legger alle bildene inn i slideshow transistion
        for (ImageView bilde : bildeListe) {

            bilde.setOpacity(0);
            this.root.getChildren().add(bilde);
        }

        stage = SlideShowWindow.getSlideShowWindow();
        stage.setScene(new Scene(root, 800, 600, Color.BLANCHEDALMOND));
        stage.show();


        System.out.println("Starter slideshowlooping");
        slideshow = new SequentialTransition();
        imageTrans = new ImageTransition();
        new Thread(addTransitions()).start();
        slideshow.play();
    }

    public Task addTransitions() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                    //Legger inn overgang for alle bilder i bilde listen
                    for (ImageView bilde : bildeListe) {
                        System.out.println("Legger til: " + bilde.toString());
                        slideshow.getChildren().add(imageTrans.getFullOvergang(bilde));
                        slideshow.play();
                    }
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    }
    public static void main(String[] args) {
        launch(args);
    }
}