package match;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import score.Score;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class Match {

  private final String homeTeam;
  private final String awayTeam;

  @Getter
  private Score score = new Score(0, 0);

  public boolean hasTeams(String otherHomeTeam, String otherAwayTeam) {
    return homeTeam.equals(otherHomeTeam) && awayTeam.equals(otherAwayTeam);
  }

  public void setScore(Score newScore) {
    if (newScore.isNotLowerThen(getScore())) {
      this.score = newScore;
    } else {
      logAttemptToDecrementScore(newScore);
    }
  }

  private void logAttemptToDecrementScore(Score newScore) {
    log.info("score " + getScore() + " cannot be decremented to " + newScore);
  }

  @Override
  public String toString() {
    return homeTeam + " " + getScore().getHomeTeamScore() + " - "
        + awayTeam + " " + getScore().getAwayTeamScore();
  }

  public boolean hasSameTeam(Match newMatch) {
    return homeTeam.equals(newMatch.homeTeam)
        || awayTeam.equals(newMatch.homeTeam)
        || homeTeam.equals(newMatch.awayTeam)
        || awayTeam.equals(newMatch.awayTeam);
  }
}
