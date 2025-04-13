public class Score {
  private final int homeTeamScore;

  private final int awayTeamScore;

  public Score(int homeTeamScore, int awayTeamScore) {
    this.homeTeamScore = homeTeamScore;
    this.awayTeamScore = awayTeamScore;
  }

  public int getHomeTeamScore() {
    return homeTeamScore;
  }

  public int getAwayTeamScore() {
    return awayTeamScore;
  }

  public int getTotal() {
    return getHomeTeamScore() + getAwayTeamScore();
  }

  public boolean isNotLowerThen(Score other) {
    return getAwayTeamScore() >= other.getHomeTeamScore()
        && getAwayTeamScore() >= other.getAwayTeamScore();
  }
}
