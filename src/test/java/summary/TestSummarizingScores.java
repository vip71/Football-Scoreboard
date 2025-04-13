package summary;

import match.Match;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import score.Score;
import scoreboard.Scoreboard;

public class TestSummarizingScores {

  private static void startMatches(TestData data, Scoreboard scoreboard) {
    for (int i = 0; i < data.getHomeTeams().length; i++) {
      scoreboard.startMatch(new Match(data.getHomeTeams()[i], data.getAwayTeams()[i]));
    }
  }

  private static void updateMatchesScores(TestData data, Scoreboard scoreboard) {
    for (int i = 0; i < data.getHomeTeams().length; i++) {
      updateMatchScore(data, scoreboard, i);
    }
  }

  private static void updateMatchScore(TestData data, Scoreboard scoreboard, int i) {
    Match current = scoreboard.getMatch(data.getHomeTeams()[i], data.getAwayTeams()[i]);
    if (current != null) {
      current.setScore(new Score(data.getScoresHome()[i], data.getScoresAway()[i]));
    }
  }

  @DataProvider(name = "teamsAndScores")
  public Object[][] teamsAndScores() {
    return TestCases.getCases();
  }

  @Test(dataProvider = "teamsAndScores")
  public void testUpdatingScore(TestData data) {
    //Given Scoreboard is created
    Scoreboard scoreboard = new Scoreboard();
    //When Matches are started in particular order
    startMatches(data, scoreboard);
    //And Their scores are updated
    updateMatchesScores(data, scoreboard);
    //Then Scoreboard summary is ordered by total goals and start time
    Assert.assertEquals(scoreboard.getSummary(), data.getExpectedSummary());
  }
}
