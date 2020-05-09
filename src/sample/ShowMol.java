package sample;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;
/**
 * Created by Храпулик on 20.05.2017.
 */

public class ShowMol extends NewFinder {

    class MyLine {
        Line line;
        Circle start, end;

        public MyLine(Line line, Circle start, Circle end) {
            this.line = line;
            this.start = start;
            this.end = end;
        }
    }
    ArrayList<Circle> circles = new ArrayList<Circle>();
    ArrayList<MyLine> lines = new ArrayList<MyLine>();
    boolean f = false;
    double dx = 0, dy = 0;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        primaryStage.setTitle("Insert brutto");
        primaryStage.setScene(new Scene(root, 400, 400));

        Random rand = new Random();
        sample.Molecule m = new sample.Molecule("H2O");

        try {
            for (int i = 0; i < m.Size(); i++) {
                String Aname = m.Atomname(i);
                double Aradios = m.Atomrad(i);
                Circle c = new Circle();

                c.setCenterX(rand.nextInt(370));
                c.setCenterY(rand.nextInt(370));
                c.setRadius(10);
                c.setFill(Color.BLUEVIOLET);
                root.getChildren().add(c);
                circles.add(c);

                c.setOnMousePressed((e) -> {
                    f = true;
                    dx = e.getSceneX() - c.getCenterX();
                    dy = e.getSceneY() - c.getCenterY();
                });

                c.setOnMouseDragged((e) -> {
                    if (f) {
                        c.setCenterX(e.getSceneX() - dx);
                        c.setCenterY(e.getSceneY() - dy);

                        for (int ii = 0; ii < lines.size(); ii++) {
                            if (lines.get(ii).end.equals(c)) {
                                lines.get(ii).line.setEndX(e.getSceneX() - dx);
                                lines.get(ii).line.setEndY(e.getSceneY() - dy);
                            }
                        }
                    }
                });

                c.setOnMouseReleased((e) -> {
                    if (f) {
                        c.setCenterX(e.getSceneX() - dx);
                        c.setCenterY(e.getSceneY() - dy);

                        for (int ii = 0; ii < lines.size(); ii++) {
                            if (lines.get(ii).end.equals(c)) {
                                lines.get(ii).line.setEndX(e.getSceneX() - dx);
                                lines.get(ii).line.setEndY(e.getSceneY() - dy);
                            }
                        }

                        f = false;
                    }
                });

            }

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
