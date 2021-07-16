package sportsLeague;

public interface LeagueManager {
    void addFootballClub(SportsClub sportsClub);
    void delFootballClub();
    void DisplayClubDetails(String name);
    void displayPremierLeagueTable();
    void addPlayMatch(String homeTeam,String awayTeam, int homeTeamScore,int awayTeamScore, String date);
    void saveData() ;
    void loadData() ;

}
