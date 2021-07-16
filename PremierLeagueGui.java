package sportsLeague;

import javafx.application.Application;
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



public class PremierLeagueGui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Stage primary = new Stage();
        TableView<SportsClub> tableMember = new TableView<>();
        final ObservableList<SportsClub> observer = FXCollections.observableArrayList(PremierLeagueManager.sportsClubList);

        TableColumn date = new TableColumn("Date");
        date.setCellValueFactory(new PropertyValueFactory("date"));
        date.prefWidthProperty().bind(tableMember.widthProperty().multiply(0.12));
        date.setResizable(false);

        TableColumn position = new TableColumn("Position");
        position.setCellValueFactory(new PropertyValueFactory("position"));
        position.prefWidthProperty().bind(tableMember.widthProperty().multiply(0.1));
        position.setResizable(false);

        TableColumn clubName = new TableColumn("Club Name");
        clubName.setCellValueFactory(new PropertyValueFactory("nameOfClub"));
        clubName.prefWidthProperty().bind(tableMember.widthProperty().multiply(0.2));
        clubName.setResizable(false);

        TableColumn played = new TableColumn("Played Matches");
        played.setCellValueFactory(new PropertyValueFactory("numOfPlayedMatches"));
        played.setResizable(false);


        TableColumn won = new TableColumn("Won");
        won.setCellValueFactory(new PropertyValueFactory("numOfWin"));
        won.setResizable(false);


        TableColumn lost = new TableColumn("Lost");
        lost.setCellValueFactory(new PropertyValueFactory("numOfDefeat"));
        lost.setResizable(false);


        TableColumn goalsFor = new TableColumn("Goals For");
        goalsFor.setCellValueFactory(new PropertyValueFactory("numOfScoredGoals"));
        goalsFor.prefWidthProperty().bind(tableMember.widthProperty().multiply(0.1));
        goalsFor.setResizable(false);


        TableColumn goalsAgainst = new TableColumn("Goals Against");
        goalsAgainst.setCellValueFactory(new PropertyValueFactory("numOfReceivedGoals"));
        goalsAgainst.prefWidthProperty().bind(tableMember.widthProperty().multiply(0.15));
        goalsAgainst.setResizable(false);
        goalsAgainst.setSortType(TableColumn.SortType.DESCENDING);

        TableColumn goalsDifference = new TableColumn("Goals Difference");
        goalsDifference.setCellValueFactory(new PropertyValueFactory("goalDifference"));
        goalsDifference.prefWidthProperty().bind(tableMember.widthProperty().multiply(0.15));
        goalsDifference.setResizable(false);


        TableColumn points = new TableColumn("Points");
        points.setCellValueFactory(new PropertyValueFactory("numOfPoints"));
        points.setResizable(false);

        tableMember.setItems(observer);
        tableMember.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableMember.getColumns().addAll(date,position, clubName, played, won, lost, goalsAgainst, goalsDifference, points);


        // Match Table Button
        Button btnMatchTable = new Button("Match Table");
        btnMatchTable.setLayoutY(600);
        btnMatchTable.setLayoutX(500);

        btnMatchTable.setOnAction(event ->{
            primary.close();
            RandomMatch.playMatch();
        });

        VBox tblBox = new VBox();
        tblBox.setLayoutX(60);
        tblBox.setLayoutY(70);
        tblBox.setStyle("-fx-background-color: aquamarine");
        tblBox.setSpacing(5);
        tblBox.prefWidthProperty().bind(primary.widthProperty().multiply(0.9));
        tblBox.setPadding(new Insets(50, 50, 50, 50));
        tblBox.getChildren().addAll(tableMember);

        AnchorPane pane = new AnchorPane();
        pane.getChildren().addAll(tblBox,btnMatchTable);

        Scene tblScene = new Scene(pane, 1400, 700);
        primary.setTitle("Members Details");
        primary.setScene(tblScene);
        primary.setMaxWidth(1400);
        primary.setMaxHeight(700);
        primary.show();

    }

    private static TableColumn.SortType reverse(TableColumn.SortType st) {
        return TableColumn.SortType.values()[1-st.ordinal()];
    }
}

