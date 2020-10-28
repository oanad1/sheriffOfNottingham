package com.tema1.rounds;
import com.tema1.players.PlayerTurns;


/*Simuleaza executia unei runde, utilizand un obiect de tip Subround;
* Apeleaza metoda playSubround() de nrPlayers ori, actualizand campul
* Subround.sheriff la fiecare apel*/

public final class Round {

    private final PlayerTurns playerTurns;
    private final Subround subround;

    public Round(final PlayerTurns playerTurns, final Subround subround) {

        this.playerTurns = playerTurns;
        this.subround = subround;
    }

    public void playRound() {

        for (int i = 0; i < playerTurns.getNrPlayers(); i++) {
            subround.setSheriff(playerTurns.getPlayers().get(i));
            subround.playSubround();
        }
    }

    public PlayerTurns getPlayerTurns() {
        return playerTurns;
    }

    public Subround getSubround() {
        return subround;
    }
}
