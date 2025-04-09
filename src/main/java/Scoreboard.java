import java.util.LinkedList;

public class Scoreboard {

  final LinkedList<Match> startedMatches = new LinkedList<>();

  public void startMatch(String homeTeam, String awayTeam) {
    Match match = new Match(homeTeam, awayTeam);
    startedMatches.add(match);
  }

  public Match getMatch(String homeTeam, String awayTeam) {
    return startedMatches.stream()
        .filter(match -> match.hasTeams(homeTeam, awayTeam))
        .findFirst()
        .orElse(null);
  }

  public void finishMatch(String homeTeam, String awayTeam) {
    startedMatches.removeIf(match -> match.hasTeams(homeTeam, awayTeam));
  }
}
