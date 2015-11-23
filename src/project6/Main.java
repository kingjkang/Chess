package project6;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.*;

public class Main extends Application{

	Stage staplesCenter;
	Scene tay1989;
	BorderPane universe;
	BorderPane borderPaneCenter, borderPaneRight, borderPaneLeft, borderPaneTop, borderPaneBottom;
	
	public GridPane createBoard(){
		GridPane board = new GridPane();
		int size = 8;
		for (int row = 0; row < size; row++){
			for (int col = 0; col < size; col++){
				StackPane spot = new StackPane();
				String color;
				if ((row+col)%2 == 0){
					color = "chocolate";
				}
				else {
					color = "brown";
				}
				spot.setStyle("-fx-background-color: "+color+";");
				board.add(spot, col, row);
			}
		}
		for (int i = 0; i < size; i++) {
            board.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            board.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
		return board;
	}
	
	@Override
	public void start(Stage primaryStage) {
		universe = new BorderPane();
		
		staplesCenter = primaryStage;
		staplesCenter.setTitle("Chess");
		
		universe.setCenter(createBoard());
		
		tay1989 = new Scene(universe, 500, 500);
		
		staplesCenter.centerOnScreen();
		staplesCenter.setScene(tay1989);
		staplesCenter.show();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("Test 1");
		launch(args);
	}

}
