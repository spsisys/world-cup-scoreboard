package com.spsisys.scoreboard.utils;

import com.spsisys.scoreboard.entities.Game;
import com.spsisys.scoreboard.entities.Team;

public class WCScoreBoardUtils {
    public static boolean areValidTeams(Team homeTeam, Team awayTeam) {
        return WCScoreBoardUtils.isValidTeam(homeTeam) && WCScoreBoardUtils.isValidTeam(awayTeam);
    }

    public static boolean isValidTeam(Team team) {
        return team != null && team.getId() != null && team.getName() != null;
    }

    public static boolean isValidResult(int homeTeamScore, int awayTeamScore) {
        return homeTeamScore >= 0 && awayTeamScore >= 0;
    }

    public static int getTotalScore(Game game) {
        return game.getHomeTeamScore() + game.getAwayTeamScore();
    }

}
