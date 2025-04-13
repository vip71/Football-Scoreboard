package scoreboard;

import lombok.extern.slf4j.Slf4j;
import match.Match;

@Slf4j
public class ScoreboardLogger {

  void logMatchWithOccupiedTeam(Match match) {
    log.info("Match " + match + " cannot be started because one of teams is occupied");
  }

  void logMissingMatch(String homeTeam, String awayTeam) {
    log.info("Match with home team " + homeTeam + " and away team " + awayTeam + " has not been found");
  }

  void logMatchCannotBeFinished(String homeTeam, String awayTeam) {
    log.info("Match with home team " + homeTeam + " and away team " + awayTeam + " cannot be finished");
  }
}
