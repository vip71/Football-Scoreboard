package start;

import base.LoggerTestBase;
import match.Match;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import scoreboard.Scoreboard;

public class TestStartingMatchDuplicate extends LoggerTestBase {

  @DataProvider(name = "teams")
  public Object[][] teams() {
    return new Object[][]{
        {"Poland", "Denmark", "Poland", "Denmark"},
    };
  }

  @Override
  public Class<?> getLoggerClass() {
    return Scoreboard.class;
  }

  private String getExpectedLog(Match match) {
    return "Match " + match + " cannot be started because one of teams is occupied";
  }

  @Test(dataProvider = "teams")
  public void testStartingMatchWithOccupiedTeam(String homeTeam,
                                                String awayTeam,
                                                String invalidHomeTeam,
                                                String invalidAwayTeam) {
    //Given Scoreboard is created and one match is started
    Scoreboard scoreboard = new Scoreboard();
    scoreboard.startMatch(new Match(homeTeam, awayTeam));
    //When User tries to start match with same teams
    Match invalidMatch = new Match(invalidHomeTeam, invalidAwayTeam);
    scoreboard.startMatch(invalidMatch);
    //Then An attempt to create match with occupied teams was noted in logger
    Assert.assertTrue(logContains(getExpectedLog(invalidMatch)));
  }
}
