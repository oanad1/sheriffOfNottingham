package com.tema1.main;


import com.tema1.players.PlayerTurns;
import com.tema1.rounds.Game;
import com.tema1.rounds.Rank;;

public final class Main {
    private Main() {
        // just to trick checkstyle
    }

    public static void main(final String[] args) {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
        GameInput gameInput = gameInputLoader.load();

        final PlayerTurns playerTurns = new PlayerTurns(gameInput.getPlayerNames().size(),
                gameInput.getPlayerNames());

        Game game = new Game(playerTurns, gameInput.getAssetIds(), gameInput.getRounds());
        game.playGame();

        Rank.printRank(playerTurns);
    }
}
