import java.util.LinkedList;

public class Scoreboard {

  final LinkedList<Match> startedMatches = new LinkedList<>();

  public void startMatch(String homeTeam, String awayTeam) {
    Match match = new Match(homeTeam, awayTeam);
    startedMatches.add(match);
  }

  public Match getMatch(String homeTeam, String awayTeam) {
    for (Match match : startedMatches) {
      if(match.hasTeams(homeTeam, awayTeam)){
        return match;
      }
    }
    return null;
  }
}
