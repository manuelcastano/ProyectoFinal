package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import threads.ThreadBall;

public class WindowController implements Initializable{
	
	@FXML
	private BorderPane bp;
	@FXML
	private MenuButton mb;
	private ThreadBall tb;
	@FXML
	private AnchorPane operations;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Canvas c = new Canvas(70, 440);
		bp.setRight(c);
		GraphicsContext gc = c.getGraphicsContext2D();
		tb = new ThreadBall(gc, false);
		tb.start();
		MenuItem ball = new MenuItem("ball");
		mb.getItems().clear();
		mb.getItems().add(ball);
		ball.setOnAction(e -> {
			addBall();
		});
	}
	
	public void addBall() {
		Label color = new Label("Color");
		TextField colorT = new TextField();
		Label type = new Label("type");
		TextField typeT = new TextField();
		operations.getChildren().add(color);
		operations.getChildren().add(colorT);
		operations.getChildren().add(type);
		operations.getChildren().add(typeT);
	}
	
}
