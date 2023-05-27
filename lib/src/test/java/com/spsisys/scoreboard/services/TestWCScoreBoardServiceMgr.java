package com.spsisys.scoreboard.services;

import com.spsisys.scoreboard.entities.Game;
import com.spsisys.scoreboard.entities.Team;
import com.spsisys.scoreboard.exceptions.TeamIsPlayingException;
import com.spsisys.scoreboard.stores.WCScoreBoardStoreMgr;
import com.spsisys.scoreboard.testdata.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestWCScoreBoardServiceMgr {
    WCScoreBoardService serviceUnderTest;

    private void startGame(Team homeTeam, Team awayTeam) {
        try {
            serviceUnderTest.startGame(homeTeam, awayTeam);
        } catch (TeamIsPlayingException e) {
        }
    }

    private void startDefaultGameWithScore(Team homeTeam, Team awayTeam, int homeTeamScore, int awayTeamScore) {
        try {
            serviceUnderTest.startGame(homeTeam, awayTeam);
            serviceUnderTest.updateScore(homeTeam, awayTeam, homeTeamScore, awayTeamScore);
        } catch (TeamIsPlayingException e) {
        }
    }

    @BeforeEach
    void initService() {
        this.serviceUnderTest = new WCScoreBoardServiceMgr(new WCScoreBoardStoreMgr());
        this.startGame(TestData.MEXICO, TestData.CANADA);
    }

    @Test
    void whenAGameWasCreatedSuccessfullyShouldBeCorrectlyStarted() {
        assertEquals(serviceUnderTest.getSummary().size(), 1);
        Game game = serviceUnderTest.getSummary().get(0);
        assertEquals(game.getHomeTeamScore(), 0);
        assertEquals(game.getAwayTeamScore(), 0);
    }

    @Test
    void whenAGameStartedIncorrectlyShouldThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> serviceUnderTest.startGame(null, TestData.CANADA));
        assertThrows(IllegalArgumentException.class, () -> serviceUnderTest.startGame(TestData.MEXICO, null));
        assertThrows(IllegalArgumentException.class, () -> serviceUnderTest.startGame(new Team(null, null), new Team(null, null)));
    }

}
