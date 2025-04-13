package scoreboard;

import lombok.RequiredArgsConstructor;
import match.Match;

import java.util.Comparator;
import java.util.LinkedList;

import static java.util.Comparator.comparingInt;

@RequiredArgsConstructor
public class ScoreboardSummarizer {

  private static final Comparator<Match> DESCENDING_SCORE_COMPARATOR
      = comparingInt((Match match) -> -match.getScore().getTotal());

  private final LinkedList<Match> startedMatches;

  public String getSummary() {
    Match[] sortedMatches = sortByDescendingScore(startedMatches);
    String[] records = getSummaryRecords(sortedMatches);
    return joinRecords(records);
  }

  private String joinRecords(String[] records) {
    return String.join("\n", records);
  }

  private String[] getSummaryRecords(Match[] matches) {
    String[] result = new String[matches.length];
    for (int i = 0; i < matches.length; i++) {
      result[i] = getSummaryRecord(matches, i);
    }
    return result;
  }

  private String getSummaryRecord(Match[] matches, int i) {
    return (i + 1) + ". " + matches[i].toString();
  }

  private Match[] sortByDescendingScore(LinkedList<Match> startedMatches) {
    return startedMatches.stream()
        .sorted(DESCENDING_SCORE_COMPARATOR)
        .toArray(Match[]::new);
  }
}
