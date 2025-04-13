package match;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import score.Score;

@Slf4j
@RequiredArgsConstructor
public class Match {

  private final String homeTeam;
  private final String awayTeam;

  @Getter
  private Score score = new Score(0, 0);

  public boolean hasExactTeams(String otherHomeTeam, String otherAwayTeam) {
    return homeTeam.equals(otherHomeTeam) && awayTeam.equals(otherAwayTeam);
  }

  public boolean sharesAnyTeamWith(Match newMatch) {
    return containsTeam(newMatch.homeTeam) || containsTeam(newMatch.awayTeam);
  }

  private boolean containsTeam(String team) {
    return homeTeam.equals(team) || awayTeam.equals(team);
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
}
