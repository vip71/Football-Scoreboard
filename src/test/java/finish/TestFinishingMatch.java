package finish;

import match.Match;
import scoreboard.Scoreboard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestFinishingMatch {

  @DataProvider(name = "teams")
  public Object[][] teams() {
    return new Object[][]{
        {"Poland", "Denmark"},
    };
  }

  @Test(dataProvider = "teams")
  public void testFinishingMatch(String homeTeam, String awayTeam) {
    //Given Scoreboard has been created
    Scoreboard scoreboard = new Scoreboard();
    //And Match has been started
    scoreboard.startMatch(new Match(homeTeam, awayTeam));
    //When Match is finished
    scoreboard.finishMatch(homeTeam, awayTeam);
    //Then Match is removed from scoreboard
    Match match = scoreboard.getMatch(homeTeam, awayTeam);
    Assert.assertNull(match);
  }
}

