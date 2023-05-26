package com.spsisys.scoreboard.store;

import com.spsisys.scoreboard.entities.Game;
import com.spsisys.scoreboard.entities.Team;

import java.util.List;

public interface WCScoreBoardStore {
    void putGame(Game game);

    void removeGame(Team homeTeam, Team awayTeam);

    void removeGame(Game game);

    Game getGame(Team homeTeam, Team awayTeam);

    List<Game> getGames();

    Game getGameByTeam(Team team);
}
