import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Optional;

public class Scoreboard {

  private static final Logger logger = LoggerFactory.getLogger(Scoreboard.class);
  private final LinkedList<Match> startedMatches = new LinkedList<>();
  private final ScoreboardSummarizer summarizer = new ScoreboardSummarizer(startedMatches);

  public void startMatch(Match match) {
    startedMatches.addFirst(match);
  }

  public Match getMatch(String homeTeam, String awayTeam) {
    return
        filterFirstMatch(homeTeam, awayTeam)
            .orElseGet(() -> {
              logMissingMatch(homeTeam, awayTeam);
              return null;
            });
  }

  private Optional<Match> filterFirstMatch(String homeTeam, String awayTeam) {
    return startedMatches.stream()
        .filter(match -> match.hasTeams(homeTeam, awayTeam))
        .findFirst();
  }

  private void logMissingMatch(String homeTeam, String awayTeam) {
    logger.info("Match with home team " + homeTeam + " and away team " + awayTeam + " has not been found");
  }

  public void finishMatch(String homeTeam, String awayTeam) {
    startedMatches.removeIf(match -> match.hasTeams(homeTeam, awayTeam));
  }

  public String getSummary() {
    return summarizer.getSummary();
  }
}
