/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.spsisys.scoreboard;

import com.spsisys.scoreboard.entities.Game;
import com.spsisys.scoreboard.exceptions.TeamIsPlayingException;
import com.spsisys.scoreboard.testdata.TestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    Library libraryUnderTest = new Library();

    @Test
    void startGameMethodShouldCreateAGame() {
//        assertTrue(classUnderTest.someLibraryMethod(), "someLibraryMethod should return 'true'");
        try {
            Game game = libraryUnderTest.startGame(TestData.URUGUAY, TestData.ITALY);
            assertNotNull(game);
            assertEquals(game.getHomeTeamScore(), 0);
            assertEquals(game.getAwayTeamScore(), 0);
            assertTrue(libraryUnderTest.getSummary().stream()
                    .anyMatch((Game assertGame) -> assertGame.getHomeTeam().getId().equals(TestData.URUGUAY.getId())
                            && assertGame.getAwayTeam().getId().equals(TestData.ITALY.getId())));
        } catch (TeamIsPlayingException e) {
        }
    }

    @Test
    void finishGameMethodShouldRemoveTheGame() {
        try {
            Game game = libraryUnderTest.startGame(TestData.URUGUAY, TestData.ITALY);
            assertTrue(libraryUnderTest.getSummary().stream()
                    .anyMatch((Game assertGame) -> assertGame.getHomeTeam().getId().equals(TestData.URUGUAY.getId())
                            && assertGame.getAwayTeam().getId().equals(TestData.ITALY.getId())));
            libraryUnderTest.finishGame(game);
            assertFalse(libraryUnderTest.getSummary().stream()
                    .anyMatch((Game assertGame) -> assertGame.getHomeTeam().getId().equals(TestData.URUGUAY.getId())
                            && assertGame.getAwayTeam().getId().equals(TestData.ITALY.getId())));
        } catch (TeamIsPlayingException e) {
        }
    }

    @Test
    void updateScoreMethodShouldUpdateTheGame() {
        try {
            Game game = libraryUnderTest.startGame(TestData.URUGUAY, TestData.ITALY);
            libraryUnderTest.updateScore(game, 6, 6);
            assertTrue(libraryUnderTest.getSummary().stream()
                    .anyMatch((Game assertGame) -> assertGame.getHomeTeam().getId().equals(TestData.URUGUAY.getId())
                            && assertGame.getAwayTeam().getId().equals(TestData.ITALY.getId())
                            && assertGame.getHomeTeamScore() == 6 && assertGame.getAwayTeamScore() == 6));
        } catch (TeamIsPlayingException e) {
        }
    }
}
