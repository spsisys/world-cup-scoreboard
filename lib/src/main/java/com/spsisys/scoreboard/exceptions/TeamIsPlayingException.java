package com.spsisys.scoreboard.exceptions;

import com.spsisys.scoreboard.entities.Team;

public class TeamIsPlayingException extends Exception {
    public TeamIsPlayingException(Team team) {
        super(String.format("%1$s is already playing!", team.getName()));
    }
}
