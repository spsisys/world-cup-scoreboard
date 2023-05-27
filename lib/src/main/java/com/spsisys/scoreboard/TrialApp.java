package com.spsisys.scoreboard;

import com.spsisys.scoreboard.entities.Game;
import com.spsisys.scoreboard.exceptions.TeamIsPlayingException;
import com.spsisys.scoreboard.stores.TrialAppData;

public class TrialApp {
    public static void main(String[] args) {
        Library library = new Library();

        Game game1;
        Game game2;
        Game game3;
        Game game4;
        Game game5;

        try {
            game1 = library.startGame(TrialAppData.MEXICO, TrialAppData.CANADA);
            game2 = library.startGame(TrialAppData.SPAIN, TrialAppData.BRAZIL);
            game3 = library.startGame(TrialAppData.GERMANY, TrialAppData.FRANCE);
            game4 = library.startGame(TrialAppData.URUGUAY, TrialAppData.ITALY);
            game5 = library.startGame(TrialAppData.ARGENTINA, TrialAppData.AUSTRALIA);

            library.updateScore(game1, 0, 5);
            library.updateScore(game2, 10, 2);
            library.updateScore(game3, 2, 2);
            library.updateScore(game4, 6, 6);
            library.updateScore(game5, 3, 1);
        } catch (TeamIsPlayingException e) {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("******** SUMMARY OF CURRENT GAMES BY TOTAL SCORE ********\n" + library.getSummaryAsString());
        }
    }
}
