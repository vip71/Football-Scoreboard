## Overview
This is a simple Java library that simulates a Live Football World Cup score Board. It allows you to add matches, update scores, and view the current match standings.
This project is being developed using TDD approach.

## Features
- Start match, adds match to scoreboard.
- Update match score.
- Finish match, removes match from scoreboard.
- Display scores for all currently played matches.


## Entities
### Scoreboard
Stores all the matches and allows for updating and viewing the scores. 
Matches are stored in list and are sorted by time of their start, from the most recent to the least recent.
#### `void startMatch(Match match)`
Starts a new match if neither team is already involved in another ongoing match.  
Logs a message if a match cannot be started due to team conflict.

#### `Match getMatch(String homeTeam, String awayTeam)`
Retrieves the first match that exactly matches the given home and away team names.  
Returns `null` and logs a message if no such match is found.

#### `void finishMatch(String homeTeam, String awayTeam)`
Finishes and removes the match that exactly matches the given teams.  
Logs a message if no such match exists to be finished.

#### `String getSummary()`
Returns a summary of all started matches using the `ScoreboardSummarizer`.
### ScoreboardSummarizer
Helper of scoreboard, prepares summary of all currently played matches.
### ScoreboardLogger
Helper of scoreboard, provides logging functionality.
### Match
Represents a match between two teams. Stores only names of home team and away team and score.

#### `boolean hasExactTeams(String otherHomeTeam, String otherAwayTeam)`
Checks whether the match involves exactly the given home and away team names.

#### `boolean sharesAnyTeamWith(Match newMatch)`
Checks whether this match shares either team with another match.

#### `void setScore(Score score)`
Updates the score of the match to the provided `score`, but only if the new score is not lower than the current one for either team.  
Logs an info message if an attempt is made to decrement the score.

#### `Score getScore()`
Returns the current score of the match.

#### `String toString()`
Returns a string representation of the match.

### Score
Represents the score of a match, two integers one for home team and the other for away team.

#### `int getHomeTeamScore()`
Returns the score of the home team.

#### `int getAwayTeamScore()`
Returns the score of the away team.

#### `int getTotal()`
Returns the total score of both teams combined.

#### `boolean isNotLowerThen(Score other)`
Checks if this score is greater than or equal to the given `other` score for both home and away teams.

#### `String toString()`
Returns a string representation of the score in the format `(home:away)`.
## Tests
- Tests are utilising TestNG library. Test data is separated from code and stored in data providers.
Tests are written using "Given-When-Then" format.

- All the features are tested. 
Negative tests are also performed in order to ensure proper error handling.
Some tests are checking logs to guarantee invalid usages of scoreboard are displayed.

- Tests of summarizing scores of matches have very complex data sets
so test data has been abstracted to supporting classes.

## Error Handling
- An attempt to add new match with currently playing team is aborted and results in logging error message.
- Acquiring match with teams that do not play right now returns null. 
Acquiring match with inverted home and away teams to existing match also returns null.
These actions result in logging error message.
- Finishing not existing match results in logging error message.
- An attempt to decrement score of team is aborted and results in logging error message. 
If user tries to set new decremented score for one team and valid score for opposing team in one attempt neither score is updated.

## Assumptions
- Team can play only in one match at once.
- Score cannot be decremented.
- Tracking real-life time of matches is not responsibility of this library.
- Scoreboard can be reused to track matches from other events like champions league.
In order to ensure system flexibility names of each team are not checked if they are actually names of countries.
The only requirement for team name is not to be null.
- Updating score is generally more frequent than summarizing current matches, 
so it is less computationally expensive to sort matches before summarizing
then updating elements position in list each time score changes.

## Conventions
- Lombok annotations are used to enhance quality of codebase, by eliminating generic parts of code.
- Invalid usages of scoreboard are displayed as SLF4J logger messages.
- An attempts to acquire not existing matches from scoreboard return null values.
Similar solution can be found in Java Map Class for getting values from not existing keys.
This convention ensures that library is user-friendly 
by avoiding using try-catch blocks and optionals that increase visual complexity of code.
The drawback of this solution is that user has to check if value is not null on their own.
The aim of project was to prepare the simplest solution to the problem and usage of
optionals or try-catch blocks is in opinion of developer more complex.
- Operations on collections that do not use indexing are written using streams api.
- Operations on collections that use indexing are written as traditional for loops.
