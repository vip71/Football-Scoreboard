public class Match {

  private final String homeTeam;

  private final String awayTeam;

  private Score score = new Score(0, 0);

  public Match(String homeTeam, String awayTeam) {
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
  }

  public String getHomeTeam() {
    return homeTeam;
  }

  public String getAwayTeam() {
    return awayTeam;
  }

  public boolean hasTeams(String homeTeam, String awayTeam) {
    return getHomeTeam().equals(homeTeam) && getAwayTeam().equals(awayTeam);
  }

  public Score getScore() {
    return score;
  }

  public void setScore(Score score) {
    this.score = score;
  }

  @Override
  public String toString() {
    return getHomeTeam() + " " + getScore().getHomeTeamScore() + " - "
        + getAwayTeam() + " " + getScore().getAwayTeamScore();
  }
}
