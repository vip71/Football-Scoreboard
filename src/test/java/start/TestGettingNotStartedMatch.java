package start;

import base.LoggerTestBase;
import match.Match;
import scoreboard.Scoreboard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestGettingNotStartedMatch extends LoggerTestBase {

  @DataProvider(name = "teams")
  public Object[][] teams() {
    return new Object[][]{
        {"Poland", "Denmark", "England", "Ukraine"},
        {"Poland", "Denmark", "Denmark", "Poland"},
        {"Poland", "Denmark", "Poland", "Ukraine"},
    };
  }

  @Override
  public Class<?> getLoggerClass() {
    return Scoreboard.class;
  }

  private String getExpectedLog(String homeTeam, String awayTeam) {
    return "Match with home team " + homeTeam + " and away team " + awayTeam + " has not been found";
  }

  @Test(dataProvider = "teams")
  public void testGettingNotStartedMatch(String homeTeam,
                                         String awayTeam,
                                         String invalidHomeTeam,
                                         String invalidAwayTeam) {
    //Given Scoreboard is created
    Scoreboard scoreboard = new Scoreboard();
    //And One match is started
    scoreboard.startMatch(new Match(homeTeam, awayTeam));
    //When Match with different team names is acquired
    Match match = scoreboard.getMatch(invalidHomeTeam, invalidAwayTeam);
    //Then Match is not found
    Assert.assertNull(match);
    //And An attempt to get invalid match was noted in logger
    Assert.assertTrue(logContains(getExpectedLog(invalidHomeTeam, invalidAwayTeam)));
  }

}
