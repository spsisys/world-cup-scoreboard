package com.spsisys.scoreboard.services;

import com.spsisys.scoreboard.entities.Game;
import com.spsisys.scoreboard.entities.Team;
import com.spsisys.scoreboard.exceptions.TeamIsPlayingException;

import java.util.List;

public interface WCScoreBoardService {
    // 1. Start a game. When a game starts, it should capture (being initial score 0 – 0)
    Game startGame(Team homeTeam, Team awayTeam) throws TeamIsPlayingException;

    // 2. Finish game. It will remove a match from the scoreboard
    void finishGame(Team homeTeam, Team awayTeam);

    // 3. Update score. Receiving the pair score; home team score and away team score updates a game score
    void updateScore(Team homeTeam, Team awayTeam, int homeTeamScore, int awayTeamScore);

    // Get a summary of games by total score. Those games with the same total score will be returned
    // ordered by the most recently added to our system
    List<Game> getSummary();
}
