## Overview
This is a simple Java library that simulates a Live Football World Cup score Board. It allows you to add matches, update scores, and view the current match standings.
This project is being developed using TDD approach.

## Features
- Start match, adds match to scoreboard.
- Update match score.
- Finish match, removes match from scoreboard.
- Display scores for all currently played matches.


## Entities
- **scoreboard**: Stores all the matches and allows for updating and viewing the scores. 
Matches are stored in list and are sorted by time of their start, from the most recent to the least recent. 
- **scoreboard summarizer**: Helper of scoreboard, prepares summary of all currently played matches.
- **scoreboard logger**: Helper of scoreboard, provides logging functionality.
- **match**: Represents a match between two teams. Stores only names of home team and away team and score.
- **score**: Represents the score of a match, two integers one for home team and the other for away team.

## Tests
Tests are utilising TestNG library. Test data is separated from code and stored in data providers.
Tests are written using "Given-When-Then" format.
All the features are tested. 
Negative tests are also performed in order to ensure proper error handling.
Some tests are checking logs to guarantee invalid usages of scoreboard are displayed.

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
- Updating score is generally more frequent than summarizing current matches, 
so it is less computationally expensive to sort matches before summarizing
then updating elements position in list each time score changes.
- Scoreboard can be reused to track matches from other events like champions league.
In order to ensure system flexibility names of each team are not checked if they are actually names of countries.

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
