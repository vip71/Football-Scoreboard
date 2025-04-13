package summary;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class TestData {
  @Getter
  private final String[] homeTeams;
  @Getter
  private final String[] awayTeams;
  @Getter
  private final int[] scoresHome;
  @Getter
  private final int[] scoresAway;
  @Getter
  private final String expectedSummary;
}
