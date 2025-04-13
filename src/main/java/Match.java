import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Match {

  private static final Logger logger = LoggerFactory.getLogger(Match.class);
  private final String homeTeam;
  private final String awayTeam;
  private Score score = new Score(0, 0);

  public Match(String homeTeam, String awayTeam) {
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
  }

  public boolean hasTeams(String otherHomeTeam, String otherAwayTeam) {
    return homeTeam.equals(otherHomeTeam) && awayTeam.equals(otherAwayTeam);
  }

  public Score getScore() {
    return score;
  }

  public void setScore(Score newScore) {
    if (newScore.isNotLowerThen(getScore())) {
      this.score = newScore;
    } else {
      logAttemptToDecrementScore(newScore);
    }
  }

  private void logAttemptToDecrementScore(Score newScore) {
    logger.info("Score " + getScore() + " cannot be decremented to " + newScore);
  }

  @Override
  public String toString() {
    return homeTeam + " " + getScore().getHomeTeamScore() + " - "
        + awayTeam + " " + getScore().getAwayTeamScore();
  }
}
