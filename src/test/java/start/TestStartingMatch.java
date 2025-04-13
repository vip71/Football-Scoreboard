package start;

import match.Match;
import scoreboard.Scoreboard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestStartingMatch {

  @DataProvider(name = "teams")
  public Object[][] teams() {
    return new Object[][]{
        {"Poland", "Denmark"},
    };
  }

  @Test(dataProvider = "teams")
  public void testStartingMatch(String homeTeam, String awayTeam) {
    //Given Scoreboard is created
    Scoreboard scoreboard = new Scoreboard();
    //When Match is started
    scoreboard.startMatch(new Match(homeTeam, awayTeam));
    //Then match is saved in scoreboard
    Match match = scoreboard.getMatch(homeTeam, awayTeam);
    Assert.assertNotNull(match);
  }

}
