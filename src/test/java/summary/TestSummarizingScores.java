package summary;

import match.Match;
import score.Score;
import scoreboard.Scoreboard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestSummarizingScores {

  @DataProvider(name = "teamsAndScores")
  public Object[][] teamsAndScores() {
    String[] homeTeams = {"Mexico", "Spain", "Germany", "Uruguay", "Argentina"};
    String[] awayTeams = {"Canada", "Brazil", "France", "Italy", "Australia"};
    int[] homeTeamScores = {0, 10, 2, 6, 3};
    int[] awayTeamScores = {5, 2, 2, 6, 1};
    String expectedSummary =
        """
            1. Uruguay 6 - Italy 6
            2. Spain 10 - Brazil 2
            3. Mexico 0 - Canada 5
            4. Argentina 3 - Australia 1
            5. Germany 2 - France 2""";

    return new Object[][]{
        {homeTeams, awayTeams, homeTeamScores, awayTeamScores, expectedSummary}
    };
  }

  @Test(dataProvider = "teamsAndScores")
  public void testUpdatingScore(String[] homeTeams,
                                String[] awayTeams,
                                int[] homeTeamScores,
                                int[] awayTeamScores,
                                String expectedSummary) {

    //Given Scoreboard is created
    Scoreboard scoreboard = new Scoreboard();
    //When Matches are started in particular order
    for (int i = 0; i < homeTeams.length; i++) {
      scoreboard.startMatch(new Match(homeTeams[i], awayTeams[i]));
    }
    //And Their scores are updated
    Match current;
    for (int i = 0; i < homeTeams.length; i++) {
      current = scoreboard.getMatch(homeTeams[i], awayTeams[i]);
      current.setScore(new Score(homeTeamScores[i], awayTeamScores[i]));
    }
    //Then Scoreboard summary is ordered by total goals and start time
    Assert.assertEquals(scoreboard.getSummary(), expectedSummary);
  }
}
