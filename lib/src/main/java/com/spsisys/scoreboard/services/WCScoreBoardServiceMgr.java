package com.spsisys.scoreboard.services;

import com.spsisys.scoreboard.entities.Game;
import com.spsisys.scoreboard.entities.Team;
import com.spsisys.scoreboard.exceptions.TeamIsPlayingException;
import com.spsisys.scoreboard.stores.WCScoreBoardStore;
import com.spsisys.scoreboard.utils.WCScoreBoardUtils;

import java.util.Date;
import java.util.List;

public class WCScoreBoardServiceMgr implements WCScoreBoardService {
    private final WCScoreBoardStore store;

    // Constructor
    public WCScoreBoardServiceMgr(WCScoreBoardStore store) {
        this.store = store;
    }

    private boolean isTeamPlaying(Team team) {
        return this.store.getGameByTeam(team) != null;
    }

    private void setGameScore(Game game, int homeTeamScore, int awayTeamScore) {
        game.setHomeTeamScore(homeTeamScore);
        game.setAwayTeamScore(awayTeamScore);
    }

    /**
     * 3. Football World Cup Score Board requirements
     */
    // 1. Start a game. When a game starts, it should capture (being initial score 0 – 0)
    @Override
    public Game startGame(Team homeTeam, Team awayTeam) throws TeamIsPlayingException {
        if (!WCScoreBoardUtils.areValidTeams(homeTeam, awayTeam)) {
            throw new IllegalArgumentException("Invalid Teams: Teams cannot be null");
        }
        if (homeTeam.getId().equals(awayTeam.getId())) {
            throw new IllegalArgumentException("Invalid Teams: Home and away team cannot be the same");
        }
        if (isTeamPlaying(homeTeam)) {
            throw new TeamIsPlayingException(homeTeam);
        }
        if (isTeamPlaying(awayTeam)) {
            throw new TeamIsPlayingException(awayTeam);
        }

        Game game = new Game(homeTeam, awayTeam, new Date());
//        game.setStartDate(new Date());
        this.store.putGame(game);
        return game;
    }

    // 2. Finish game. It will remove a match from the scoreboard
    @Override
    public void finishGame(Team homeTeam, Team awayTeam) {
        if (!WCScoreBoardUtils.areValidTeams(homeTeam, awayTeam)) {
            return;
        }
        this.store.removeGame(homeTeam, awayTeam);
    }

    // 3. Update score. Receiving the pair score; home team score and away team score updates a game score
    @Override
    public void updateScore(Team homeTeam, Team awayTeam, int homeTeamScore, int awayTeamScore) {
        if (!WCScoreBoardUtils.areValidTeams(homeTeam, awayTeam)) {
            return;
        }
        if (!WCScoreBoardUtils.isValidResult(homeTeamScore, awayTeamScore)) {
            return;
        }
        Game game = this.store.getGame(homeTeam, awayTeam);
        if (game == null) {
            return;
        }
        setGameScore(game, homeTeamScore, awayTeamScore);
        this.store.putGame(game);
    }

    // 4. Get a summary of games by total score. Those games with the same total score will be returned
    // ordered by the most recently added to our system
    @Override
    public List<Game> getSummary() {
        List<Game> games = this.store.getGames();
        games.sort((game1, game2) -> {
            if (WCScoreBoardUtils.getTotalScore(game1) > WCScoreBoardUtils.getTotalScore(game2)) {
                return -1;
            } else if (WCScoreBoardUtils.getTotalScore(game1) < WCScoreBoardUtils.getTotalScore(game2)) {
                return 1;
            }
            return game1.getStartDate().before(game2.getStartDate()) ? 1 : -1;
        });
        return games;
    }
}

