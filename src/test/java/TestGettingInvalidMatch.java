import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestGettingInvalidMatch {

  @DataProvider(name = "teams")
  public Object[][] teams() {
    return new Object[][]{
        {"Poland", "Denmark", "England", "Ukraine"},
        {"Poland", "Denmark", "Denmark", "Poland"},
        {"Poland", "Denmark", "Poland", "Ukraine"},
    };
  }

  @Test(dataProvider = "teams")
  public void testGettingInvalidMatch(String homeTeam,
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
  }

}
