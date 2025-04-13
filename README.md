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
- **scoreboard Summarizer**: Helper of scoreboard, prepares summary of all currently played matches.
- **match**: Represents a match between two teams. Stores only names of home team and away team and score.
- **score**: Represents the score of a match, two integers one for home team and the other for away team.

## Tests
Tests are utilising TestNG library. Test data is separated from code and stored in data providers.
Tests are written using "Given-When-Then" format.
All the features are tested. 
Negative tests are also performed in order to ensure proper error handling.

## Error Handling
- An attempt to add new match with currently playing team is aborted and results in logging error message.
- Acquiring match with teams that do not play right now returns null. 
Acquiring match with inverted home and away teams to existing match also returns null.
This actions result in logging error message.
- Finishing not existing match results in logging error message.
- An attempt to decrement score of team is aborted and results in logging error message. 
If user tries to set new decremented score for one team and valid score for opposing team in one attempt neither score is updated.

## Assumptions
- Team can play only in one match at once.
- Score cannot be decremented.
- Updating score is generally more frequent than summary current matches, 
so it is less computationally expensive to sort matches before summary
then updating elements position in list each time score changes.
- Scoreboard can be reused to track matches from other events like champions league.
In order to ensure system flexibility names of each team is not checked if there are actually names of countries.

## Conventions
- Lombok annotations are used to enhance quality of codebase, by eliminating generic parts of code.