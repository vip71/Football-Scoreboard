import java.util.LinkedList;

import static java.util.Comparator.comparingInt;

public class Scoreboard {

  final LinkedList<Match> startedMatches = new LinkedList<>();

  public void startMatch(String homeTeam, String awayTeam) {
    Match match = new Match(homeTeam, awayTeam);
    startedMatches.addFirst(match);
  }

  public Match getMatch(String homeTeam, String awayTeam) {
    return startedMatches.stream()
        .filter(match -> match.hasTeams(homeTeam, awayTeam))
        .findFirst()
        .orElse(null);
  }

  public void finishMatch(String homeTeam, String awayTeam) {
    startedMatches.removeIf(match -> match.hasTeams(homeTeam, awayTeam));
  }

  public String getSummary() {
    Match[] sortedMatches = startedMatches.stream()
        .sorted(comparingInt(match -> -match.getScore().getTotal()))
        .toArray(Match[]::new);

    String[] matchSummaries = new String[sortedMatches.length];
    for(int i = 0; i < sortedMatches.length; i++){
      matchSummaries[i] = (i + 1) + ". "+sortedMatches[i].toString();
    }
    return String.join("\n",matchSummaries);
  }
}
