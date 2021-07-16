package sportsLeague;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Random;


public class RandomMatch {

    private static int positionOfIndex1;
    private static int positionOfIndex2;
    static PremierLeagueManager manager = new PremierLeagueManager();


    public static void playMatch() {

        Stage primary = new Stage();
        PremierLeagueGui premierLeagueGui = new PremierLeagueGui();

        TableView<SportsClub> tablePlayedMatch = new TableView<>();
        final ObservableList<SportsClub> observer = FXCollections.observableArrayList(PremierLeagueManager.sportsClubList2);

        // Creating Column
        TableColumn date = new TableColumn("Date");
        date.setCellValueFactory(new PropertyValueFactory("date"));
        date.prefWidthProperty().bind(tablePlayedMatch.widthProperty().multiply(0.1));
        date.setResizable(false);

        TableColumn homeTeam = new TableColumn("Home Team");
        homeTeam.setCellValueFactory(new PropertyValueFactory("homeTeam"));
        homeTeam.prefWidthProperty().bind(tablePlayedMatch.widthProperty().multiply(0.2));
        homeTeam.setResizable(false);

        TableColumn awayTeam = new TableColumn("Away Team");
        awayTeam.setCellValueFactory(new PropertyValueFactory("awayTeam"));
        awayTeam.prefWidthProperty().bind(tablePlayedMatch.widthProperty().multiply(0.2));
        awayTeam.setResizable(false);

        TableColumn scoredGoals = new TableColumn("Goals For");
        scoredGoals.setCellValueFactory(new PropertyValueFactory("numOfScoredGoals"));
        scoredGoals.prefWidthProperty().bind(tablePlayedMatch.widthProperty().multiply(0.1));
        scoredGoals.setResizable(false);

        TableColumn receivedGoal = new TableColumn("Goals Against");
        receivedGoal.setCellValueFactory(new PropertyValueFactory("numOfReceivedGoals"));
        receivedGoal.prefWidthProperty().bind(tablePlayedMatch.widthProperty().multiply(0.2));
        receivedGoal.setResizable(false);


        // Back Button
        Button btnBack = new Button("Back to Standing Table");
        btnBack.setLayoutY(600);
        btnBack.setLayoutX(350);
        btnBack.setOnAction(event -> {
            primary.close();
            premierLeagueGui.start(primary);
        });

        // Random Button
        Button btnRandom = new Button("Random Play");
        btnRandom.setLayoutY(600);
        btnRandom.setLayoutX(150);

        btnRandom.setOnAction(event -> {
            randomMatch();
            primary.close();
            playMatch();
        });

        // Save button
        Button btnSave = new Button("Save and Exit ");
        btnSave.setLayoutY(600);
        btnSave.setLayoutX(800);

        btnSave.setOnAction(event -> {
            manager.saveData();
            primary.close();
        });

        // Date Button
        Button btnDate = new Button("Sort By Date");
        btnDate.setLayoutY(600);
        btnDate.setLayoutX(600);
        btnDate.setOnAction(event -> {
            primary.close();
            PremierLeagueManager.sportsClubList2.sort(new Comparator<SportsClub>() {

                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                @Override
                public int compare(SportsClub sportsClub1, SportsClub sportsClub2) {

                    LocalDate date01 = LocalDate.parse(((FootballClub) sportsClub1).getDate(), format);
                    LocalDate date02 = LocalDate.parse(((FootballClub) sportsClub2).getDate(), format);
                    try {
                        return date01.compareTo(date02);
                    } catch (Exception exception) {
                        throw new IllegalArgumentException(exception);
                    }
                }
            });
            playMatch();
        });


        tablePlayedMatch.setItems(observer);
        tablePlayedMatch.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tablePlayedMatch.getColumns().addAll(date, homeTeam, awayTeam, scoredGoals, receivedGoal);

        // Adding the Table into VBox
        VBox tblBox = new VBox();
        tblBox.setLayoutX(60);
        tblBox.setLayoutY(70);
        tblBox.setStyle("-fx-background-color: aquamarine");
        tblBox.setSpacing(5);
        tblBox.prefWidthProperty().bind(primary.widthProperty().multiply(0.9));
        tblBox.setPadding(new Insets(50, 50, 50, 50));
        tblBox.getChildren().addAll(tablePlayedMatch);

        AnchorPane pane = new AnchorPane();
        pane.getChildren().addAll(tblBox, btnBack, btnRandom, btnDate,btnSave);


        Scene tblScene = new Scene(pane, 1400, 700);
        primary.setTitle("Match Table");
        primary.setScene(tblScene);
        primary.setMaxWidth(1000);
        primary.setMaxHeight(700);
        primary.show();


    }


    private static void randomMatch() {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");

        while (true) {
            positionOfIndex1 = new Random().nextInt(PremierLeagueManager.sportsClubList.size());
            positionOfIndex2 = new Random().nextInt(PremierLeagueManager.sportsClubList.size());

            if (positionOfIndex1 != positionOfIndex2) {
                break;
            }
        }

        String homeTeam = PremierLeagueManager.sportsClubList.get(positionOfIndex1).getNameOfClub(); // Pick the one club randomly and assign to home team
        String awayTeam = PremierLeagueManager.sportsClubList.get(positionOfIndex2).getNameOfClub(); // Pick the one club randomly and assign to away team

        int homeTeamGoal = new Random().nextInt(15);  // Randomly pick one number and assign to Home team goal
        int awayTeamGoal = new Random().nextInt(15); // Randomly pick one number and assign to away team goal

        // Randomly generate the date
        Random randomDate = new Random();
        java.util.Calendar calenderClass = java.util.Calendar.getInstance();
        calenderClass.set(java.util.Calendar.MONTH, Math.abs(randomDate.nextInt()) % 12);
        calenderClass.set(java.util.Calendar.DAY_OF_MONTH, Math.abs(randomDate.nextInt()) % 30);
        calenderClass.setLenient(true);
        String date = dateFormat.format(calenderClass.getTime());


        manager.addPlayMatch(homeTeam, awayTeam, homeTeamGoal, awayTeamGoal, date);
        SportsClub sportsClub = new FootballClub("", "", homeTeam, awayTeam, homeTeamGoal, awayTeamGoal, date);
        PremierLeagueManager.sportsClubList2.add(sportsClub);

    }

}




