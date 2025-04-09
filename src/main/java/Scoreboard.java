import java.util.LinkedList;
import java.util.Objects;

public class Scoreboard {

  LinkedList<Match> startedMatches = new LinkedList<>();

  public void startMatch(String homeTeam, String awayTeam) {
    Match match = new Match(homeTeam, awayTeam);
    startedMatches.add(match);
  }

  public Match getMatch(String homeTeam, String awayTeam) {
    for (Match match : startedMatches) {
      if(Objects.equals(match.getHomeTeam(), homeTeam) && Objects.equals(match.getAwayTeam(), awayTeam)){
        return match;
      }
    }
    return null;
  }
}
