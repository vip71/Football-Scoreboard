package scoreboard;

import lombok.extern.slf4j.Slf4j;
import match.Match;

import java.util.LinkedList;
import java.util.Optional;

@Slf4j
public class Scoreboard {

  private final LinkedList<Match> startedMatches = new LinkedList<>();
  private final ScoreboardSummarizer summarizer = new ScoreboardSummarizer(startedMatches);

  public void startMatch(Match newMatch) {
    if (canMatchBeStarted(newMatch)) {
      startedMatches.addFirst(newMatch);
    } else {
      logMatchWithOccupiedTeam(newMatch);
    }
  }

  private void logMatchWithOccupiedTeam(Match match) {
    log.info("Match " + match + " cannot be started because one of teams is occupied");
  }

  private boolean canMatchBeStarted(Match newMatch) {
    return startedMatches.stream().noneMatch(startedMatch -> startedMatch.sharesAnyTeamWith(newMatch));
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
        .filter(match -> match.hasExactTeams(homeTeam, awayTeam))
        .findFirst();
  }

  private void logMissingMatch(String homeTeam, String awayTeam) {
    log.info("Match with home team " + homeTeam + " and away team " + awayTeam + " has not been found");
  }

  public void finishMatch(String homeTeam, String awayTeam) {
    startedMatches.removeIf(match -> match.hasExactTeams(homeTeam, awayTeam));
  }

  public String getSummary() {
    return summarizer.getSummary();
  }
}
