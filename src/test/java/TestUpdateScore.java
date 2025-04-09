import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestUpdateScore {
  @DataProvider(name = "teamsAndScores")
  public Object[][] teams() {
    return new Object[][]{
        {"Poland", "Denmark", 3, 2},
    };
  }

  @Test(dataProvider = "teamsAndScores")
  public void testUpdatingScore(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {
    //Given Scoreboard is created
    Scoreboard scoreboard = new Scoreboard();
    //And Match is started
    scoreboard.startMatch(homeTeam, awayTeam);
    //When Match score is updated
    scoreboard.getMatch(homeTeam,awayTeam).setScore(homeTeamScore, awayTeamScore);
    //Then Match score is saved in scoreboard
    Score score = scoreboard.getMatch(homeTeam, awayTeam).getScore();
    Assert.assertEquals(score.getHomeTeamScore(), homeTeamScore);
    Assert.assertEquals(score.getAwayTeamScore(), awayTeamScore);
  }
}
