package sportsLeague;

import javafx.application.Application;

import java.util.Scanner;

public class ConsoleApplication {
    private static boolean decide;
    private static String clubName;
    private static String location;
    private static boolean clubNameCheck;
    private static boolean locationCheck;
    private static String homeTeam;
    private static String awayTeam;
    private static int homeTeamScore;
    private static int awayTeamScore;
    private static String date;
    private static boolean homeTeamCheck;
    private static boolean awayTeamCheck;
    private static boolean homeTeamScoreCheck;
    private static boolean awayTeamScoreCheck;
    private static boolean dateCheck;
    static LeagueManager manager = new PremierLeagueManager();
    static Scanner USER_INPUT = new Scanner(System.in);

    public static void main(String[] args) {
       manager.loadData();  // Call the function for load the file
        menu:
        do {
            try {
                USER_INPUT = new Scanner(System.in);
                displayMenu();
                System.out.print("\nEnter the option here : ");
                int option = USER_INPUT.nextInt();

                switch (option) {
                    case 1:
                        addFootballClub();
                        endOfDecide();
                        break;
                    case 2:
                        manager.delFootballClub();
                        endOfDecide();
                        break;
                    case 3:
                        DisplayClubDetails();
                        endOfDecide();
                        break;

                    case 4:
                        manager.displayPremierLeagueTable();
                        endOfDecide();
                        break;
                    case 5:
                        addPlayMatch();
                        endOfDecide();
                        break;
                    case 6:
                        Application.launch(PremierLeagueGui.class); // For gui
                        break ;
                    case 7:
                        System.out.println("\n:::::::::::Thank You For Use the Premier League Console Application::::::::");
                        decide = false;
                        manager.saveData();
                        break menu;
                    default:
                        System.out.println("\nYou Selected an Invalid Option. Please Try Again! ");
                        endOfDecide();
                }
            } catch (Exception e) {

                System.out.println("\nPlease Enter Valid input");
                decide = true;
            }
        } while (decide);
    }

    private static void displayMenu() {
        System.out.println("\n:::::::::::::::::::::::::::Welcome to Premier League Football::::::::::::::::::::::::::");
        System.out.println("\n1: Create a new football club and add it in the premier league.");
        System.out.println("\n2: Delete relegated an existing club from the premier league");
        System.out.println("\n3: Display the Information about selected Club ");
        System.out.println("\n4: Display the Premier League Table");
        System.out.println("\n5: Add a played match with its score and its date ");
        System.out.println("\n6: Open the GUI Application ");
        System.out.println("\n7: Save and Quit the Application\n");
        System.out.println("\n::::::::::::::::::::::::::::Choose the option You want:::::::::::::::::::::::::::::::::");
    }

    private static void addFootballClub() {
        SportsClub sportsClub;

        do {
            System.out.print("\nEnter the Club Name: ");
            clubName = USER_INPUT.next();
            clubNameCheck = clubName.matches("^[a-zA-Z]*$");

            if (clubNameCheck) {
                break;
            } else {
                System.out.println("Please enter the valid Foot club name");
            }
        } while (!clubNameCheck);

        do {
            System.out.print("\nEnter the Location of Club: ");
            location = USER_INPUT.next();
            locationCheck = location.matches("^[a-zA-Z]*$");

            if (locationCheck) {
                break;
            } else {
                System.out.println("Please valid the valid location name");
            }
        } while (!locationCheck);

        sportsClub = new FootballClub(clubName, location, 0, 0, 0, 0, 0, 0, 0, 0, "", 0);
        manager.addFootballClub(sportsClub);


    }

    private static void addPlayMatch() {
        SportsClub sportsClub;

        do {
            System.out.print("\nEnter the Home Club name : ");
            homeTeam = USER_INPUT.next();
            homeTeamCheck = homeTeam.matches("^[a-zA-Z]*$"); // Check the String using regex

            if (homeTeamCheck) {
                break;
            } else {
                System.out.println("Please enter valid Club Name");
            }
        } while (!homeTeamCheck);


        do {
            System.out.print("\nEnter the Away Club Name : ");
            awayTeam = USER_INPUT.next();
            awayTeamCheck = awayTeam.matches("^[a-zA-Z]*$");

            if (awayTeamCheck) {
                break;
            } else {
                System.out.println("Please enter Valid Club Name");
            }
        } while (!awayTeamCheck);


        do {
            try {
                USER_INPUT = new Scanner(System.in);
                System.out.print("\nEnter the Home Club Score : ");
                homeTeamScore = USER_INPUT.nextInt();
                homeTeamScoreCheck = false;
            } catch (Exception e) {
                System.out.println("Please enter the valid input");
                homeTeamScoreCheck = true;
            }
        } while (homeTeamScoreCheck);

        do {
            try {
                USER_INPUT = new Scanner(System.in);
                System.out.print("\nEnter the Away Club Score : ");
                awayTeamScore = USER_INPUT.nextInt();
                awayTeamScoreCheck = false;
            } catch (Exception e) {
                System.out.println("Please enter the valid input");
                awayTeamScoreCheck = true;
            }
        } while (awayTeamScoreCheck);


        do {
            System.out.print("\nEnter the Date: ");
            date = USER_INPUT.next();
            dateCheck = date.matches("^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-([12][0-9]{3})$"); // check the Date Format

            if (dateCheck) {
                break;
            } else {
                System.out.println("Please Enter Valid Date Format Like this (dd-mm-yyyy) ");
            }
        } while (!dateCheck);

        sportsClub = new FootballClub("", "", homeTeam, awayTeam, homeTeamScore, awayTeamScore, date);
        manager.addPlayMatch(homeTeam, awayTeam, homeTeamScore, awayTeamScore, date);
        PremierLeagueManager.sportsClubList2.add(sportsClub);

    }

    private static void DisplayClubDetails() {

        System.out.print("\nEnter the Club Name: ");
        clubName = USER_INPUT.next();
        manager.DisplayClubDetails(clubName);

    }

    private static void endOfDecide() {
        while (true) {
            System.out.println("\nExit the Program Enter :: E  \nContinue the Program Enter :: C");
            System.out.println("\n:::::::::::::::::::::::::::::::Choose the Option You Want :::::::::::::::::::::::::::");
            System.out.print("\nEnter the Option here : ");
            String decision = USER_INPUT.next();

            if (decision.equalsIgnoreCase("e")) {
                System.out.println("::::::::::::::::::::::::::::::: Exit the Program ::::::::::::::::::::::::::::::::");
                decide = false;
                break;
            } else if (decision.equalsIgnoreCase("c")) {
                decide = true;
                break;
            } else {
                System.out.println("Please type valid command");
            }

        }
    }


}

