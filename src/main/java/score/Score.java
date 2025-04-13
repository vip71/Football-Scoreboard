package score;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Score {
  @Getter
  private final int homeTeamScore;
  @Getter
  private final int awayTeamScore;

  public int getTotal() {
    return homeTeamScore + awayTeamScore;
  }

  public boolean isNotLowerThen(Score other) {
    return homeTeamScore >= other.homeTeamScore
        && awayTeamScore >= other.awayTeamScore;
  }

  @Override
  public String toString() {
    return "(" + homeTeamScore + ":" + awayTeamScore + ")";
  }
}
