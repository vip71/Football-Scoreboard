package base;

import match.Match;
import score.Score;

public abstract class LoggerCheckingTest extends LoggerListeningTest {

  protected String getMatchCannotBeFinishedLog(String homeTeam, String awayTeam) {
    return "Match with home team " + homeTeam + " and away team " + awayTeam + " cannot be finished";
  }

  protected String getMatchHasNotBeenFoundLog(String homeTeam, String awayTeam) {
    return "Match with home team " + homeTeam + " and away team " + awayTeam + " has not been found";
  }

  protected String getMatchCannotBeStartedLog(Match match) {
    return "Match " + match + " cannot be started because one of teams is occupied";
  }

  protected String getScoreCannotBeDecrementedLog(Score newScore, Score score) {
    return "score " + score + " cannot be decremented to " + newScore;
  }

}
