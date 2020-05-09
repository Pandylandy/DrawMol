package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {

        class MyLine
        {
            Line line;
            Circle start, end;

            public MyLine(Line line, Circle start, Circle end)
            {
                this.line=line; this.start=start; this.end=end;
            }
        }

        ArrayList<MyLine> lines=new ArrayList<MyLine>();
        ArrayList<Circle> circ_list = new ArrayList<>();
        boolean f = false;
        double dx = 0, dy = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        primaryStage.setTitle("Molecule");
        primaryStage.setScene(new Scene(root, 300, 275));

        final Text txt = new Text();
        txt.setText("Enter molecule's brutto formula"+"\n"+"( example: H2O1)");
        txt.setLayoutX(50);
        txt.setLayoutY(100);
        root.getChildren().add(txt);

        TextField name = new TextField();
        name.setLayoutX(50);
        name.setLayoutY(120);
        root.getChildren().add(name);

        Button btn = new Button("Find");
        btn.setLayoutX(50);
        btn.setLayoutY(150);
        root.getChildren().add(btn);

        btn.setOnAction((event) -> {

        Stage con_matrix = new Stage();
        GridPane g = new GridPane();
        con_matrix.setScene(new Scene(g,400,200));
        con_matrix.setTitle("Connection Matrix");

        try {
            Molecule mol = new Molecule(name.getText());
            String out_str = mol.Print();

            g.setHgap(5); g.setVgap(5);
            for (int i = 0; i<mol.m.size();i++ ){
                Text ind = new Text();
                ind.setText(""+i);
                g.add(ind,1+i,0);
            }

            for (int i = 0; i<mol.m.size();i++ ){
                Text ind = new Text();
                ind.setText(""+i);
                g.add(ind,0,1+i);
            }

            TextField[][] matrix = new TextField[mol.m.size()][mol.m.size()];
            for ( int i = 0; i < mol.m.size(); i++){
                for ( int j = 0; j < mol.m.size(); j++){
                    TextField field = new TextField();
                    matrix[i][j] = field;
                    g.add(field,1+j,1+i);
                }
            }


            Button subm = new Button("Submit");
            g.add(subm,mol.m.size()-1,2+mol.m.size());

            subm.setOnAction((event1) ->{
                int var;
                for (int i = 0; i < matrix.length; i++){
                    for (int j = 0; j <= i; j++){
                        var = Integer.parseInt(matrix[i][j].getText());
                        if (var == 1){
                            mol.new_bond(i,j);
                        }
                    }
                }
                System.out.println(mol.bonds);


                Group root1 = new Group();

                Stage draw = new Stage();
                draw.setScene(new Scene(root1,300,300));
                draw.setTitle("Drawing");

                double w = draw.getScene().getWidth();
                double h = draw.getScene().getHeight();

                for (int i = 0; i < mol.m.size(); i++){

                    Circle circ = new Circle();
                    if (mol.m.get(i).symbol.equals("O")){
                        circ.setFill(Color.INDIANRED);
                        circ.setRadius(25);
                    }
                    if (mol.m.get(i).symbol.equals("H")){
                        circ.setFill(Color.CADETBLUE);
                        circ.setRadius(15);
                    }
                    if (mol.m.get(i).symbol.equals("C")){
                        circ.setFill(Color.GREY);
                        circ.setRadius(30);
                    }


                    circ.setCenterX(50+i*10);
                    circ.setCenterY(50+i*10);


                    circ.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            f = true;
                            dx = event.getSceneX() - circ.getCenterX();
                            dy = event.getSceneY() - circ.getCenterY();
                        }
                    });

                    circ.setOnMouseDragged(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if(f)
                            {
                                if (event.getSceneX() - dx + circ.getRadius() < w && (event.getSceneX() - dx - circ.getRadius() >= 0) )
                                    circ.setCenterX(event.getSceneX()-dx);
                                if ((event.getSceneY() - dy + circ.getRadius() < h) && (event.getSceneY() - dy - circ.getRadius() >= 0) )
                                    circ.setCenterY(event.getSceneY() - dy);

                                for(int ii=0;ii<lines.size();ii++)
                                {
                                    if(lines.get(ii).end.equals(circ)) {
                                        lines.get(ii).line.setEndX(event.getSceneX() - dx);
                                        lines.get(ii).line.setEndY(event.getSceneY() - dy);
                                    }

                                    if(lines.get(ii).start.equals(circ)) {
                                        lines.get(ii).line.setStartX(event.getSceneX() - dx);
                                        lines.get(ii).line.setStartY(event.getSceneY() - dy);
                                    }
                                }
                            }



                        }
                    });

                    circ.setOnMouseReleased(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if(f)
                            {
                                if (event.getSceneX() - dx + circ.getRadius() < w && (event.getSceneX() - dx - circ.getRadius() >= 0) )
                                    circ.setCenterX(event.getSceneX()-dx);
                                if ((event.getSceneY() - dy + circ.getRadius() < h) && (event.getSceneY() - dy - circ.getRadius() >= 0) )
                                    circ.setCenterY(event.getSceneY() - dy);

                                for(int ii=0;ii<lines.size();ii++)
                                {
                                    if(lines.get(ii).end.equals(circ)) {
                                        lines.get(ii).line.setEndX(event.getSceneX() - dx);
                                        lines.get(ii).line.setEndY(event.getSceneY() - dy);
                                    }
                                }

                                f = false;

                            }
                        }
                    });
                    circ_list.add(circ);
                    root1.getChildren().add(circ);
                }


                for(int i=0;i<mol.bonds.size();i++)
                {
                    Line line=new Line();
                    line.setStartX(circ_list.get(mol.bonds.get(i).get(0)).getCenterX());
                    line.setStartY(circ_list.get(mol.bonds.get(i).get(0)).getCenterY());
                    line.setEndX(circ_list.get(mol.bonds.get(i).get(1)).getCenterX());
                    line.setEndY(circ_list.get(mol.bonds.get(i).get(1)).getCenterY());

                    lines.add(new MyLine(line,circ_list.get(mol.bonds.get(i).get(0)),circ_list.get(mol.bonds.get(i).get(1))));
                    root1.getChildren().add(line);
                    line.toBack();

                }


                draw.show();
            });

            con_matrix.show();

        }
        catch (Exception e) {
            System.out.println("brutto formula is wrong:" + e.getMessage());

        }});

primaryStage.show();


    }
    public static void main(String[] args) {
        launch(args);
    }}

