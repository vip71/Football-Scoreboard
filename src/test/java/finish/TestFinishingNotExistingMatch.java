package finish;

import base.LoggerTestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import scoreboard.Scoreboard;

public class TestFinishingNotExistingMatch extends LoggerTestBase {

  @DataProvider(name = "teams")
  public Object[][] teams() {
    return new Object[][]{
        {"Poland", "Denmark"},
    };
  }

  @Override
  public Class<?> getLoggerClass() {
    return Scoreboard.class;
  }

  private String getExpectedLog(String homeTeam, String awayTeam) {
    return "Match with home team " + homeTeam + " and away team " + awayTeam + " cannot be finished";
  }

  @Test(dataProvider = "teams")
  public void testFinishingNotExistingMatch(String homeTeam, String awayTeam) {
    //Given Scoreboard is created
    Scoreboard scoreboard = new Scoreboard();
    //When User tries to finish not existing match
    scoreboard.finishMatch(homeTeam, awayTeam);
    //Then Attempt to finish not started match is noted in logger
    Assert.assertTrue(logContains(getExpectedLog(homeTeam, awayTeam)));
  }

}
