import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestDecrementingScore {

  @DataProvider(name = "teamsAndScores")
  public Object[][] teamsAndScores() {
    return new Object[][]{
        {"Poland", "Denmark", 3, 2, 2, 1},
        {"Poland", "Denmark", 3, 2, 3, 1},
        {"Poland", "Denmark", 3, 2, 3, -1},
    };
  }

  @Test(dataProvider = "teamsAndScores")
  public void testUpdatingScore(String homeTeam,
                                String awayTeam,
                                int previousHomeScore,
                                int previousAwayScore,
                                int updatedHomeScore,
                                int updatedAwayScore) {
    //Given Scoreboard is created
    Scoreboard scoreboard = new Scoreboard();
    //And Match is started
    scoreboard.startMatch(new Match(homeTeam, awayTeam));
    Match match = scoreboard.getMatch(homeTeam, awayTeam);
    match.setScore(new Score(previousHomeScore, previousAwayScore));
    //When Score of either team is decremented
    match.setScore(new Score(updatedHomeScore, updatedAwayScore));
    //Then operation of updating score is aborted and score does not change
    Score score = scoreboard.getMatch(homeTeam, awayTeam).getScore();
    Assert.assertEquals(score.getHomeTeamScore(), previousHomeScore);
    Assert.assertEquals(score.getAwayTeamScore(), previousAwayScore);
  }
}
