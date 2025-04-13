package start;

import base.LoggerCheckingTest;
import match.Match;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import scoreboard.Scoreboard;
import scoreboard.ScoreboardLogger;

public class TestGettingNotStartedMatch extends LoggerCheckingTest {

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
    return ScoreboardLogger.class;
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
    Assert.assertTrue(logContains(getMatchHasNotBeenFoundLog(invalidHomeTeam, invalidAwayTeam)));
  }

}
