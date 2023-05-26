package com.spsisys.scoreboard.store;

import com.spsisys.scoreboard.entities.Game;
import com.spsisys.scoreboard.entities.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WCScoreBoardStore {
    private final Map<String, Game> games = new HashMap<>();

    private String generateTeamsKey(Team homeTeam, Team awayTeam) {
        return homeTeam.getTeamKey() + "_" + awayTeam.getTeamKey();
    }

    private String generateGameKey(Game game) {
        return this.generateTeamsKey(game.getHomeTeam(), game.getAwayTeam());
    }

    public void putGame(Game game) {
        this.games.put(generateGameKey(game), game);
    }

    public void removeGame(Team homeTeam, Team awayTeam) {
        this.games.remove(this.generateTeamsKey(homeTeam, awayTeam));
    }

    public void removeGame(Game game) {
        this.games.remove(this.generateTeamsKey(game.getHomeTeam(), game.getAwayTeam()));
    }

    public Game getGame(Team homeTeam, Team awayTeam) {
        return this.games.get(this.generateTeamsKey(homeTeam, awayTeam));
    }

    public List<Game> getGames() {
        return new ArrayList<Game>(this.games.values());
    }

    public Game getGameByTeam(Team team) {
        for (Game game : this.games.values()) {
            if (game.getHomeTeam().getId().equals(team.getId()) || game.getAwayTeam().getId().equals(team.getId())) {
                return game;
            }
        }
        return null;
    }
}
