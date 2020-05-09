package sample;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by adeliya16 on 4/24/17.
 */
public class NewFinder extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Group root = new Group();
        sample.Molecule m = new sample.Molecule("H2O1");

        primaryStage.setTitle("Insert brutto");
        primaryStage.setScene(new Scene(root, 300, 275));
        Text txt=new Text();
        txt.setLayoutX(50);
        txt.setLayoutY(100);

        TextField name=new TextField();
        name.setLayoutX(50);
        name.setLayoutY(120);
        root.getChildren().add(name);

        Button btn=new Button("Find molecule");
        btn.setLayoutX(50);
        btn.setLayoutY(150);
        root.getChildren().add(btn);
        Random rand = new Random();

        btn.setOnAction((e)->
        {
            Stage hello=new Stage();
            BorderPane g=new BorderPane();
            hello.setScene(new Scene(g,300,100));
            hello.setTitle("Molecule");

            Text message=new Text();
            message.setText("H2O");
            message.setTextAlignment((TextAlignment.CENTER));
            message.setFont(new Font("Arial",30));
            message.setFill(Color.CRIMSON);

            hello.show();

            System.out.println(name.getText());
            name.setText("!!!");
            Button bt = new Button("Show mol");
            bt.setLayoutX(320);
            bt.setLayoutY(75);
            root.getChildren().add(bt);
            bt.setOnAction((ev) ->{
                try {Stage s = new Stage();
                    sample.ShowMol sm=new sample.ShowMol();
                    sm.start(s);
                }
                catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            } );
    });
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
