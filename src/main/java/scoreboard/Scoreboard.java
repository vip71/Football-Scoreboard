package scoreboard;

import match.Match;

import java.util.LinkedList;
import java.util.Optional;

public class Scoreboard {

  private final LinkedList<Match> startedMatches = new LinkedList<>();
  private final ScoreboardSummarizer summarizer = new ScoreboardSummarizer(startedMatches);
  private final ScoreboardLogger logger = new ScoreboardLogger();

  public void startMatch(Match newMatch) {
    if (canMatchBeStarted(newMatch)) {
      startedMatches.addFirst(newMatch);
    } else {
      logger.logMatchWithOccupiedTeam(newMatch);
    }
  }

  private boolean canMatchBeStarted(Match newMatch) {
    return startedMatches.stream().noneMatch(startedMatch -> startedMatch.sharesAnyTeamWith(newMatch));
  }

  public Match getMatch(String homeTeam, String awayTeam) {
    return
        filterFirstMatch(homeTeam, awayTeam)
            .orElseGet(() -> {
              logger.logMissingMatch(homeTeam, awayTeam);
              return null;
            });
  }

  private Optional<Match> filterFirstMatch(String homeTeam, String awayTeam) {
    return startedMatches.stream()
        .filter(match -> match.hasExactTeams(homeTeam, awayTeam))
        .findFirst();
  }

  public void finishMatch(String homeTeam, String awayTeam) {
    if (hasMatch(homeTeam, awayTeam)) {
      startedMatches.removeIf(match -> match.hasExactTeams(homeTeam, awayTeam));
    } else {
      logger.logMatchCannotBeFinished(homeTeam, awayTeam);
    }
  }

  private boolean hasMatch(String homeTeam, String awayTeam) {
    return filterFirstMatch(homeTeam, awayTeam).isPresent();
  }

  public String getSummary() {
    return summarizer.getSummary();
  }
}
