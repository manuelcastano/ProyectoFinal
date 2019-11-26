package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import model.*;
import threads.ThreadBall;

public class WindowController implements Initializable{
	
	@FXML
	private BorderPane bp;
	@FXML
	private MenuButton mb;
	@FXML
	private MenuButton mb1;
	@FXML
	private MenuButton delete;
	@FXML
	private MenuButton order;
	private ThreadBall tb;
	@FXML
	private AnchorPane operations;
	private League league;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			league = new League();
		} catch (ClassNotFoundException | IOException | ElementExistException e1) {
			e1.printStackTrace();
		}
		Canvas c = new Canvas(70, 440);
		bp.setRight(c);
		GraphicsContext gc = c.getGraphicsContext2D();
		tb = new ThreadBall(gc, false);
		tb.start();
		MenuItem ball = new MenuItem("ball");
		MenuItem referee = new MenuItem("referee");
		MenuItem stadium = new MenuItem("stadium");
		MenuItem club = new MenuItem("club");
		MenuItem coach = new MenuItem("coach");
		MenuItem technicalAssistant = new MenuItem("technical assistant");
		MenuItem physicalTrainer = new MenuItem("physical trainer");
		MenuItem goalKeeper = new MenuItem("goalKeeper");
		MenuItem defense = new MenuItem("defense");
		MenuItem recovery = new MenuItem("recovery");
		MenuItem creation = new MenuItem("creation");
		MenuItem forward = new MenuItem("forward");
		mb.getItems().clear();
		mb.getItems().addAll(ball, referee, stadium, club, coach, technicalAssistant, physicalTrainer, goalKeeper, defense, recovery, creation, forward);
		ball.setOnAction(e -> {
			addBall();
		});
		referee.setOnAction(e -> {
			addReferee();
		});
		stadium.setOnAction(e -> {
			addStadium();
		});
		club.setOnAction(e -> {
			addClub();
		});
		coach.setOnAction(e -> {
			addCoach();
		});
		technicalAssistant.setOnAction(e -> {
			addTechnicalAssistant();
		});
		physicalTrainer.setOnAction(e -> {
			addPhysicalTrainer();
		});
		goalKeeper.setOnAction(e -> {
			addgoalKeeper();
		});
		defense.setOnAction(e -> {
			addDefense();
		});
		recovery.setOnAction(e -> {
			addRecovery();
		});
		creation.setOnAction(e -> {
			addCreation();
		});
		forward.setOnAction(e -> {
			addForward();
		});
		mb1.getItems().clear();
		MenuItem nameClub = new MenuItem("Name club");
		MenuItem goals = new MenuItem("Player goals");
		MenuItem foulsReferee = new MenuItem("Referee fouls");
		MenuItem typeBall = new MenuItem("Type ball");
		MenuItem stadiumName = new MenuItem("Stadium name");
		MenuItem wonGames = new MenuItem("Won games of a technical");
		mb1.getItems().addAll(nameClub, goals, foulsReferee, typeBall, stadiumName, wonGames);
		nameClub.setOnAction(e -> {
			updateNameClub();
		});
		goals.setOnAction(e -> {
			updateGoals();
		});
		foulsReferee.setOnAction(e -> {
			updateFoulsReferee();
		});
		typeBall.setOnAction(e -> {
			updateTypeBall();
		});
		stadiumName.setOnAction(e -> {
			updateStadiumName();
		});
		wonGames.setOnAction(e -> {
			updateWonGames();
		});
		delete.getItems().clear();
		MenuItem clubD = new MenuItem("Club");
		MenuItem playerD = new MenuItem("Player");
		MenuItem refereeD = new MenuItem("Referee");
		MenuItem ballD = new MenuItem("Ball");
		MenuItem technicalD = new MenuItem("Technical");
		MenuItem stadiumD = new MenuItem("Stadium");
		delete.getItems().addAll(clubD, playerD, refereeD, ballD, technicalD, stadiumD);
		clubD.setOnAction(e -> {
			deleteClub();
		});
		playerD.setOnAction(e -> {
			deletePlayer();
		});
		refereeD.setOnAction(e -> {
			deleteReferee();
		});
		ballD.setOnAction(e -> {
			deleteBall();
		});
		technicalD.setOnAction(e -> {
			deleteTechnical();
		});
		stadiumD.setOnAction(e -> {
			deleteStadium();
		});
		order.getItems().clear();
		MenuItem clubPoints = new MenuItem("Clubs by points");
		MenuItem playerGoals = new MenuItem("Players by goals");
		MenuItem playerAssists = new MenuItem("Players by assists");
		MenuItem stadiumCapacity = new MenuItem("Stadiums by capacity");
		MenuItem stadiumArea = new MenuItem("Stadiums by area");
		MenuItem clubName = new MenuItem("Clubs by name");
		order.getItems().addAll(clubPoints, playerGoals, playerAssists, stadiumCapacity, stadiumArea, clubName);
		clubPoints.setOnAction(e -> {
			orderClubsByPoints();
		});
		playerGoals.setOnAction(e -> {
			orderPlayersByGoals();
		});
		playerAssists.setOnAction(e -> {
			orderPlayersByAssists();
		});
		stadiumCapacity.setOnAction(e -> {
			orderStadiumsByCapacity();
		});
		stadiumArea.setOnAction(e -> {
			orderStadiumsByArea();
		});
		clubName.setOnAction(e -> {
			orderClubsByName();
		});
	}

	public void addBall() {
		operations.getChildren().clear();
		Label color = new Label("Color");
		color.setLayoutX(0);
		color.setLayoutY(0);
		TextField colorT = new TextField();
		colorT.setLayoutX(50);
		colorT.setLayoutY(0);
		Label type = new Label("type");
		type.setLayoutX(0);
		type.setLayoutY(30);
		TextField typeT = new TextField();
		typeT.setLayoutX(50);
		typeT.setLayoutY(30);
		Label id = new Label("id");
		id.setLayoutX(0);
		id.setLayoutY(60);
		TextField idT = new TextField();
		idT.setLayoutX(50);
		idT.setLayoutY(60);
		Button add = new Button("Add ball");
		add.setLayoutX(35);
		add.setLayoutY(100);
		Label error = new Label("The element already exist");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(130);
		add.setOnAction(e -> {
			String colorBall = colorT.getText();
			String typeBall = typeT.getText();
			String idBall = idT.getText();
			Ball b = new Ball(colorBall, typeBall, idBall);
			try {
				league.addBall(b);
				error.setVisible(false);
			} catch (ElementExistException e1) {
				error.setVisible(true);
			} catch (IOException e1) {
			}
		});
		operations.getChildren().add(color);
		operations.getChildren().add(colorT);
		operations.getChildren().add(type);
		operations.getChildren().add(typeT);
		operations.getChildren().add(id);
		operations.getChildren().add(idT);
		operations.getChildren().add(add);
		operations.getChildren().add(error);
	}
	
	public void addReferee() {
		operations.getChildren().clear();
		Label name = new Label("name");
		name.setLayoutX(0);
		name.setLayoutY(0);
		TextField nameT = new TextField();
		nameT.setLayoutX(50);
		nameT.setLayoutY(0);
		Label id = new Label("id");
		id.setLayoutX(0);
		id.setLayoutY(30);
		TextField idT = new TextField();
		idT.setLayoutX(50);
		idT.setLayoutY(30);
		Label salary = new Label("salary");
		salary.setLayoutX(0);
		salary.setLayoutY(60);
		TextField salaryT = new TextField();
		salaryT.setLayoutX(50);
		salaryT.setLayoutY(60);
		Label yellowCards = new Label("yellow cards");
		yellowCards.setLayoutX(-20);
		yellowCards.setLayoutY(90);
		TextField yellowCardsT = new TextField();
		yellowCardsT.setLayoutX(50);
		yellowCardsT.setLayoutY(90);
		Label redCards = new Label("red cards");
		redCards.setLayoutX(0);
		redCards.setLayoutY(120);
		TextField redCardsT = new TextField();
		redCardsT.setLayoutX(50);
		redCardsT.setLayoutY(120);
		Label fouls = new Label("fouls");
		fouls.setLayoutX(0);
		fouls.setLayoutY(150);
		TextField foulsT = new TextField();
		foulsT.setLayoutX(50);
		foulsT.setLayoutY(150);
		Button add = new Button("Add referee");
		add.setLayoutX(35);
		add.setLayoutY(190);
		Label error = new Label("The element already exist");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(220);
		Label syntaxError = new Label("Enter correct data please");
		syntaxError.setVisible(false);
		syntaxError.setLayoutX(35);
		syntaxError.setLayoutY(250);
		add.setOnAction(e -> {
			try {
				String nameReferee = nameT.getText();
				String idReferee = idT.getText();
				double salaryReferee = Double.parseDouble(salaryT.getText());
				int yellowCardsReferee = Integer.parseInt(yellowCardsT.getText());
				int redCardsReferee = Integer.parseInt(redCardsT.getText());
				int foulsReferee = Integer.parseInt(foulsT.getText());
				syntaxError.setVisible(false);
				Referee r = new Referee(nameReferee, idReferee, salaryReferee, yellowCardsReferee, redCardsReferee, foulsReferee);
				try {
					league.addReferee(r);
					error.setVisible(false);
				} catch (ElementExistException e1) {
					error.setVisible(true);
				} catch (IOException e1) {
				}
			}catch(Exception ex) {
				syntaxError.setVisible(true);
			}
		});
		operations.getChildren().add(name);
		operations.getChildren().add(nameT);
		operations.getChildren().add(id);
		operations.getChildren().add(idT);
		operations.getChildren().add(salary);
		operations.getChildren().add(salaryT);
		operations.getChildren().add(yellowCards);
		operations.getChildren().add(yellowCardsT);
		operations.getChildren().add(redCards);
		operations.getChildren().add(redCardsT);
		operations.getChildren().add(fouls);
		operations.getChildren().add(foulsT);
		operations.getChildren().add(add);
		operations.getChildren().add(error);
		operations.getChildren().add(syntaxError);
	}
	
	public void addStadium() {
		operations.getChildren().clear(); 
		Label name = new Label("name");
		name.setLayoutX(0);
		name.setLayoutY(0);
		TextField nameT = new TextField();
		nameT.setLayoutX(50);
		nameT.setLayoutY(0);
		Label capacity = new Label("capacity");
		capacity.setLayoutX(0);
		capacity.setLayoutY(30);
		TextField capacityT = new TextField();
		capacityT.setLayoutX(50);
		capacityT.setLayoutY(30);
		Label area = new Label("area");
		area.setLayoutX(0);
		area.setLayoutY(60);
		TextField areaT = new TextField();
		areaT.setLayoutX(50);
		areaT.setLayoutY(60);
		Button add = new Button("Add stadium");
		add.setLayoutX(35);
		add.setLayoutY(100);
		Label error = new Label("The element already exist");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(130);
		Label syntaxError = new Label("Enter correct data please");
		syntaxError.setVisible(false);
		syntaxError.setLayoutX(35);
		syntaxError.setLayoutY(160);
		add.setOnAction(e -> {
			try {
				String nameStadium = nameT.getText();
				int capacityStadium = Integer.parseInt(capacityT.getText());
				double areaStadium = Double.parseDouble(areaT.getText());
				syntaxError.setVisible(false);
				Stadium s = new Stadium(nameStadium, capacityStadium, areaStadium);
				try {
					league.addStadium(s);
					error.setVisible(false);
				} catch (ElementExistException e1) {
					error.setVisible(true);
				} catch (IOException e1) {
				}
			}catch(Exception ex) {
				syntaxError.setVisible(true);
			}
		});
		operations.getChildren().add(name);
		operations.getChildren().add(nameT);
		operations.getChildren().add(capacity);
		operations.getChildren().add(capacityT);
		operations.getChildren().add(area);
		operations.getChildren().add(areaT);
		operations.getChildren().add(add);
		operations.getChildren().add(error);
		operations.getChildren().add(syntaxError);
	}
	
	public void addClub() {
		operations.getChildren().clear(); 
		Label name = new Label("name");
		name.setLayoutX(0);
		name.setLayoutY(0);
		TextField nameT = new TextField();
		nameT.setLayoutX(50);
		nameT.setLayoutY(0);
		Label points = new Label("points");
		points.setLayoutX(0);
		points.setLayoutY(30);
		TextField pointsT = new TextField();
		pointsT.setLayoutX(50);
		pointsT.setLayoutY(30);
		Button add = new Button("Add club");
		add.setLayoutX(35);
		add.setLayoutY(70);
		Label error = new Label("The element already exist");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(100);
		Label syntaxError = new Label("Enter correct data please");
		syntaxError.setVisible(false);
		syntaxError.setLayoutX(35);
		syntaxError.setLayoutY(130);
		add.setOnAction(e -> {
			try {
				String nameClub = nameT.getText();
				int pointsClub = Integer.parseInt(pointsT.getText());
				Club c = new Club(nameClub, pointsClub);
				syntaxError.setVisible(false);
				try {
					league.addClub(c);
					error.setVisible(false);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ElementExistException e1) {
					error.setVisible(true);
				}
			} catch(Exception ex) {
				syntaxError.setVisible(true);
			}
		});
		operations.getChildren().add(name);
		operations.getChildren().add(nameT);
		operations.getChildren().add(points);
		operations.getChildren().add(pointsT);
		operations.getChildren().add(add);
		operations.getChildren().add(error);
		operations.getChildren().add(syntaxError);
	}
	
	public void addCoach() {
		operations.getChildren().clear();
		Label nameClub = new Label("name of the club");
		nameClub.setLayoutX(-50);
		nameClub.setLayoutY(0);
		TextField nameClubT = new TextField();
		nameClubT.setLayoutX(50);
		nameClubT.setLayoutY(0);
		Label name = new Label("name");
		name.setLayoutX(0);
		name.setLayoutY(30);
		TextField nameT = new TextField();
		nameT.setLayoutX(50);
		nameT.setLayoutY(30);
		Label id = new Label("id");
		id.setLayoutX(0);
		id.setLayoutY(60);
		TextField idT = new TextField();
		idT.setLayoutX(50);
		idT.setLayoutY(60);
		Label salary = new Label("salary");
		salary.setLayoutX(0);
		salary.setLayoutY(90);
		TextField salaryT = new TextField();
		salaryT.setLayoutX(50);
		salaryT.setLayoutY(90);
		Label hoursWorked = new Label("hours worked");
		hoursWorked.setLayoutX(-30);
		hoursWorked.setLayoutY(120);
		TextField hoursWorkedT = new TextField();
		hoursWorkedT.setLayoutX(50);
		hoursWorkedT.setLayoutY(120);
		Label wonGames = new Label("won games");
		wonGames.setLayoutX(-30);
		wonGames.setLayoutY(150);
		TextField wonGamesT = new TextField();
		wonGamesT.setLayoutX(50);
		wonGamesT.setLayoutY(150);
		Button add = new Button("Add coach");
		add.setLayoutX(35);
		add.setLayoutY(190);
		Label error = new Label("The element already exist");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(220);
		Label syntaxError = new Label("Enter correct data please");
		syntaxError.setVisible(false);
		syntaxError.setLayoutX(35);
		syntaxError.setLayoutY(250);
		add.setOnAction(e -> {
			try {
				String nameCoach = nameT.getText();
				String idCoach = idT.getText();
				double salaryCoach = Double.parseDouble(salaryT.getText());
				int hoursWorkedCoach = Integer.parseInt(hoursWorkedT.getText());
				int wonGamesCoach = Integer.parseInt(wonGamesT.getText());
				Technical t = new Coach(nameCoach, idCoach, salaryCoach, hoursWorkedCoach, wonGamesCoach);
				try {
					league.addTechnical(nameClubT.getText(), t);
					syntaxError.setVisible(false);
					error.setVisible(false);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ElementExistException e1) {
					error.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotFindedException e1) {
					syntaxError.setVisible(true);
				}
			} catch(Exception ex) {
				syntaxError.setVisible(true);
			}
		});
		operations.getChildren().add(nameClub);
		operations.getChildren().add(nameClubT);
		operations.getChildren().add(name);
		operations.getChildren().add(nameT);
		operations.getChildren().add(id);
		operations.getChildren().add(idT);
		operations.getChildren().add(salary);
		operations.getChildren().add(salaryT);
		operations.getChildren().add(hoursWorked);
		operations.getChildren().add(hoursWorkedT);
		operations.getChildren().add(wonGames);
		operations.getChildren().add(wonGamesT);
		operations.getChildren().add(add);
		operations.getChildren().add(error);
		operations.getChildren().add(syntaxError);
	}
	
	public void addTechnicalAssistant() {
		operations.getChildren().clear();
		Label nameClub = new Label("name of the club");
		nameClub.setLayoutX(-50);
		nameClub.setLayoutY(0);
		TextField nameClubT = new TextField();
		nameClubT.setLayoutX(50);
		nameClubT.setLayoutY(0);
		Label name = new Label("name");
		name.setLayoutX(0);
		name.setLayoutY(30);
		TextField nameT = new TextField();
		nameT.setLayoutX(50);
		nameT.setLayoutY(30);
		Label id = new Label("id");
		id.setLayoutX(0);
		id.setLayoutY(60);
		TextField idT = new TextField();
		idT.setLayoutX(50);
		idT.setLayoutY(60);
		Label salary = new Label("salary");
		salary.setLayoutX(0);
		salary.setLayoutY(90);
		TextField salaryT = new TextField();
		salaryT.setLayoutX(50);
		salaryT.setLayoutY(90);
		Label hoursWorked = new Label("hours worked");
		hoursWorked.setLayoutX(-30);
		hoursWorked.setLayoutY(120);
		TextField hoursWorkedT = new TextField();
		hoursWorkedT.setLayoutX(50);
		hoursWorkedT.setLayoutY(120);
		Label plannedPlays = new Label("planned plays");
		plannedPlays.setLayoutX(-30);
		plannedPlays.setLayoutY(150);
		TextField plannedPlaysT = new TextField();
		plannedPlaysT.setLayoutX(50);
		plannedPlaysT.setLayoutY(150);
		Button add = new Button("Add technical assistant");
		add.setLayoutX(35);
		add.setLayoutY(190);
		Label error = new Label("The element already exist");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(220);
		Label syntaxError = new Label("Enter correct data please");
		syntaxError.setVisible(false);
		syntaxError.setLayoutX(35);
		syntaxError.setLayoutY(250);
		add.setOnAction(e -> {
			try {
				String nameCoach = nameT.getText();
				String idCoach = idT.getText();
				double salaryCoach = Double.parseDouble(salaryT.getText());
				int hoursWorkedCoach = Integer.parseInt(hoursWorkedT.getText());
				int plannedPlaysCoach = Integer.parseInt(plannedPlaysT.getText());
				Technical t = new TechnicalAssistant(nameCoach, idCoach, salaryCoach, hoursWorkedCoach, plannedPlaysCoach);
				try {
					league.addTechnical(nameClubT.getText(), t);
					syntaxError.setVisible(false);
					error.setVisible(false);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ElementExistException e1) {
					error.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotFindedException e1) {
					syntaxError.setVisible(true);
				}
			} catch(Exception ex) {
				syntaxError.setVisible(true);
			}
		});
		operations.getChildren().add(nameClub);
		operations.getChildren().add(nameClubT);
		operations.getChildren().add(name);
		operations.getChildren().add(nameT);
		operations.getChildren().add(id);
		operations.getChildren().add(idT);
		operations.getChildren().add(salary);
		operations.getChildren().add(salaryT);
		operations.getChildren().add(hoursWorked);
		operations.getChildren().add(hoursWorkedT);
		operations.getChildren().add(plannedPlays);
		operations.getChildren().add(plannedPlaysT);
		operations.getChildren().add(add);
		operations.getChildren().add(error);
		operations.getChildren().add(syntaxError);
	}
	
	public void addPhysicalTrainer() {
		operations.getChildren().clear();
		Label nameClub = new Label("name of the club");
		nameClub.setLayoutX(-50);
		nameClub.setLayoutY(0);
		TextField nameClubT = new TextField();
		nameClubT.setLayoutX(50);
		nameClubT.setLayoutY(0);
		Label name = new Label("name");
		name.setLayoutX(0);
		name.setLayoutY(30);
		TextField nameT = new TextField();
		nameT.setLayoutX(50);
		nameT.setLayoutY(30);
		Label id = new Label("id");
		id.setLayoutX(0);
		id.setLayoutY(60);
		TextField idT = new TextField();
		idT.setLayoutX(50);
		idT.setLayoutY(60);
		Label salary = new Label("salary");
		salary.setLayoutX(0);
		salary.setLayoutY(90);
		TextField salaryT = new TextField();
		salaryT.setLayoutX(50);
		salaryT.setLayoutY(90);
		Label hoursWorked = new Label("hours worked");
		hoursWorked.setLayoutX(-30);
		hoursWorked.setLayoutY(120);
		TextField hoursWorkedT = new TextField();
		hoursWorkedT.setLayoutX(50);
		hoursWorkedT.setLayoutY(120);
		Label injuredPlayers = new Label("injured players");
		injuredPlayers.setLayoutX(-30);
		injuredPlayers.setLayoutY(150);
		TextField injuredPlayersT = new TextField();
		injuredPlayersT.setLayoutX(50);
		injuredPlayersT.setLayoutY(150);
		Button add = new Button("Add physical trainer");
		add.setLayoutX(35);
		add.setLayoutY(190);
		Label error = new Label("The element already exist");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(220);
		Label syntaxError = new Label("Enter correct data please");
		syntaxError.setVisible(false);
		syntaxError.setLayoutX(35);
		syntaxError.setLayoutY(250);
		add.setOnAction(e -> {
			try {
				String nameCoach = nameT.getText();
				String idCoach = idT.getText();
				double salaryCoach = Double.parseDouble(salaryT.getText());
				int hoursWorkedCoach = Integer.parseInt(hoursWorkedT.getText());
				int injuredPlayersCoach = Integer.parseInt(injuredPlayersT.getText());
				Technical t = new PhysicalTrainer(nameCoach, idCoach, salaryCoach, hoursWorkedCoach, injuredPlayersCoach);
				try {
					league.addTechnical(nameClubT.getText(), t);
					syntaxError.setVisible(false);
					error.setVisible(false);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ElementExistException e1) {
					error.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotFindedException e1) {
					syntaxError.setVisible(true);
				}
			} catch(Exception ex) {
				syntaxError.setVisible(true);
			}
		});
		operations.getChildren().add(nameClub);
		operations.getChildren().add(nameClubT);
		operations.getChildren().add(name);
		operations.getChildren().add(nameT);
		operations.getChildren().add(id);
		operations.getChildren().add(idT);
		operations.getChildren().add(salary);
		operations.getChildren().add(salaryT);
		operations.getChildren().add(hoursWorked);
		operations.getChildren().add(hoursWorkedT);
		operations.getChildren().add(injuredPlayers);
		operations.getChildren().add(injuredPlayersT);
		operations.getChildren().add(add);
		operations.getChildren().add(error);
		operations.getChildren().add(syntaxError);
	}
	
	public void addgoalKeeper() {
		operations.getChildren().clear();
		Label nameClub = new Label("name of the club");
		nameClub.setLayoutX(-50);
		nameClub.setLayoutY(0);
		TextField nameClubT = new TextField();
		nameClubT.setLayoutX(50);
		nameClubT.setLayoutY(0);
		Label name = new Label("name");
		name.setLayoutX(0);
		name.setLayoutY(30);
		TextField nameT = new TextField();
		nameT.setLayoutX(50);
		nameT.setLayoutY(30);
		Label id = new Label("id");
		id.setLayoutX(0);
		id.setLayoutY(60);
		TextField idT = new TextField();
		idT.setLayoutX(50);
		idT.setLayoutY(60);
		Label salary = new Label("salary");
		salary.setLayoutX(0);
		salary.setLayoutY(90);
		TextField salaryT = new TextField();
		salaryT.setLayoutX(50);
		salaryT.setLayoutY(90);
		Label yellowCards = new Label("yellow cards");
		yellowCards.setLayoutX(-20);
		yellowCards.setLayoutY(120);
		TextField yellowCardsT = new TextField();
		yellowCardsT.setLayoutX(50);
		yellowCardsT.setLayoutY(120);
		Label redCards = new Label("red cards");
		redCards.setLayoutX(0);
		redCards.setLayoutY(150);
		TextField redCardsT = new TextField();
		redCardsT.setLayoutX(50);
		redCardsT.setLayoutY(150);
		Label goals = new Label("goals");
		goals.setLayoutX(0);
		goals.setLayoutY(180);
		TextField goalsT = new TextField();
		goalsT.setLayoutX(50);
		goalsT.setLayoutY(180);
		Label assists = new Label("assists");
		assists.setLayoutX(0);
		assists.setLayoutY(210);
		TextField assistsT = new TextField();
		assistsT.setLayoutX(50);
		assistsT.setLayoutY(210);
		Label saved = new Label("saved");
		saved.setLayoutX(0);
		saved.setLayoutY(240);
		TextField savedT = new TextField();
		savedT.setLayoutX(50);
		savedT.setLayoutY(240);
		Button add = new Button("Add goalkeeper");
		add.setLayoutX(35);
		add.setLayoutY(280);
		Label error = new Label("The element already exist");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(310);
		Label syntaxError = new Label("Enter correct data please");
		syntaxError.setVisible(false);
		syntaxError.setLayoutX(35);
		syntaxError.setLayoutY(340);
		add.setOnAction(e -> {
			try {
				String namePlayer = nameT.getText();
				String idPlayer = idT.getText();
				double salaryPlayer = Double.parseDouble(salaryT.getText());
				int yellowCardsPlayer = Integer.parseInt(yellowCardsT.getText());
				int redCardsPlayer = Integer.parseInt(redCardsT.getText());
				int goalsPlayer = Integer.parseInt(goalsT.getText());
				int assistsPlayer = Integer.parseInt(assistsT.getText());
				int savedPlayer = Integer.parseInt(savedT.getText());
				Player p = new GoalKeeper(namePlayer, idPlayer, salaryPlayer, yellowCardsPlayer, redCardsPlayer, goalsPlayer, assistsPlayer, savedPlayer);
				try {
					league.addPlayer(nameClubT.getText(), p);
					error.setVisible(false);
					syntaxError.setVisible(false);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (ElementExistException e1) {
					error.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} catch(Exception ex) {
				syntaxError.setVisible(true);
			}
		});
		operations.getChildren().addAll(nameClub, nameClubT, name, nameT, id, idT, salary, salaryT, yellowCards, yellowCardsT, redCards, redCardsT, goals, goalsT, assists, assistsT, saved, savedT, add, error, syntaxError);
	}
	
	public void addDefense() {
		operations.getChildren().clear();
		Label nameClub = new Label("name of the club");
		nameClub.setLayoutX(-50);
		nameClub.setLayoutY(0);
		TextField nameClubT = new TextField();
		nameClubT.setLayoutX(50);
		nameClubT.setLayoutY(0);
		Label name = new Label("name");
		name.setLayoutX(0);
		name.setLayoutY(30);
		TextField nameT = new TextField();
		nameT.setLayoutX(50);
		nameT.setLayoutY(30);
		Label id = new Label("id");
		id.setLayoutX(0);
		id.setLayoutY(60);
		TextField idT = new TextField();
		idT.setLayoutX(50);
		idT.setLayoutY(60);
		Label salary = new Label("salary");
		salary.setLayoutX(0);
		salary.setLayoutY(90);
		TextField salaryT = new TextField();
		salaryT.setLayoutX(50);
		salaryT.setLayoutY(90);
		Label yellowCards = new Label("yellow cards");
		yellowCards.setLayoutX(-20);
		yellowCards.setLayoutY(120);
		TextField yellowCardsT = new TextField();
		yellowCardsT.setLayoutX(50);
		yellowCardsT.setLayoutY(120);
		Label redCards = new Label("red cards");
		redCards.setLayoutX(0);
		redCards.setLayoutY(150);
		TextField redCardsT = new TextField();
		redCardsT.setLayoutX(50);
		redCardsT.setLayoutY(150);
		Label goals = new Label("goals");
		goals.setLayoutX(0);
		goals.setLayoutY(180);
		TextField goalsT = new TextField();
		goalsT.setLayoutX(50);
		goalsT.setLayoutY(180);
		Label assists = new Label("assists");
		assists.setLayoutX(0);
		assists.setLayoutY(210);
		TextField assistsT = new TextField();
		assistsT.setLayoutX(50);
		assistsT.setLayoutY(210);
		Label recoveries = new Label("recoveries");
		recoveries.setLayoutX(-5);
		recoveries.setLayoutY(240);
		TextField recoveriesT = new TextField();
		recoveriesT.setLayoutX(50);
		recoveriesT.setLayoutY(240);
		Button add = new Button("Add defense");
		add.setLayoutX(35);
		add.setLayoutY(280);
		Label error = new Label("The element already exist");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(310);
		Label syntaxError = new Label("Enter correct data please");
		syntaxError.setVisible(false);
		syntaxError.setLayoutX(35);
		syntaxError.setLayoutY(340);
		add.setOnAction(e -> {
			try {
				String namePlayer = nameT.getText();
				String idPlayer = idT.getText();
				double salaryPlayer = Double.parseDouble(salaryT.getText());
				int yellowCardsPlayer = Integer.parseInt(yellowCardsT.getText());
				int redCardsPlayer = Integer.parseInt(redCardsT.getText());
				int goalsPlayer = Integer.parseInt(goalsT.getText());
				int assistsPlayer = Integer.parseInt(assistsT.getText());
				int recoveriesPlayer = Integer.parseInt(recoveriesT.getText());
				Player p = new Defense(namePlayer, idPlayer, salaryPlayer, yellowCardsPlayer, redCardsPlayer, goalsPlayer, assistsPlayer, recoveriesPlayer);
				try {
					league.addPlayer(nameClubT.getText(), p);
					error.setVisible(false);
					syntaxError.setVisible(false);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (ElementExistException e1) {
					error.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} catch(Exception ex) {
				syntaxError.setVisible(true);
			}
		});
		operations.getChildren().addAll(nameClub, nameClubT, name, nameT, id, idT, salary, salaryT, yellowCards, yellowCardsT, redCards, redCardsT, goals, goalsT, assists, assistsT, recoveries, recoveriesT, add, error, syntaxError);
	}
	
	public void addRecovery() {
		operations.getChildren().clear();
		Label nameClub = new Label("name of the club");
		nameClub.setLayoutX(-50);
		nameClub.setLayoutY(0);
		TextField nameClubT = new TextField();
		nameClubT.setLayoutX(50);
		nameClubT.setLayoutY(0);
		Label name = new Label("name");
		name.setLayoutX(0);
		name.setLayoutY(30);
		TextField nameT = new TextField();
		nameT.setLayoutX(50);
		nameT.setLayoutY(30);
		Label id = new Label("id");
		id.setLayoutX(0);
		id.setLayoutY(60);
		TextField idT = new TextField();
		idT.setLayoutX(50);
		idT.setLayoutY(60);
		Label salary = new Label("salary");
		salary.setLayoutX(0);
		salary.setLayoutY(90);
		TextField salaryT = new TextField();
		salaryT.setLayoutX(50);
		salaryT.setLayoutY(90);
		Label yellowCards = new Label("yellow cards");
		yellowCards.setLayoutX(-20);
		yellowCards.setLayoutY(120);
		TextField yellowCardsT = new TextField();
		yellowCardsT.setLayoutX(50);
		yellowCardsT.setLayoutY(120);
		Label redCards = new Label("red cards");
		redCards.setLayoutX(0);
		redCards.setLayoutY(150);
		TextField redCardsT = new TextField();
		redCardsT.setLayoutX(50);
		redCardsT.setLayoutY(150);
		Label goals = new Label("goals");
		goals.setLayoutX(0);
		goals.setLayoutY(180);
		TextField goalsT = new TextField();
		goalsT.setLayoutX(50);
		goalsT.setLayoutY(180);
		Label assists = new Label("assists");
		assists.setLayoutX(0);
		assists.setLayoutY(210);
		TextField assistsT = new TextField();
		assistsT.setLayoutX(50);
		assistsT.setLayoutY(210);
		Label passes = new Label("passes");
		passes.setLayoutX(-5);
		passes.setLayoutY(240);
		TextField passesT = new TextField();
		passesT.setLayoutX(50);
		passesT.setLayoutY(240);
		Label recoveries = new Label("recoveries");
		recoveries.setLayoutX(-5);
		recoveries.setLayoutY(270);
		TextField recoveriesT = new TextField();
		recoveriesT.setLayoutX(50);
		recoveriesT.setLayoutY(270);
		Button add = new Button("Add recovery");
		add.setLayoutX(35);
		add.setLayoutY(310);
		Label error = new Label("The element already exist");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(340);
		Label syntaxError = new Label("Enter correct data please");
		syntaxError.setVisible(false);
		syntaxError.setLayoutX(35);
		syntaxError.setLayoutY(370);
		add.setOnAction(e -> {
			try {
				String namePlayer = nameT.getText();
				String idPlayer = idT.getText();
				double salaryPlayer = Double.parseDouble(salaryT.getText());
				int yellowCardsPlayer = Integer.parseInt(yellowCardsT.getText());
				int redCardsPlayer = Integer.parseInt(redCardsT.getText());
				int goalsPlayer = Integer.parseInt(goalsT.getText());
				int assistsPlayer = Integer.parseInt(assistsT.getText());
				int passesPlayer = Integer.parseInt(passesT.getText());
				int recoveriesPlayer = Integer.parseInt(recoveriesT.getText());
				Player p = new Recovery(namePlayer, idPlayer, salaryPlayer, yellowCardsPlayer, redCardsPlayer, goalsPlayer, assistsPlayer, passesPlayer, recoveriesPlayer);
				try {
					league.addPlayer(nameClubT.getText(), p);
					error.setVisible(false);
					syntaxError.setVisible(false);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (ElementExistException e1) {
					error.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} catch(Exception ex) {
				syntaxError.setVisible(true);
			}
		});
		operations.getChildren().addAll(nameClub, nameClubT, name, nameT, id, idT, salary, salaryT, yellowCards, yellowCardsT, redCards, redCardsT, goals, goalsT, assists, assistsT, passes, passesT, recoveries, recoveriesT, add, error, syntaxError);
	}
	
	public void addCreation() {
		operations.getChildren().clear();
		Label nameClub = new Label("name of the club");
		nameClub.setLayoutX(-50);
		nameClub.setLayoutY(0);
		TextField nameClubT = new TextField();
		nameClubT.setLayoutX(50);
		nameClubT.setLayoutY(0);
		Label name = new Label("name");
		name.setLayoutX(0);
		name.setLayoutY(30);
		TextField nameT = new TextField();
		nameT.setLayoutX(50);
		nameT.setLayoutY(30);
		Label id = new Label("id");
		id.setLayoutX(0);
		id.setLayoutY(60);
		TextField idT = new TextField();
		idT.setLayoutX(50);
		idT.setLayoutY(60);
		Label salary = new Label("salary");
		salary.setLayoutX(0);
		salary.setLayoutY(90);
		TextField salaryT = new TextField();
		salaryT.setLayoutX(50);
		salaryT.setLayoutY(90);
		Label yellowCards = new Label("yellow cards");
		yellowCards.setLayoutX(-20);
		yellowCards.setLayoutY(120);
		TextField yellowCardsT = new TextField();
		yellowCardsT.setLayoutX(50);
		yellowCardsT.setLayoutY(120);
		Label redCards = new Label("red cards");
		redCards.setLayoutX(0);
		redCards.setLayoutY(150);
		TextField redCardsT = new TextField();
		redCardsT.setLayoutX(50);
		redCardsT.setLayoutY(150);
		Label goals = new Label("goals");
		goals.setLayoutX(0);
		goals.setLayoutY(180);
		TextField goalsT = new TextField();
		goalsT.setLayoutX(50);
		goalsT.setLayoutY(180);
		Label assists = new Label("assists");
		assists.setLayoutX(0);
		assists.setLayoutY(210);
		TextField assistsT = new TextField();
		assistsT.setLayoutX(50);
		assistsT.setLayoutY(210);
		Label passes = new Label("passes");
		passes.setLayoutX(-5);
		passes.setLayoutY(240);
		TextField passesT = new TextField();
		passesT.setLayoutX(50);
		passesT.setLayoutY(240);
		Label createdOccassions = new Label("created occassions");
		createdOccassions.setLayoutX(-50);
		createdOccassions.setLayoutY(270);
		TextField createdOccassionsT = new TextField();
		createdOccassionsT.setLayoutX(50);
		createdOccassionsT.setLayoutY(270);
		Button add = new Button("Add creation");
		add.setLayoutX(35);
		add.setLayoutY(310);
		Label error = new Label("The element already exist");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(340);
		Label syntaxError = new Label("Enter correct data please");
		syntaxError.setVisible(false);
		syntaxError.setLayoutX(35);
		syntaxError.setLayoutY(370);
		add.setOnAction(e -> {
			try {
				String namePlayer = nameT.getText();
				String idPlayer = idT.getText();
				double salaryPlayer = Double.parseDouble(salaryT.getText());
				int yellowCardsPlayer = Integer.parseInt(yellowCardsT.getText());
				int redCardsPlayer = Integer.parseInt(redCardsT.getText());
				int goalsPlayer = Integer.parseInt(goalsT.getText());
				int assistsPlayer = Integer.parseInt(assistsT.getText());
				int passesPlayer = Integer.parseInt(passesT.getText());
				int createdOccassionsPlayer = Integer.parseInt(createdOccassionsT.getText());
				Player p = new Creation(namePlayer, idPlayer, salaryPlayer, yellowCardsPlayer, redCardsPlayer, goalsPlayer, assistsPlayer, passesPlayer, createdOccassionsPlayer);
				try {
					league.addPlayer(nameClubT.getText(), p);
					error.setVisible(false);
					syntaxError.setVisible(false);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (ElementExistException e1) {
					error.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} catch(Exception ex) {
				syntaxError.setVisible(true);
			}
		});
		operations.getChildren().addAll(nameClub, nameClubT, name, nameT, id, idT, salary, salaryT, yellowCards, yellowCardsT, redCards, redCardsT, goals, goalsT, assists, assistsT, passes, passesT, createdOccassions, createdOccassionsT, add, error, syntaxError);
	}
	
	public void addForward() {
		operations.getChildren().clear();
		Label nameClub = new Label("name of the club");
		nameClub.setLayoutX(-50);
		nameClub.setLayoutY(0);
		TextField nameClubT = new TextField();
		nameClubT.setLayoutX(50);
		nameClubT.setLayoutY(0);
		Label name = new Label("name");
		name.setLayoutX(0);
		name.setLayoutY(30);
		TextField nameT = new TextField();
		nameT.setLayoutX(50);
		nameT.setLayoutY(30);
		Label id = new Label("id");
		id.setLayoutX(0);
		id.setLayoutY(60);
		TextField idT = new TextField();
		idT.setLayoutX(50);
		idT.setLayoutY(60);
		Label salary = new Label("salary");
		salary.setLayoutX(0);
		salary.setLayoutY(90);
		TextField salaryT = new TextField();
		salaryT.setLayoutX(50);
		salaryT.setLayoutY(90);
		Label yellowCards = new Label("yellow cards");
		yellowCards.setLayoutX(-20);
		yellowCards.setLayoutY(120);
		TextField yellowCardsT = new TextField();
		yellowCardsT.setLayoutX(50);
		yellowCardsT.setLayoutY(120);
		Label redCards = new Label("red cards");
		redCards.setLayoutX(0);
		redCards.setLayoutY(150);
		TextField redCardsT = new TextField();
		redCardsT.setLayoutX(50);
		redCardsT.setLayoutY(150);
		Label goals = new Label("goals");
		goals.setLayoutX(0);
		goals.setLayoutY(180);
		TextField goalsT = new TextField();
		goalsT.setLayoutX(50);
		goalsT.setLayoutY(180);
		Label assists = new Label("assists");
		assists.setLayoutX(0);
		assists.setLayoutY(210);
		TextField assistsT = new TextField();
		assistsT.setLayoutX(50);
		assistsT.setLayoutY(210);
		Label kicksToGoal = new Label("kicks to goal");
		kicksToGoal.setLayoutX(-30);
		kicksToGoal.setLayoutY(240);
		TextField kicksToGoalT = new TextField();
		kicksToGoalT.setLayoutX(50);
		kicksToGoalT.setLayoutY(240);
		Button add = new Button("Add forward");
		add.setLayoutX(35);
		add.setLayoutY(280);
		Label error = new Label("The element already exist");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(310);
		Label syntaxError = new Label("Enter correct data please");
		syntaxError.setVisible(false);
		syntaxError.setLayoutX(35);
		syntaxError.setLayoutY(340);
		add.setOnAction(e -> {
			try {
				String namePlayer = nameT.getText();
				String idPlayer = idT.getText();
				double salaryPlayer = Double.parseDouble(salaryT.getText());
				int yellowCardsPlayer = Integer.parseInt(yellowCardsT.getText());
				int redCardsPlayer = Integer.parseInt(redCardsT.getText());
				int goalsPlayer = Integer.parseInt(goalsT.getText());
				int assistsPlayer = Integer.parseInt(assistsT.getText());
				int kicksToGoalPlayer = Integer.parseInt(kicksToGoalT.getText());
				Player p = new Defense(namePlayer, idPlayer, salaryPlayer, yellowCardsPlayer, redCardsPlayer, goalsPlayer, assistsPlayer, kicksToGoalPlayer);
				try {
					league.addPlayer(nameClubT.getText(), p);
					error.setVisible(false);
					syntaxError.setVisible(false);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (ElementExistException e1) {
					error.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} catch(Exception ex) {
				syntaxError.setVisible(true);
			}
		});
		operations.getChildren().addAll(nameClub, nameClubT, name, nameT, id, idT, salary, salaryT, yellowCards, yellowCardsT, redCards, redCardsT, goals, goalsT, assists, assistsT, kicksToGoal, kicksToGoalT, add, error, syntaxError);
	}
	
	public void updateNameClub() {
		operations.getChildren().clear();
		Label nameClub = new Label("Name of the club");
		nameClub.setLayoutX(-50);
		nameClub.setLayoutY(0);
		TextField nameClubT = new TextField();
		nameClubT.setLayoutX(50);
		nameClubT.setLayoutY(0);
		Label newName = new Label("New name");
		newName.setLayoutX(-20);
		newName.setLayoutY(30);
		TextField newNameT = new TextField();
		newNameT.setLayoutX(50);
		newNameT.setLayoutY(30);
		Button add = new Button("Update name");
		add.setLayoutX(35);
		add.setLayoutY(70);
		Label error = new Label("The element doesn't exist");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(100);
		add.setOnAction(e -> {
			String theName = nameClubT.getText();
			String theNewName = newNameT.getText();
			try {
				league.updateNameClub(theName, theNewName);
				error.setVisible(false);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (NotFindedException e1) {
				error.setVisible(true);
			}
		});
		operations.getChildren().addAll(nameClub, nameClubT, newName, newNameT, add, error);
	}
	
	public void updateGoals() {
		operations.getChildren().clear();
		Label nameClub = new Label("Name of the club");
		nameClub.setLayoutX(-50);
		nameClub.setLayoutY(0);
		TextField nameClubT = new TextField();
		nameClubT.setLayoutX(50);
		nameClubT.setLayoutY(0);
		Label namePlayer = new Label("Name of the player");
		namePlayer.setLayoutX(-55);
		namePlayer.setLayoutY(30);
		TextField namePlayerT = new TextField();
		namePlayerT.setLayoutX(50);
		namePlayerT.setLayoutY(30);
		Label numberGoals = new Label("Number of goals");
		numberGoals.setLayoutX(-50);
		numberGoals.setLayoutY(60);
		TextField numberGoalsT = new TextField();
		numberGoalsT.setLayoutX(50);
		numberGoalsT.setLayoutY(60);
		Button add = new Button("Update goals");
		add.setLayoutX(35);
		add.setLayoutY(100);
		Label error = new Label("The element doesn't exist");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(130);
		Label syntaxError = new Label("Enter correct data please");
		syntaxError.setVisible(false);
		syntaxError.setLayoutX(35);
		syntaxError.setLayoutY(160);
		add.setOnAction(e -> {
			try {
				String nameClubN = nameClubT.getText();
				String namePlayerN = namePlayerT.getText();
				int goals = Integer.parseInt(numberGoalsT.getText());
				syntaxError.setVisible(false);
				try {
					league.updateNumberGoalsPlayer(nameClubN, namePlayerN, goals);;
					error.setVisible(false);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (NotFindedException e1) {
					error.setVisible(true);
				}
			} catch(Exception ex) {
				syntaxError.setVisible(true);
			}
		});
		operations.getChildren().addAll(nameClub, nameClubT, namePlayer, namePlayerT, numberGoals, numberGoalsT, add, error, syntaxError);
	}
	
	public void updateFoulsReferee() {
		operations.getChildren().clear();
		Label idReferee = new Label("Id of the referee");
		idReferee.setLayoutX(-50);
		idReferee.setLayoutY(0);
		TextField idRefereeT = new TextField();
		idRefereeT.setLayoutX(50);
		idRefereeT.setLayoutY(0);
		Label fouls = new Label("Number of fouls");
		fouls.setLayoutX(-55);
		fouls.setLayoutY(30);
		TextField foulsT = new TextField();
		foulsT.setLayoutX(50);
		foulsT.setLayoutY(30);
		Button add = new Button("Update fouls");
		add.setLayoutX(35);
		add.setLayoutY(70);
		Label error = new Label("The element doesn't exist");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(100);
		Label syntaxError = new Label("Enter correct data please");
		syntaxError.setVisible(false);
		syntaxError.setLayoutX(35);
		syntaxError.setLayoutY(130);
		add.setOnAction(e -> {
			try {
				String idRefereeN = idRefereeT.getText();
				int foulsN = Integer.parseInt(foulsT.getText());
				league.updateNumberFouls(idRefereeN, foulsN);
				syntaxError.setVisible(false);
				try {
					league.updateNumberFouls(idRefereeN, foulsN);
					error.setVisible(false);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (NotFindedException e1) {
					error.setVisible(true);
				}
			} catch(Exception ex) {
				syntaxError.setVisible(true);
			}
		});
		operations.getChildren().addAll(idReferee, idRefereeT, fouls, foulsT, add, error, syntaxError);
	}
	
	public void updateTypeBall() {
		operations.getChildren().clear();
		Label id = new Label("Id");
		id.setLayoutX(0);
		id.setLayoutY(0);
		TextField idT = new TextField();
		idT.setLayoutX(50);
		idT.setLayoutY(0);
		Label type = new Label("New type");
		type.setLayoutX(-5);
		type.setLayoutY(30);
		TextField typeT = new TextField();
		typeT.setLayoutX(50);
		typeT.setLayoutY(30);
		Button add = new Button("Update type");
		add.setLayoutX(35);
		add.setLayoutY(70);
		Label error = new Label("The element doesn't exist");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(100);
		add.setOnAction(e -> {
			String idBall = idT.getText();
			String typeBall = typeT.getText();
			try {
				league.updateTypeBall(idBall, typeBall);
				error.setVisible(false);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (NotFindedException e1) {
				error.setVisible(true);
			}
		});
		operations.getChildren().addAll(id, idT, type, typeT, add, error);
	}
	
	public void updateStadiumName() {
		operations.getChildren().clear();
		Label name = new Label("Name");
		name.setLayoutX(0);
		name.setLayoutY(0);
		TextField nameT = new TextField();
		nameT.setLayoutX(50);
		nameT.setLayoutY(0);
		Label newName = new Label("New name");
		newName.setLayoutX(-8);
		newName.setLayoutY(30);
		TextField newNameT = new TextField();
		newNameT.setLayoutX(50);
		newNameT.setLayoutY(30);
		Button add = new Button("Update name");
		add.setLayoutX(35);
		add.setLayoutY(70);
		Label error = new Label("The element doesn't exist");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(100);
		add.setOnAction(e -> {
			String nameStadium = nameT.getText();
			String newNameStadium = newNameT.getText();
			try {
				league.updateNameStadium(nameStadium, newNameStadium);
				error.setVisible(false);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (NotFindedException e1) {
				error.setVisible(true);
			}
		});
		operations.getChildren().addAll(name, nameT, newName, newNameT, add, error);
	}
	
	public void updateWonGames() {
		operations.getChildren().clear();
		Label name = new Label("Name");
		name.setLayoutX(0);
		name.setLayoutY(0);
		TextField nameT = new TextField();
		nameT.setLayoutX(50);
		nameT.setLayoutY(0);
		Label wonGames = new Label("Won games");
		wonGames.setLayoutX(-20);
		wonGames.setLayoutY(30);
		TextField wonGamesT = new TextField();
		wonGamesT.setLayoutX(50);
		wonGamesT.setLayoutY(30);
		Label clubName = new Label("Club name");
		clubName.setLayoutX(-10);
		clubName.setLayoutY(60);
		TextField clubNameT = new TextField();
		clubNameT.setLayoutX(50);
		clubNameT.setLayoutY(60);
		Button add = new Button("Update won games");
		add.setLayoutX(35);
		add.setLayoutY(100);
		Label error = new Label("The element doesn't exist");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(130);
		Label syntaxError = new Label("Enter correct data");
		syntaxError.setVisible(false);
		syntaxError.setLayoutX(35);
		syntaxError.setLayoutY(160);
		add.setOnAction(e -> {
			try {
				String nameTechnical = nameT.getText();
				int wonGamesTechnical = Integer.parseInt(wonGamesT.getText());
				String club = clubNameT.getText();
				syntaxError.setVisible(false);
				try {
					league.updateWonGames(nameTechnical, wonGamesTechnical, club);
					error.setVisible(false);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (NotFindedException e1) {
					error.setVisible(true);
				}
			} catch(Exception ex) {
				syntaxError.setVisible(true);
			}
		});
		operations.getChildren().addAll(name, nameT, wonGames, wonGamesT, clubName, clubNameT, add, error, syntaxError);
	}
	
	public void deleteClub() {
		operations.getChildren().clear();
		Label name = new Label("Name");
		name.setLayoutX(0);
		name.setLayoutY(0);
		TextField nameT = new TextField();
		nameT.setLayoutX(50);
		nameT.setLayoutY(0);
		Button add = new Button("Delete club");
		add.setLayoutX(35);
		add.setLayoutY(40);
		Label error = new Label("The element to eliminate doesn't exists");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(70);
		add.setOnAction(e -> {
			try {
				league.eliminateClub(nameT.getText());
				error.setVisible(false);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (EliminateException e1) {
				error.setVisible(true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		operations.getChildren().addAll(name, nameT, add, error);
	}
	
	public void deletePlayer() {
		operations.getChildren().clear();
		Label name = new Label("Name");
		name.setLayoutX(0);
		name.setLayoutY(0);
		TextField nameT = new TextField();
		nameT.setLayoutX(50);
		nameT.setLayoutY(0);
		Label nameClub = new Label("Club");
		nameClub.setLayoutX(0);
		nameClub.setLayoutY(30);
		TextField nameClubT = new TextField();
		nameClubT.setLayoutX(50);
		nameClubT.setLayoutY(30);
		Button add = new Button("Delete player");
		add.setLayoutX(35);
		add.setLayoutY(70);
		Label error = new Label("The element to eliminate doesn't exists");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(100);
		Label errorClub = new Label("The club doesn't exists");
		errorClub.setVisible(false);
		errorClub.setLayoutX(35);
		errorClub.setLayoutY(130);
		add.setOnAction(e -> {
			try {
				String namePlayer = nameT.getText();
				String club = nameClubT.getText();
				league.eliminatePlayer(club, namePlayer);
				error.setVisible(false);
				errorClub.setVisible(false);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (EliminateException e1) {
				error.setVisible(true);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (NotFindedException e1) {
				errorClub.setVisible(true);
			}
		});
		operations.getChildren().addAll(name, nameT, nameClub, nameClubT, add, error, errorClub);
	}
	
	public void deleteReferee() {
		operations.getChildren().clear();
		Label name = new Label("Name");
		name.setLayoutX(0);
		name.setLayoutY(0);
		TextField nameT = new TextField();
		nameT.setLayoutX(50);
		nameT.setLayoutY(0);
		Button add = new Button("Delete referee");
		add.setLayoutX(35);
		add.setLayoutY(40);
		Label error = new Label("The element to eliminate doesn't exists");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(70);
		add.setOnAction(e -> {
			try {
				league.eliminateReferee(nameT.getText());
				error.setVisible(false);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (EliminateException e1) {
				error.setVisible(true);
			}
		});
		operations.getChildren().addAll(name, nameT, add, error);
	}
	
	public void deleteBall() {
		operations.getChildren().clear();
		Label id = new Label("Id");
		id.setLayoutX(0);
		id.setLayoutY(0);
		TextField idT = new TextField();
		idT.setLayoutX(50);
		idT.setLayoutY(0);
		Button add = new Button("Delete ball");
		add.setLayoutX(35);
		add.setLayoutY(40);
		Label error = new Label("The element to eliminate doesn't exists");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(70);
		add.setOnAction(e -> {
			try {
				league.eliminateBall(idT.getText());
				error.setVisible(false);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (EliminateException e1) {
				error.setVisible(true);
			}
		});
		operations.getChildren().addAll(id, idT, add, error);
	}
	
	public void deleteTechnical() {
		operations.getChildren().clear();
		Label name = new Label("Name");
		name.setLayoutX(0);
		name.setLayoutY(0);
		TextField nameT = new TextField();
		nameT.setLayoutX(50);
		nameT.setLayoutY(0);
		Label nameClub = new Label("Club");
		nameClub.setLayoutX(0);
		nameClub.setLayoutY(30);
		TextField nameClubT = new TextField();
		nameClubT.setLayoutX(50);
		nameClubT.setLayoutY(30);
		Button add = new Button("Delete technical");
		add.setLayoutX(35);
		add.setLayoutY(70);
		Label error = new Label("The element to eliminate doesn't exists");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(100);
		Label errorClub = new Label("The club doesn't exists");
		errorClub.setVisible(false);
		errorClub.setLayoutX(35);
		errorClub.setLayoutY(130);
		add.setOnAction(e -> {
			try {
				String nameTechnical = nameT.getText();
				String club = nameClubT.getText();
				league.eliminateTechnical(club, nameTechnical);
				error.setVisible(false);
				errorClub.setVisible(false);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (EliminateException e1) {
				error.setVisible(true);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (NotFindedException e1) {
				errorClub.setVisible(true);
			}
		});
		operations.getChildren().addAll(name, nameT, nameClub, nameClubT, add, error, errorClub);
	}
	
	public void deleteStadium() {
		operations.getChildren().clear();
		Label name = new Label("Name");
		name.setLayoutX(0);
		name.setLayoutY(0);
		TextField nameT = new TextField();
		nameT.setLayoutX(50);
		nameT.setLayoutY(0);
		Button add = new Button("Delete stadium");
		add.setLayoutX(35);
		add.setLayoutY(40);
		Label error = new Label("The element to eliminate doesn't exists");
		error.setVisible(false);
		error.setLayoutX(35);
		error.setLayoutY(70);
		add.setOnAction(e -> {
			try {
				league.eliminateStadium(nameT.getText());
				error.setVisible(false);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (EliminateException e1) {
				error.setVisible(true);
			}
		});
		operations.getChildren().addAll(name, nameT, add, error);
	}
	
	public void orderClubsByPoints() {
		operations.getChildren().clear();
		league.orderClubsByPoints();
		Label table = new Label(league.clubTable());
		table.setLayoutX(0);
		table.setLayoutY(0);
		table.setFont(new Font(15));;
		operations.getChildren().add(table);
	}
	
	public void orderPlayersByGoals() {
		operations.getChildren().clear();
		league.orderPlayersByGoals();
		Label table = new Label(league.playersByGoals());
		table.setLayoutX(0);
		table.setLayoutY(0);
		table.setFont(new Font(15));;
		operations.getChildren().add(table);
	}
	
	public void orderPlayersByAssists() {
		operations.getChildren().clear();
		league.orderPlayersByAssists();
		Label table = new Label(league.playersByAssists());
		table.setLayoutX(0);
		table.setLayoutY(0);
		table.setFont(new Font(15));
		operations.getChildren().add(table);
	}
	
	public void orderStadiumsByCapacity() {
		operations.getChildren().clear();
		league.orderStadiumsByCapacity();
		Label table = new Label(league.stadiumsByCapacity());
		table.setLayoutX(0);
		table.setLayoutY(0);
		table.setFont(new Font(15));
		operations.getChildren().add(table);
	}
	
	public void orderStadiumsByArea() {
		operations.getChildren().clear();
		league.orderStadiumsByArea();
		Label table = new Label(league.stadiumsByArea());
		table.setLayoutX(0);
		table.setLayoutY(0);
		table.setFont(new Font(15));
		operations.getChildren().add(table);
	}
	
	public void orderClubsByName() {
		operations.getChildren().clear();
		league.orderClubsBynames();
		Label table = new Label(league.clubs());
		table.setLayoutX(0);
		table.setLayoutY(0);
		table.setFont(new Font(15));
		operations.getChildren().add(table);
	}
}