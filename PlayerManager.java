package nfl;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PlayerManager extends Application {
	Stage menu = new Stage(); // Create new Stage
	 public ArrayList < NFLPlayer > availablePlayers = new ArrayList < NFLPlayer > (); // Create ArrayList for available players
	 public ArrayList < OffensivePlayer > offensivePlayers = new ArrayList < OffensivePlayer > (); // Create ArrayList for Offensive players
	 public ArrayList < DefensivePlayer > defensivePlayers = new ArrayList < DefensivePlayer > (); // Create ArrayList for Deffensive Players
	 public ArrayList < Object > currentRoster = new ArrayList < Object > (); // Create ArrayList for my current roster
	 public TextField searchField = new TextField(); // Create textfield for search 

	 @Override // Start the application
	 public void start(Stage primaryStage) {
	  createPlayers();
	  menu.setTitle("NFL Draft Application");
	  showMenu(); // Show main menu
	 }

	 // Show Main Menu
	 public void showMenu() {
	  BorderPane pane = new BorderPane();
	  pane.setTop(getLogo());
	  VBox center = new VBox(getSearchBar(), getMenu());
	  center.setPadding(new Insets(15, 15, 15, 15));;
	  pane.setCenter(center);
	  menu.setScene(new Scene(pane, 700, 550));
	  menu.show();
	 }

	 // Show Offense Menu
	 private void showOffense() {

	  BorderPane pane = new BorderPane();
	  pane.setTop(getLogo());

	  VBox vBox = new VBox(15);
	  vBox.setPadding(new Insets(15, 5, 5, 5));

	  Button back = new Button();
	  back.setText("\u21E6"); // unicode for back Arrow
	  back.setId("back to menu");
	  back.setFont(Font.font("Verdana", 20));
	  back.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

	  Button AvailableOff = new Button("All Available"); // create My Roster button
	  AvailableOff.setId("allOff"); // set button id
	  AvailableOff.setMaxWidth(Double.MAX_VALUE); // set button width
	  AvailableOff.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

	  Button QB = new Button("QB"); // create offense button
	  QB.setId("QB"); // set button id
	  QB.setMaxWidth(Double.MAX_VALUE); // set button width
	  QB.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

	  Button RB = new Button("RB"); // create Defense button
	  RB.setId("RB"); // set button id
	  RB.setMaxWidth(Double.MAX_VALUE); // set button width
	  RB.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

	  Button WR = new Button("WR"); // create My Roster button
	  WR.setId("WR"); // set button id
	  WR.setMaxWidth(Double.MAX_VALUE); // set button width
	  WR.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

	  HBox offenseType = new HBox(); // create HBox
	  offenseType.setSpacing(125);
	  offenseType.setPadding(new Insets(0, 30, 0, 30));
	  offenseType.getChildren().addAll(AvailableOff, QB, RB, WR); // add all nodes to scene

	  vBox.getChildren().addAll(back, getSearchBar(), new Label("Select Position"), offenseType);
	  pane.setCenter(vBox);
	  menu.setScene(new Scene(pane, 700, 550));
	  menu.show();
	 }

	 // List offensive players
	 private void listOffensivePlayers(String selection) {
	  BorderPane pane = new BorderPane();

	  pane.setTop(getLogo());

	  VBox vBox = new VBox(15);
	  vBox.setPadding(new Insets(15, 5, 5, 5));

	  Button back = new Button();
	  back.setText("\u21E6"); // unicode for back Arrow
	  back.setId("back to offense");
	  back.setFont(Font.font("Verdana", 20));
	  back.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

	  GridPane list = new GridPane();
	  list.setAlignment(Pos.CENTER);
	  list.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
	  list.setHgap(5.5);
	  list.setVgap(5.5);
	  int i = 1;

	  list.add(new Label("Name"), 0, 0);
	  list.add(new Label("Age"), 1, 0);
	  list.add(new Label("Number"), 2, 0);
	  list.add(new Label("Team"), 3, 0);
	  list.add(new Label("Height"), 4, 0);
	  list.add(new Label("Weight"), 5, 0);
	  list.add(new Label("Passing/Rec"), 6, 0);
	  list.add(new Label("Rushing"), 7, 0);
	  list.add(new Label("TD"), 8, 0);
	  list.add(new Label("Draft"), 9, 0);

	  if (selection == "allOff") {
	   for (OffensivePlayer object: offensivePlayers) {
	    if (object.getStatus() == "available") {
	     Label name = new Label(object.getName());
	     Label age = new Label(Integer.toString(object.getAge()));
	     Label number = new Label(Integer.toString(object.getNumber()));
	     Label team = new Label(object.getTeam());
	     Label height = new Label(Float.toString(object.getHeight()));
	     Label weight = new Label(Float.toString(object.getWeight()));
	     Label passRec = new Label(Double.toString(object.getPassing()));
	     Label rushing = new Label(Double.toString(object.getRushing()));
	     Label td = new Label(Double.toString(object.getTd()));
	     Button draft = new Button();
	     draft.setText("Draft"); // unicode for back Arrow
	     draft.setId("draftOff " + object.getName());
	     draft.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
	     list.add(name, 0, i);
	     list.add(age, 1, i);
	     list.add(number, 2, i);
	     list.add(team, 3, i);
	     list.add(height, 4, i);
	     list.add(weight, 5, i);
	     list.add(passRec, 6, i);
	     list.add(rushing, 7, i);
	     list.add(td, 8, i);
	     list.add(draft, 9, i);
	     i++;
	    }
	   }
	  } else {
	   for (OffensivePlayer object: offensivePlayers) {
	    if (object.getStatus() == "available" && object.getPosition() == selection) {
	     Label name = new Label(object.getName());
	     Label age = new Label(Integer.toString(object.getAge()));
	     Label number = new Label(Integer.toString(object.getNumber()));
	     Label team = new Label(object.getTeam());
	     Label height = new Label(Float.toString(object.getHeight()));
	     Label weight = new Label(Float.toString(object.getWeight()));
	     Label passRec = new Label(Double.toString(object.getPassing()));
	     Label rushing = new Label(Double.toString(object.getRushing()));
	     Label td = new Label(Double.toString(object.getTd()));
	     Button draft = new Button();
	     draft.setText("Draft"); // unicode for back Arrow
	     draft.setId("draftOff " + object.getPosition() + " " + object.getName());
	     draft.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
	     list.add(name, 0, i);
	     list.add(age, 1, i);
	     list.add(number, 2, i);
	     list.add(team, 3, i);
	     list.add(height, 4, i);
	     list.add(weight, 5, i);
	     list.add(passRec, 6, i);
	     list.add(rushing, 7, i);
	     list.add(td, 8, i);
	     list.add(draft, 9, i);
	     i++;
	    }
	   }
	  }

	  // Columns will resize with window
	  ColumnConstraints columnConstraints = new ColumnConstraints();
	  columnConstraints.setFillWidth(true);
	  columnConstraints.setHgrow(Priority.ALWAYS);
	  list.getColumnConstraints().add(columnConstraints);

	  // Add All Node to vBox
	  vBox.getChildren().addAll(back, getSearchBar(), list);
	  pane.setCenter(vBox);

	  // Create ScrollPane
	  ScrollPane scroller = new ScrollPane(pane);
	  scroller.setFitToWidth(true);

	  menu.setScene(new Scene(scroller, 700, 550));
	  menu.show();
	 }

	 // Show Defense Menu
	 public void showDefense() {

	  BorderPane pane = new BorderPane();
	  pane.setTop(getLogo());

	  VBox vBox = new VBox(15);
	  vBox.setPadding(new Insets(15, 5, 5, 5));

	  Button back = new Button();
	  back.setText("\u21E6"); // unicode for back Arrow
	  back.setId("back to menu");
	  back.setFont(Font.font("Verdana", 20));
	  back.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

	  Button AvailableOff = new Button("All Available"); // create My Roster button
	  AvailableOff.setId("alldef"); // set button id
	  AvailableOff.setMaxWidth(Double.MAX_VALUE); // set button width
	  AvailableOff.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

	  Button DE = new Button("DE"); // create offense button
	  DE.setId("DE"); // set button id
	  DE.setMaxWidth(Double.MAX_VALUE); // set button width
	  DE.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

	  Button ILB = new Button("ILB"); // create Defense button
	  ILB.setId("ILB"); // set button id
	  ILB.setMaxWidth(Double.MAX_VALUE); // set button width
	  ILB.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

	  Button DB = new Button("DB"); // create My Roster button
	  DB.setId("DB"); // set button id
	  DB.setMaxWidth(Double.MAX_VALUE); // set button width
	  DB.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

	  Button FS = new Button("FS"); // create My Roster button
	  FS.setId("FS"); // set button id
	  FS.setMaxWidth(Double.MAX_VALUE); // set button width
	  FS.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

	  Button OLB = new Button("OLB"); // create My Roster button
	  OLB.setId("OLB"); // set button id
	  OLB.setMaxWidth(Double.MAX_VALUE); // set button width
	  OLB.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

	  Button MLB = new Button("MLB"); // create My Roster button
	  MLB.setId("MLB"); // set button id
	  MLB.setMaxWidth(Double.MAX_VALUE); // set button width
	  MLB.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

	  Button DT = new Button("DT"); // create My Roster button
	  DT.setId("DT"); // set button id
	  DT.setMaxWidth(Double.MAX_VALUE); // set button width
	  DT.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

	  FlowPane defenseType = new FlowPane(); // create HBox
	  defenseType.setHgap(40);
	  defenseType.setPadding(new Insets(0, 10, 0, 10));
	  defenseType.getChildren().addAll(AvailableOff, DE, ILB, DB, FS, OLB, MLB, DT); // add all nodes to scene

	  vBox.getChildren().addAll(back, getSearchBar(), new Label("Select Position"), defenseType);
	  pane.setCenter(vBox);
	  menu.setScene(new Scene(pane, 700, 550));
	  menu.show();
	 }

	 // List defensive players
	 private void listDeffensivePlayers(String selection) {
	  BorderPane pane = new BorderPane();
	  pane.setTop(getLogo());

	  VBox vBox = new VBox(15);
	  vBox.setPadding(new Insets(15, 5, 5, 5));

	  Button back = new Button();
	  back.setText("\u21E6"); // unicode for back Arrow
	  back.setId("back to defense");
	  back.setFont(Font.font("Verdana", 20));
	  back.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

	  // Create gridPane for list of players
	  GridPane list = new GridPane();
	  list.setAlignment(Pos.CENTER);
	  list.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
	  list.setHgap(5.5);
	  list.setVgap(5.5);
	  int i = 1;

	  // Set grid headers
	  list.add(new Label("Name"), 0, 0);
	  list.add(new Label("Age"), 1, 0);
	  list.add(new Label("Number"), 2, 0);
	  list.add(new Label("Team"), 3, 0);
	  list.add(new Label("Height"), 4, 0);
	  list.add(new Label("Weight"), 5, 0);
	  list.add(new Label("Tackles"), 6, 0);
	  list.add(new Label("Sacks"), 7, 0);
	  list.add(new Label("Int"), 8, 0);
	  list.add(new Label("Draft"), 9, 0);

	  if (selection == "alldef") {
	   for (DefensivePlayer object: defensivePlayers) { // List all defensive players
	    if (object.getStatus() == "available") {
	     Label name = new Label(object.getName());
	     Label age = new Label(Integer.toString(object.getAge()));
	     Label number = new Label(Integer.toString(object.getNumber()));
	     Label team = new Label(object.getTeam());
	     Label height = new Label(Float.toString(object.getHeight()));
	     Label weight = new Label(Float.toString(object.getWeight()));
	     Label Tackles = new Label(Double.toString(object.getTackles()));
	     Label Sacks = new Label(Double.toString(object.getSacks()));
	     Label Int = new Label(Double.toString(object.getInter()));
	     Button draft = new Button();
	     draft.setText("Draft"); // unicode for back Arrow
	     draft.setId("draftdef " + object.getName());
	     draft.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
	     list.add(name, 0, i);
	     list.add(age, 1, i);
	     list.add(number, 2, i);
	     list.add(team, 3, i);
	     list.add(height, 4, i);
	     list.add(weight, 5, i);
	     list.add(Tackles, 6, i);
	     list.add(Sacks, 7, i);
	     list.add(Int, 8, i);
	     list.add(draft, 9, i);
	     i++;
	    }
	   }
	  } else {
	   for (DefensivePlayer object: defensivePlayers) {
	    if (object.getStatus() == "available" && object.getPosition() == selection) { // all players by position
	     Label name = new Label(object.getName());
	     Label age = new Label(Integer.toString(object.getAge()));
	     Label number = new Label(Integer.toString(object.getNumber()));
	     Label team = new Label(object.getTeam());
	     Label height = new Label(Float.toString(object.getHeight()));
	     Label weight = new Label(Float.toString(object.getWeight()));
	     Label Tackles = new Label(Double.toString(object.getTackles()));
	     Label Sacks = new Label(Double.toString(object.getSacks()));
	     Label Int = new Label(Double.toString(object.getInter()));
	     Button draft = new Button();
	     draft.setText("Draft"); // unicode for back Arrow
	     draft.setId("draftdef " + object.getPosition() + " " + object.getName());
	     draft.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
	     list.add(name, 0, i);
	     list.add(age, 1, i);
	     list.add(number, 2, i);
	     list.add(team, 3, i);
	     list.add(height, 4, i);
	     list.add(weight, 5, i);
	     list.add(Tackles, 6, i);
	     list.add(Sacks, 7, i);
	     list.add(Int, 8, i);
	     list.add(draft, 9, i);
	     i++;
	    }
	   }
	  }

	  // Columns will resize with window
	  ColumnConstraints columnConstraints = new ColumnConstraints();
	  columnConstraints.setFillWidth(true);
	  columnConstraints.setHgrow(Priority.ALWAYS);
	  list.getColumnConstraints().add(columnConstraints);

	  // Add All Node to vBox
	  vBox.getChildren().addAll(back, getSearchBar(), list);
	  pane.setCenter(vBox);

	  // Create ScrollPane
	  ScrollPane scroller = new ScrollPane(pane);
	  scroller.setFitToWidth(true);

	  menu.setScene(new Scene(scroller, 700, 550));
	  menu.show();
	 }

	 //  Show My Roster
	 private void showRoster() {
	  BorderPane pane = new BorderPane();
	  pane.setTop(getLogo());

	  VBox vBox = new VBox(15);
	  vBox.setPadding(new Insets(15, 5, 5, 5));

	  Button back = new Button();
	  back.setText("\u21E6"); // unicode for back Arrow
	  back.setId("back to menu");
	  back.setFont(Font.font("Verdana", 20));
	  back.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

	  // Create gridPane for list of players
	  GridPane list = new GridPane();
	  list.setAlignment(Pos.CENTER);
	  list.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
	  list.setHgap(5.5);
	  list.setVgap(5.5);
	  int i = 0;
	  int offResults = 0;
	  int defResults = 0;

	  for (Object object: currentRoster) { // List all defensive players
	   if (((NFLPlayer) object).getCategory().equals("off")) {
	    offResults++;
	   }
	  }

	  if (offResults > 0) {
	   list.add(new Label("Offense"), 0, 0);
	   list.add(new Label("Age"), 1, 0);
	   list.add(new Label("Number"), 2, 0);
	   list.add(new Label("Team"), 3, 0);
	   list.add(new Label("Height"), 4, 0);
	   list.add(new Label("Weight"), 5, 0);
	   list.add(new Label("Passing/Rec"), 6, 0);
	   list.add(new Label("Rushing"), 7, 0);
	   list.add(new Label("TD"), 8, 0);
	   list.add(new Label("Cut"), 9, 0);
	   i++;
	  }

	  for (Object object: currentRoster) { // List all defensive players
	   if (((NFLPlayer) object).getCategory() == "off") {
	    Label name = new Label(((NFLPlayer) object).getName());
	    Label age = new Label(Integer.toString(((NFLPlayer) object).getAge()));
	    Label number = new Label(Integer.toString(((NFLPlayer) object).getNumber()));
	    Label team = new Label(((NFLPlayer) object).getTeam());
	    Label height = new Label(Float.toString(((NFLPlayer) object).getHeight()));
	    Label weight = new Label(Float.toString(((NFLPlayer) object).getWeight()));
	    Label passRec = new Label(Double.toString(((OffensivePlayer) object).getPassing()));
	    Label rushing = new Label(Double.toString(((OffensivePlayer) object).getRushing()));
	    Label td = new Label(Double.toString(((OffensivePlayer) object).getTd()));
	    Button draft = new Button();
	    draft.setText("Cut"); // unicode for back Arrow
	    draft.setId("cut_ " + ((NFLPlayer) object).getName());
	    draft.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
	    list.add(name, 0, i);
	    list.add(age, 1, i);
	    list.add(number, 2, i);
	    list.add(team, 3, i);
	    list.add(height, 4, i);
	    list.add(weight, 5, i);
	    list.add(passRec, 6, i);
	    list.add(rushing, 7, i);
	    list.add(td, 8, i);
	    list.add(draft, 9, i);
	    i++;
	   }
	  }

	  // Set grid headers
	  for (Object object: currentRoster) { // List all defensive players
	   if (((NFLPlayer) object).getCategory().equals("def")) {
	    defResults++;
	   }
	  }

	  if (defResults > 0) {
	   list.add(new Label("Defense"), 0, i);
	   list.add(new Label("Age"), 1, i);
	   list.add(new Label("Number"), 2, i);
	   list.add(new Label("Team"), 3, i);
	   list.add(new Label("Height"), 4, i);
	   list.add(new Label("Weight"), 5, i);
	   list.add(new Label("Tackles"), 6, i);
	   list.add(new Label("Sacks"), 7, i);
	   list.add(new Label("Int"), 8, i);
	   list.add(new Label("Cut"), 9, i);
	   i++;
	  }

	  for (Object object: currentRoster) { // List all defensive players
	   if (((NFLPlayer) object).getCategory() == "def") {
	    Label name = new Label(((NFLPlayer) object).getName());
	    Label age = new Label(Integer.toString(((NFLPlayer) object).getAge()));
	    Label number = new Label(Integer.toString(((NFLPlayer) object).getNumber()));
	    Label team = new Label(((NFLPlayer) object).getTeam());
	    Label height = new Label(Float.toString(((NFLPlayer) object).getHeight()));
	    Label weight = new Label(Float.toString(((NFLPlayer) object).getWeight()));
	    Label Tackles = new Label(Double.toString(((DefensivePlayer) object).getTackles()));
	    Label Sacks = new Label(Double.toString(((DefensivePlayer) object).getSacks()));
	    Label Int = new Label(Double.toString(((DefensivePlayer) object).getInter()));
	    Button draft = new Button();
	    draft.setText("Cut"); // unicode for back Arrow
	    draft.setId("cut_ " + ((NFLPlayer) object).getName());
	    draft.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
	    list.add(name, 0, i);
	    list.add(age, 1, i);
	    list.add(number, 2, i);
	    list.add(team, 3, i);
	    list.add(height, 4, i);
	    list.add(weight, 5, i);
	    list.add(Tackles, 6, i);
	    list.add(Sacks, 7, i);
	    list.add(Int, 8, i);
	    list.add(draft, 9, i);
	    i++;
	   }
	  }


	  // Columns will resize with window
	  ColumnConstraints columnConstraints = new ColumnConstraints();
	  columnConstraints.setFillWidth(true);
	  columnConstraints.setHgrow(Priority.ALWAYS);
	  list.getColumnConstraints().add(columnConstraints);

	  // Add All Node to vBox
	  vBox.getChildren().addAll(back, getSearchBar(), list);
	  pane.setCenter(vBox);

	  // Create ScrollPane
	  ScrollPane scroller = new ScrollPane(pane);
	  scroller.setFitToWidth(true);

	  menu.setScene(new Scene(scroller, 700, 550));
	  menu.show();
	 }

	 //  Show Search Results
	 private void showSearchResults(String searchName) {
	  BorderPane pane = new BorderPane();
	  pane.setTop(getLogo());

	  VBox vBox = new VBox(15);
	  vBox.setPadding(new Insets(15, 5, 5, 5));

	  Button back = new Button();
	  back.setText("\u21E6"); // unicode for back Arrow
	  back.setId("back to menu");
	  back.setFont(Font.font("Verdana", 20));
	  back.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

	  // Create gridPane for list of players
	  GridPane list = new GridPane();
	  list.setAlignment(Pos.CENTER);
	  list.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
	  list.setHgap(5.5);
	  list.setVgap(5.5);
	  int i = 0;
	  int offResults = 0;
	  int defResults = 0;

	  for (Object object: offensivePlayers) { // List all defensive players
	   if (((NFLPlayer) object).getName().toLowerCase().contains(searchName.toLowerCase())) {
	    offResults++;
	   }
	  }

	  if (offResults > 0) {
	   list.add(new Label("Offense"), 0, 0);
	   list.add(new Label("Age"), 1, 0);
	   list.add(new Label("Number"), 2, 0);
	   list.add(new Label("Team"), 3, 0);
	   list.add(new Label("Height"), 4, 0);
	   list.add(new Label("Weight"), 5, 0);
	   list.add(new Label("Passing/Rec"), 6, 0);
	   list.add(new Label("Rushing"), 7, 0);
	   list.add(new Label("TD"), 8, 0);
	   list.add(new Label("Cut"), 9, 0);
	   i++;
	  }

	  for (Object object: offensivePlayers) { // List all defensive players
	   if (((NFLPlayer) object).getName().toLowerCase().contains(searchName.toLowerCase())) {
	    Label name = new Label(((NFLPlayer) object).getName());
	    Label age = new Label(Integer.toString(((NFLPlayer) object).getAge()));
	    Label number = new Label(Integer.toString(((NFLPlayer) object).getNumber()));
	    Label team = new Label(((NFLPlayer) object).getTeam());
	    Label height = new Label(Float.toString(((NFLPlayer) object).getHeight()));
	    Label weight = new Label(Float.toString(((NFLPlayer) object).getWeight()));
	    Label passRec = new Label(Double.toString(((OffensivePlayer) object).getPassing()));
	    Label rushing = new Label(Double.toString(((OffensivePlayer) object).getRushing()));
	    Label td = new Label(Double.toString(((OffensivePlayer) object).getTd()));
	    Button draft = new Button();
	    draft.setText("Draft"); // unicode for back Arrow
	    draft.setId("draftOff " + ((NFLPlayer) object).getName());
	    draft.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
	    list.add(name, 0, i);
	    list.add(age, 1, i);
	    list.add(number, 2, i);
	    list.add(team, 3, i);
	    list.add(height, 4, i);
	    list.add(weight, 5, i);
	    list.add(passRec, 6, i);
	    list.add(rushing, 7, i);
	    list.add(td, 8, i);
	    list.add(draft, 9, i);
	    i++;
	   }
	  }

	  for (Object object: defensivePlayers) { // List all defensive players
	   if (((NFLPlayer) object).getName().toLowerCase().contains(searchName.toLowerCase())) {
	    defResults++;
	   }
	  }

	  if (defResults > 0) {
	   // Set grid headers
	   list.add(new Label("Defense"), 0, i);
	   list.add(new Label("Age"), 1, i);
	   list.add(new Label("Number"), 2, i);
	   list.add(new Label("Team"), 3, i);
	   list.add(new Label("Height"), 4, i);
	   list.add(new Label("Weight"), 5, i);
	   list.add(new Label("Tackles"), 6, i);
	   list.add(new Label("Sacks"), 7, i);
	   list.add(new Label("Int"), 8, i);
	   list.add(new Label("Cut"), 9, i);
	   i++;
	  }

	  for (Object object: defensivePlayers) { // List all defensive players
	   if (((NFLPlayer) object).getName().toLowerCase().contains(searchName.toLowerCase())) {
	    Label name = new Label(((NFLPlayer) object).getName());
	    Label age = new Label(Integer.toString(((NFLPlayer) object).getAge()));
	    Label number = new Label(Integer.toString(((NFLPlayer) object).getNumber()));
	    Label team = new Label(((NFLPlayer) object).getTeam());
	    Label height = new Label(Float.toString(((NFLPlayer) object).getHeight()));
	    Label weight = new Label(Float.toString(((NFLPlayer) object).getWeight()));
	    Label Tackles = new Label(Double.toString(((DefensivePlayer) object).getTackles()));
	    Label Sacks = new Label(Double.toString(((DefensivePlayer) object).getSacks()));
	    Label Int = new Label(Double.toString(((DefensivePlayer) object).getInter()));
	    Button draft = new Button();
	    draft.setText("Draft"); // unicode for back Arrow
	    draft.setId("draftdef " + ((NFLPlayer) object).getName());
	    draft.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
	    list.add(name, 0, i);
	    list.add(age, 1, i);
	    list.add(number, 2, i);
	    list.add(team, 3, i);
	    list.add(height, 4, i);
	    list.add(weight, 5, i);
	    list.add(Tackles, 6, i);
	    list.add(Sacks, 7, i);
	    list.add(Int, 8, i);
	    list.add(draft, 9, i);
	    i++;
	   }
	  }


	  // Columns will resize with window
	  ColumnConstraints columnConstraints = new ColumnConstraints();
	  columnConstraints.setFillWidth(true);
	  columnConstraints.setHgrow(Priority.ALWAYS);
	  list.getColumnConstraints().add(columnConstraints);

	  // Add All Node to vBox
	  vBox.getChildren().addAll(back, getSearchBar(), list);
	  pane.setCenter(vBox);

	  // Create ScrollPane
	  ScrollPane scroller = new ScrollPane(pane);
	  scroller.setFitToWidth(true);

	  menu.setScene(new Scene(scroller, 700, 550));
	  menu.show();
	 }

	 // Return Main Menu	 
	 private Node getMenu() {
	  Button offense = new Button("View Offense"); // create offense button
	  offense.setId("View Offense"); // set button id
	  offense.setMaxWidth(Double.MAX_VALUE); // set button width
	  offense.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

	  Button defense = new Button("View Defense"); // create Defense button
	  defense.setId("View Defense"); // set button id
	  defense.setMaxWidth(Double.MAX_VALUE); // set button width
	  defense.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

	  Button myRoster = new Button("My Roster"); // create My Roster button
	  myRoster.setId("My Roster"); // set button id
	  myRoster.setMaxWidth(Double.MAX_VALUE); // set button width
	  myRoster.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

	  VBox vbButtons = new VBox();
	  vbButtons.setSpacing(30);
	  vbButtons.setPadding(new Insets(30, 200, 30, 200));

	  vbButtons.getChildren().addAll(offense, defense);

	  // if currentRoster has any players add button to scene
	  if (!currentRoster.isEmpty()) {
	   vbButtons.getChildren().addAll(myRoster);
	  }
	  return vbButtons;
	 }

	 // Return Logo in StackPane
	 private StackPane getLogo() {
	  StackPane stackPane = new StackPane(); // 15 pixels space between child nodes
	  stackPane.setPrefHeight(100);
	  stackPane.setPadding(new Insets(15, 15, 15, 15)); // 15 pixel padding all around
	  stackPane.setStyle("-fx-background-color: gray"); // background color is gray
	  ImageView imageView = new ImageView(new Image("images/NFL_Draft.png")); // create & add image to imageView
	  imageView.setFitHeight(200); // set imageView height
	  imageView.setPreserveRatio(true); // perserve image aspect ratio
	  stackPane.getChildren().add(imageView); // add imageView to hbox
	  return stackPane;
	 }

	 // Create and return Searchbar
	 private HBox getSearchBar() {
	  Label searchlabel = new Label("Search by Player"); // create label
	  searchField.setId("searchFor");
	  searchField.setMinWidth(50);
	  searchField.setPrefWidth(460);
	  searchField.setMaxWidth(460);
	  Button searchBtn = new Button("Search"); // create search button
	  searchBtn.setId("search"); // set button id
	  searchBtn.setMaxWidth(Double.MAX_VALUE); // set button width
	  searchBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
	  HBox searchBar = new HBox(); // create HBox
	  searchBar.getChildren().addAll(searchlabel, searchField, searchBtn); // add all nodes to scene
	  searchBar.setSpacing(10);
	  return searchBar;
	 }

	 // Create players for draft 
	 public void createPlayers() {

	  // Create Offensive Players
	  OffensivePlayer offensivePlayer1 = new OffensivePlayer("Adrian Peterson", 31, 28, "Minnesota Vikings", 6.1f, 220f, "off", 222, 30, 7.4, 1485, 4.5, 327, 11, "RB");
	  OffensivePlayer offensivePlayer2 = new OffensivePlayer("Tom Brady", 39, 12, "NE Patriots", 6.4f, 225f, "off", 4770, 0, 0, 0, 0, 0, 36, "QB");
	  OffensivePlayer offensivePlayer3 = new OffensivePlayer("Chris Ivory", 28, 33, "Jacksonville Jaguars", 6.0f, 222f, "off", 0, 30, 0, 1070, 4.3, 247, 7, "RB");
	  OffensivePlayer offensivePlayer4 = new OffensivePlayer("Odell Beckham", 23, 13, "New York Giants", 5.11f, 198f, "off", 1450, 96, 15.1, 0, 0, 0, 13, "WR");
	  OffensivePlayer offensivePlayer5 = new OffensivePlayer("DeAndre Hopkins", 24, 10, "Houston Texans", 6.1f, 218f, "off", 1521, 111, 13.7, 0, 0, 0, 11, "WR");
	  OffensivePlayer offensivePlayer6 = new OffensivePlayer("Eli Manning", 35, 10, "New York Giants", 6.4f, 218f, "off", 4432, 618, 62.6, 0, 0, 0, 35, "QB");
	  OffensivePlayer offensivePlayer7 = new OffensivePlayer("Thomas Rawls", 23, 34, "Seattle Seahawks", 5.9f, 215f, "off", 76, 9, 8.4, 830, 5.6, 147, 4, "RB");
	  OffensivePlayer offensivePlayer8 = new OffensivePlayer("LeSean McCoy", 28, 28, "Buffalo Bills", 5.11f, 208f, "off", 292, 32, 9.1, 895, 4.4, 203, 3, "RB");
	  OffensivePlayer offensivePlayer9 = new OffensivePlayer("Matt Ryan", 31, 2, "Atlanta Falcons", 6.4f, 217f, "off", 4591, 407, 7.5, 63, 1.8, 36, 21, "QB");
	  OffensivePlayer offensivePlayer10 = new OffensivePlayer("Larry Fitzgerald", 32, 11, "Arizona Cardinals", 6.3f, 218f, "off", 1215, 109, 11.1, 0, 0, 0, 9, "WR");

	  // Create Defensive Players
	  DefensivePlayer defensivePlayer1 = new DefensivePlayer("J.J. Watt", 27, 99, "Houston Texans", 6.5f, 289f, "def", 57, 19, 76, 17.5, 0, "DE");
	  DefensivePlayer defensivePlayer2 = new DefensivePlayer("NaVorro Bowman", 28, 53, "SF 49ers", 6.0f, 242f, "def", 116, 38, 154, 2.5, 0, "ILB");
	  DefensivePlayer defensivePlayer3 = new DefensivePlayer("Reggie Nelson", 32, 27, "Oakland Raiders", 5.11f, 210f, "def", 52, 25, 77, 0, 8, "DB");
	  DefensivePlayer defensivePlayer4 = new DefensivePlayer("Corey Graham", 31, 20, "Buffalo Bills", 6.0f, 196f, "def", 96, 31, 127, 1, 2, "FS");
	  DefensivePlayer defensivePlayer5 = new DefensivePlayer("Deone Bucannon", 23, 20, "Arizona Cardinals", 6.1f, 211f, "def", 93, 19, 112, 3, 1, "OLB");
	  DefensivePlayer defensivePlayer6 = new DefensivePlayer("Paul Posluszny", 31, 51, "Jacksonville Jaguars", 6.2f, 240f, "def", 103, 30, 133, 1, 3, "MLB");
	  DefensivePlayer defensivePlayer7 = new DefensivePlayer("Geno Atkins", 28, 97, "Cincinnati Bengals", 6.1f, 300f, "def", 31, 11, 42, 11, 0, "DT");
	  DefensivePlayer defensivePlayer8 = new DefensivePlayer("Dwight Freeney", 36, 36, "Arizona Cardinals", 6.1f, 268f, "def", 9, 8, 1, 8, 0, "LB");
	  DefensivePlayer defensivePlayer9 = new DefensivePlayer("Marcus Peters", 23, 22, "Kansas City Chiefs", 6.0f, 197f, "def", 53, 7, 60, 0, 8, "CB");
	  DefensivePlayer defensivePlayer10 = new DefensivePlayer("Thomas Davis", 33, 58, "Carolina Panthers", 6.1f, 235f, "def", 75, 30, 105, 5.5, 4, "OLB");

	  // Add Offensive Players to draft
	  offensivePlayers.add(offensivePlayer1);
	  offensivePlayers.add(offensivePlayer2);
	  offensivePlayers.add(offensivePlayer3);
	  offensivePlayers.add(offensivePlayer4);
	  offensivePlayers.add(offensivePlayer5);
	  offensivePlayers.add(offensivePlayer6);
	  offensivePlayers.add(offensivePlayer7);
	  offensivePlayers.add(offensivePlayer8);
	  offensivePlayers.add(offensivePlayer9);
	  offensivePlayers.add(offensivePlayer10);

	  // Add Defensive Players to draft
	  defensivePlayers.add(defensivePlayer1);
	  defensivePlayers.add(defensivePlayer2);
	  defensivePlayers.add(defensivePlayer3);
	  defensivePlayers.add(defensivePlayer4);
	  defensivePlayers.add(defensivePlayer5);
	  defensivePlayers.add(defensivePlayer6);
	  defensivePlayers.add(defensivePlayer7);
	  defensivePlayers.add(defensivePlayer8);
	  defensivePlayers.add(defensivePlayer9);
	  defensivePlayers.add(defensivePlayer10);
	 }

	 /**
	  * The main method is only needed for the IDE with limited
	  * JavaFX support. Not needed for running from the command line.
	  */
	 public static void main(String[] args) {
	  launch(args);
	 }

	 // This handles all events for when buttons are clicked 
	 private class MyEventHandler implements EventHandler < Event > {
	  @Override
	  public void handle(Event evt) {
	   // Switch that runs methods based upon button id 
	   switch (((Control) evt.getSource()).getId()) {
	    case "View Offense":
	     showOffense();
	     break;
	    case "View Defense":
	     showDefense();
	     break;
	    case "My Roster":
	     showRoster();
	     break;
	    case "back to menu":
	     showMenu();
	     break;
	    case "back to offense":
	     showOffense();
	     break;
	    case "back to defense":
	     showDefense();
	     break;
	    case "search":
	     showSearchResults(searchField.getText());
	     break;
	    case "allOff":
	     listOffensivePlayers(((Control) evt.getSource()).getId());
	     break;
	    case "QB":
	     listOffensivePlayers(((Control) evt.getSource()).getId());
	     break;
	    case "RB":
	     listOffensivePlayers(((Control) evt.getSource()).getId());
	     break;
	    case "WR":
	     listOffensivePlayers(((Control) evt.getSource()).getId());
	     break;
	    case "alldef":
	     listDeffensivePlayers(((Control) evt.getSource()).getId());
	     break;
	    case "DE":
	     listDeffensivePlayers(((Control) evt.getSource()).getId());
	     break;
	    case "ILB":
	     listDeffensivePlayers(((Control) evt.getSource()).getId());
	     break;
	    case "DB":
	     listDeffensivePlayers(((Control) evt.getSource()).getId());
	     break;
	    case "FS":
	     listDeffensivePlayers(((Control) evt.getSource()).getId());
	     break;
	    case "OLB":
	     listDeffensivePlayers(((Control) evt.getSource()).getId());
	     break;
	    case "MLB":
	     listDeffensivePlayers(((Control) evt.getSource()).getId());
	     break;
	    case "DT":
	     listDeffensivePlayers(((Control) evt.getSource()).getId());
	     break;
	    case "LB":
	     listDeffensivePlayers(((Control) evt.getSource()).getId());
	     break;
	    case "CB":
	     listDeffensivePlayers(((Control) evt.getSource()).getId());
	     break;
	   }

	   // drafting offense
	   if (((Control) evt.getSource()).getId().contains("draftOff")) {
	    String name;
	    String players;

	    if (((Control) evt.getSource()).getId().contains("QB")) { // if QB is clicked
	     name = ((Control) evt.getSource()).getId().substring(12);
	     players = "QB";
	    } else if (((Control) evt.getSource()).getId().contains("RB")) { // if RB is clicked
	     name = ((Control) evt.getSource()).getId().substring(12);
	     players = "RB";
	    } else if (((Control) evt.getSource()).getId().contains("WR")) { // if WR is clicked
	     name = ((Control) evt.getSource()).getId().substring(12);
	     players = "WR";
	    } else {
	     name = ((Control) evt.getSource()).getId().substring(9); // // All offense QB is clicked
	     players = "allOff";
	    }

	    for (Iterator < OffensivePlayer > it = offensivePlayers.iterator(); it.hasNext();) { // iterate through all objects in arraylist
	     OffensivePlayer offensivePlayers = it.next();

	     if (offensivePlayers.getName().equals(name)) { // if object name equals name of person drafted

	      Alert a = new Alert(AlertType.INFORMATION); // create alert with celebration message
	      a.setTitle("Celebrator");
	      a.setHeaderText("Wow look at that,");
	      a.setResizable(true);
	      String version = System.getProperty("java.version");
	      String content = String.format(offensivePlayers.getName() + offensivePlayers.celebrate(), version);
	      a.setContentText(content);
	      a.showAndWait();

	      currentRoster.add(offensivePlayers); // add object to current roster		 
	      it.remove(); // remove object for offensive players 

	      if (searchField.getText().isEmpty()) {
	       listOffensivePlayers(players); // list players passing selection
	      } else {
	       showSearchResults(searchField.getText());
	       searchField = new TextField();
	      }

	     }
	    }

	   }

	   // drafting defense
	   if (((Control) evt.getSource()).getId().contains("draftdef")) {
	    String name;
	    String players;

	    if (((Control) evt.getSource()).getId().contains("DE")) { // if QB is clicked
	     name = ((Control) evt.getSource()).getId().substring(12);
	     players = "DE";
	    } else if (((Control) evt.getSource()).getId().contains("ILB")) { // if RB is clicked
	     name = ((Control) evt.getSource()).getId().substring(13);
	     players = "ILB";
	    } else if (((Control) evt.getSource()).getId().contains("DB")) { // if WR is clicked
	     name = ((Control) evt.getSource()).getId().substring(12);
	     players = "DB";
	    } else if (((Control) evt.getSource()).getId().contains("FS")) { // if WR is clicked
	     name = ((Control) evt.getSource()).getId().substring(12);
	     players = "FS";
	    } else if (((Control) evt.getSource()).getId().contains("OLB")) { // if WR is clicked
	     name = ((Control) evt.getSource()).getId().substring(13);
	     players = "OLB";
	    } else if (((Control) evt.getSource()).getId().contains("MLB")) { // if WR is clicked
	     name = ((Control) evt.getSource()).getId().substring(13);
	     players = "MLB";
	    } else if (((Control) evt.getSource()).getId().contains("DT")) { // if WR is clicked
	     name = ((Control) evt.getSource()).getId().substring(12);
	     players = "DT";
	    } else if (((Control) evt.getSource()).getId().contains("LB")) { // if WR is clicked
	     name = ((Control) evt.getSource()).getId().substring(12);
	     players = "LB";
	    } else if (((Control) evt.getSource()).getId().contains("CB")) { // if WR is clicked
	     name = ((Control) evt.getSource()).getId().substring(12);
	     players = "CB";
	    } else {
	     name = ((Control) evt.getSource()).getId().substring(9); // // All offense QB is clicked
	     players = "alldef";
	    }

	    for (Iterator < DefensivePlayer > it = defensivePlayers.iterator(); it.hasNext();) { // iterate through all objects in arraylist
	     DefensivePlayer defensivePlayers = it.next();

	     if (defensivePlayers.getName().equals(name)) { // if object name equals name of person drafted

	      Alert a = new Alert(AlertType.INFORMATION); // create alert with celebration message
	      a.setTitle("Celebrator");
	      a.setHeaderText("Wow look at that,");
	      a.setResizable(true);
	      String version = System.getProperty("java.version");
	      String content = String.format(defensivePlayers.getName() + defensivePlayers.celebrate(), version);
	      a.setContentText(content);
	      a.showAndWait();

	      currentRoster.add(defensivePlayers); // add object to current roster		 
	      it.remove(); // remove object for offensive players 
	      if (searchField.getText().isEmpty()) {
	       listDeffensivePlayers(players); // list players passing selection
	      } else {
	       showSearchResults(searchField.getText());
	       searchField = new TextField();
	      }
	     }
	    }

	   }

	   // drafting defense
	   if (((Control) evt.getSource()).getId().contains("cut_")) {
	    String name;
	    name = ((Control) evt.getSource()).getId().substring(5);

	    for (Iterator < Object > it = currentRoster.iterator(); it.hasNext();) { // iterate through all objects in arraylist
	     Object currentRoster = it.next();

	     if (((NFLPlayer) currentRoster).getName().equals(name)) {

	      Alert a = new Alert(AlertType.INFORMATION); // create alert with celebration message
	      a.setTitle("Celebrator");
	      a.setHeaderText("Oh No!");
	      a.setResizable(true);
	      String version = System.getProperty("java.version");
	      String content = String.format("You have cut " + name, version);
	      a.setContentText(content);
	      a.showAndWait();

	      if (((NFLPlayer) currentRoster).getCategory().equals("off")) {
	       offensivePlayers.add((OffensivePlayer) currentRoster); // add object to current roster		 
	       it.remove(); // remove object from roster
	       showRoster(); // list players on my roster
	      }

	      if (((NFLPlayer) currentRoster).getCategory().equals("def")) {
	       defensivePlayers.add((DefensivePlayer) currentRoster); // add object to current roster		 
	       it.remove(); // remove object from roster
	       showRoster(); // list players on my roster
	      }


	     }
	    }

	   }

	  }
	}
}
