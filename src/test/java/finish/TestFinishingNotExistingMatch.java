package finish;

import base.LoggerCheckingTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import scoreboard.Scoreboard;
import scoreboard.ScoreboardLogger;

public class TestFinishingNotExistingMatch extends LoggerCheckingTest {

  @DataProvider(name = "teams")
  public Object[][] teams() {
    return new Object[][]{
        {"Poland", "Denmark"},
    };
  }

  @Override
  public Class<?> getLoggerClass() {
    return ScoreboardLogger.class;
  }

  @Test(dataProvider = "teams")
  public void testFinishingNotExistingMatch(String homeTeam, String awayTeam) {
    //Given Scoreboard is created
    Scoreboard scoreboard = new Scoreboard();
    //When User tries to finish not existing match
    scoreboard.finishMatch(homeTeam, awayTeam);
    //Then Attempt to finish not started match is noted in logger
    Assert.assertTrue(logContains(getMatchCannotBeFinishedLog(homeTeam, awayTeam)));
  }

}
