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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	
	/*
	 * */

	Stage staplesCenter;
	Scene tay1989;
	Stage ATTStadium = new Stage();
	Scene georgeStrait;
	Stage Zilker;
	Scene ACL;
	GridPane bumblebee;
	BorderPane gg;
	BorderPane universe;
	BorderPane borderPaneCenter, borderPaneRight, borderPaneLeft, borderPaneTop, borderPaneBottom;
	Image blank = new Image("file:emptyPiece.png");
	
	boolean initialPress = false;
	boolean secondaryPress = false;
	boolean checkMate = false;
	int printIR = 0;
	int printIC = 0;
	int printSR = 0;
	int printSC = 0;
	//http://www.javacodegeeks.com/2013/10/javafx-2-how-to-load-image.html
	
	public GridPane createBoard(){
		GridPane board = new GridPane();
		Button addButton = null;
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
				String pieceToAdd;
				Image picToAdd;
				//whwen the board is initialized and there is nothing there what do i print
				if (ChessPiece.ChessBoard[row][col] == null){
					picToAdd = blank;
					board.add(spot, col, row);
					addButton = new Button("", new ImageView(picToAdd));
					//not sure exaclty if this gets rid of the button or makes the button the same color
					addButton.setStyle("-fx-background-color: "+color+";");
					board.add(addButton, col, row);
					
					int printR = row;
					int printC = col;
					
					addButton.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							if (initialPress == true){
								secondaryPress = !secondaryPress;
								printSR = printR;
								printSC = printC;
							}
							else {
								initialPress = !initialPress;
								printIR = printR;
								printIC = printC;
							}
							
							if (initialPress == true && secondaryPress == true){
								initialPress = false;
								secondaryPress = false;
								
								checkMate = ChessPiece.checkMate();
								
								if(ChessPiece.checkForKing()){
									universe.setCenter(createBoard());
									if(checkMate){
										System.out.println("CheckMate! lol won.");
										staplesCenter.close();
										showGG();
										checkMate = false;
									}
									else{
										System.out.println(ChessPiece.printColor + "King is in Check!");
									}
								}
								
								if(checkMate){
									System.out.println("Stalemate! No valid move can be made. It is a draw.");
									staplesCenter.close();
									showStalemate();
									//showTie
								}
								ChessPiece.movePiece(printIR, printIC, printSR, printSC);
								if (ChessPiece.ChessBoard[printSR][printSC] instanceof Pawn){
									if (printSR == 0 || printSR == 7){
										Pawn pawn = (Pawn) ChessPiece.ChessBoard[printSR][printSC];
										Zilker = new Stage();
										bumblebee = new GridPane();
										bumblebee.setHgap(10);
										bumblebee.setVgap(10);
										bumblebee.setPadding(new Insets(25, 25, 25, 25));
										
										Zilker.setTitle("Evolution");
										String winner = "";
										Label printGG = new Label("Whaat? It's Evolving! \nPlease select the type of the piece you would like to transform into");
										bumblebee.setConstraints(printGG, 0, 0);
										
										Button Q = new Button("Queen");
										bumblebee.setConstraints(Q, 0, 2);
										
										Button R = new Button("Rook");
										bumblebee.setConstraints(R, 0, 4);
										
										Button B = new Button("Bishop");
										bumblebee.setConstraints(B, 0, 6);
										
										Button K = new Button("Knight");
										bumblebee.setConstraints(K, 0, 8);
										
										bumblebee.getChildren().addAll(printGG, Q, R, B, K);
										
										bumblebee.setAlignment(Pos.CENTER);
										ACL = new Scene(bumblebee, 600, 300);
										Zilker.centerOnScreen();
										Zilker.setScene(ACL);
										Zilker.show();
										Q.setOnAction(new EventHandler<ActionEvent>() {

											@Override
											public void handle(ActionEvent event) {
												pawn.evolve("Queen");
												universe.setCenter(createBoard());
												Zilker.close();
												
											}
											
										});
										
										R.setOnAction(new EventHandler<ActionEvent>() {

											@Override
											public void handle(ActionEvent event) {
												pawn.evolve("Rook");
												universe.setCenter(createBoard());
												Zilker.close();
												
											}
											
										});
										
										B.setOnAction(new EventHandler<ActionEvent>() {

											@Override
											public void handle(ActionEvent event) {
												pawn.evolve("Bishop");
												universe.setCenter(createBoard());
												Zilker.close();
												
											}
											
										});
										
										K.setOnAction(new EventHandler<ActionEvent>() {

											@Override
											public void handle(ActionEvent event) {
												pawn.evolve("Knight");
												universe.setCenter(createBoard());
												Zilker.close();
												
											}
											
										});
									}
								}
								System.out.println(ChessPiece.printColor + " Turn");
								universe.setCenter(createBoard());
							}
						}
					});
				}
				else {
					picToAdd = ChessPiece.ChessBoard[row][col].toImage();
					//pieceToAdd = ChessPiece.ChessBoard[row][col].toString();
					board.add(spot, col, row);
					addButton = new Button("", new ImageView(picToAdd));
					//addButton = new Button(pieceToAdd);
					//not sure exaclty if this gets rid of the button or makes the button the same color
					addButton.setStyle("-fx-background-color: "+color+";");
					board.add(addButton, col, row);
					
					int printR = row;
					int printC = col;
					//printIR = printR;
					
					addButton.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							if (initialPress == true){
								secondaryPress = !secondaryPress;
								printSR = printR;
								printSC = printC;
							}
							else {
								initialPress = !initialPress;
								printIR = printR;
								printIC = printC;
							}
							if (initialPress == true && secondaryPress == true){
								initialPress = false;
								secondaryPress = false;
								checkMate = ChessPiece.checkMate();
								universe.setCenter(createBoard());
								if(ChessPiece.checkForKing()){
									if(checkMate){
										System.out.println("CheckMate! lol won.");
										staplesCenter.close();
										showGG();
										checkMate = false;
									}
									else{
										System.out.println(ChessPiece.printColor + "King is in Check!");
									}
								}
								
								if(checkMate){
									System.out.println("Stalemate! No valid move can be made. It is a draw.");
									staplesCenter.close();
									showStalemate();
								}
								ChessPiece.movePiece(printIR, printIC, printSR, printSC);
								if (ChessPiece.ChessBoard[printSR][printSC] instanceof Pawn){
									if (printSR == 0 || printSR == 7){
										Pawn pawn = (Pawn) ChessPiece.ChessBoard[printSR][printSC];
										Zilker = new Stage();
										bumblebee = new GridPane();
										bumblebee.setHgap(10);
										bumblebee.setVgap(10);
										bumblebee.setPadding(new Insets(25, 25, 25, 25));
										
										Zilker.setTitle("Evolution");
										String winner = "";
										Label printGG = new Label("Whaat? It's Evolving! \nPlease select the type of the piece you would like to transform into");
										bumblebee.setConstraints(printGG, 0, 0);
										
										Button Q = new Button("Queen");
										bumblebee.setConstraints(Q, 0, 2);
										
										Button R = new Button("Rook");
										bumblebee.setConstraints(R, 0, 4);
										
										Button B = new Button("Bishop");
										bumblebee.setConstraints(B, 0, 6);
										
										Button K = new Button("Knight");
										bumblebee.setConstraints(K, 0, 8);
										
										bumblebee.getChildren().addAll(printGG, Q, R, B, K);
										
										bumblebee.setAlignment(Pos.CENTER);
										ACL = new Scene(bumblebee, 600, 300);
										Zilker.centerOnScreen();
										Zilker.setScene(ACL);
										Zilker.show();
										Q.setOnAction(new EventHandler<ActionEvent>() {

											@Override
											public void handle(ActionEvent event) {
												pawn.evolve("Queen");
												universe.setCenter(createBoard());
												Zilker.close();
												
											}
											
										});
										
										R.setOnAction(new EventHandler<ActionEvent>() {

											@Override
											public void handle(ActionEvent event) {
												pawn.evolve("Rook");
												universe.setCenter(createBoard());
												Zilker.close();
												
											}
											
										});
										
										B.setOnAction(new EventHandler<ActionEvent>() {

											@Override
											public void handle(ActionEvent event) {
												pawn.evolve("Bishop");
												universe.setCenter(createBoard());
												Zilker.close();
												
											}
											
										});
										
										K.setOnAction(new EventHandler<ActionEvent>() {

											@Override
											public void handle(ActionEvent event) {
												pawn.evolve("Knight");
												universe.setCenter(createBoard());
												Zilker.close();
												
											}
											
										});
									}
								}
								System.out.println(ChessPiece.printColor + " Turn");
								universe.setCenter(createBoard());
							}
						}
					});
				}
				
				//board.add(spot, col, row);
				//board.add(addButton, col, row);
			}
		}
		for (int i = 0; i < size; i++) {
            //board.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            //board.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
			board.getColumnConstraints().add(new ColumnConstraints(5, 300, 300, Priority.ALWAYS, HPos.CENTER, true));
            board.getRowConstraints().add(new RowConstraints(5, 300, 300, Priority.ALWAYS, VPos.CENTER, true));
		}
		
		return board;
	}
	
	public void showGG(){
		gg = new BorderPane();
		ATTStadium.setTitle("Game Over");
		String winner = "";
		if (ChessPiece.printColor == "white"){
			winner = "black";
		}
		else {
			winner = "white";
		}
		Label printGG = new Label("Checkmate: " + ChessPiece.printColor + " king is in checkmate \n" + winner + " wins!");
		gg.setCenter(printGG);
		georgeStrait = new Scene(gg, 300, 300);
		ATTStadium.centerOnScreen();
		ATTStadium.setScene(georgeStrait);
		ATTStadium.show();
	}
	
	public void showStalemate(){
		gg = new BorderPane();
		ATTStadium.setTitle("Game Over");
		Label printGG = new Label("Stalemate");
		gg.setCenter(printGG);
		georgeStrait = new Scene(gg, 300, 300);
		ATTStadium.centerOnScreen();
		ATTStadium.setScene(georgeStrait);
		ATTStadium.show();
	}
	
	@Override
	public void start(Stage primaryStage) {
		ChessPiece.initializeBoard();
		universe = new BorderPane();
		
		staplesCenter = primaryStage;
		staplesCenter.setTitle("Wizard's Chess");
		
		universe.setCenter(createBoard());
		//universe.setCenter(printBoard());
		
		tay1989 = new Scene(universe, 800, 800);
		
		staplesCenter.centerOnScreen();
		staplesCenter.setScene(tay1989);
		staplesCenter.show();
	}
	
	public static void main(String[] args) {
		ChessPiece.GUI = true;
		System.out.println("Welcome to Wizards Chess");
		System.out.println("White Turn");
		launch(args);
	}

}
