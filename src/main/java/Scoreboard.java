import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Optional;

public class Scoreboard {

  private final LinkedList<Match> startedMatches = new LinkedList<>();

  private final ScoreboardSummarizer summarizer = new ScoreboardSummarizer(startedMatches);

  private static final Logger logger = LoggerFactory.getLogger(Scoreboard.class);

  public void startMatch(Match match) {
    startedMatches.addFirst(match);
  }

  public Match getMatch(String homeTeam, String awayTeam) {
    return
        filterFirstMatch(homeTeam, awayTeam)
        .orElseGet(() -> {
          handleMissingMatch(homeTeam, awayTeam);
          return null;
        });
  }

  private Optional<Match> filterFirstMatch(String homeTeam, String awayTeam) {
    return startedMatches.stream()
        .filter(match -> match.hasTeams(homeTeam, awayTeam))
        .findFirst();
  }

  private static void handleMissingMatch(String homeTeam, String awayTeam) {
    logger.info("Match with teams: " + homeTeam + ", " + awayTeam + " has not been found");
  }

  public void finishMatch(String homeTeam, String awayTeam) {
    startedMatches.removeIf(match -> match.hasTeams(homeTeam, awayTeam));
  }

  public String getSummary() {
    return summarizer.getSummary();
  }
}
