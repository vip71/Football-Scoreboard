import java.util.LinkedList;

public class Scoreboard {

  private final LinkedList<Match> startedMatches = new LinkedList<>();

  private final ScoreboardSummarizer summarizer = new ScoreboardSummarizer(startedMatches);

  public void startMatch(Match match) {
    startedMatches.addFirst(match);
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

  public String getSummary() {
    return summarizer.getSummary();
  }
}
