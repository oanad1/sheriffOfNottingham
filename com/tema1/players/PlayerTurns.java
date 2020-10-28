package com.tema1.players;

import java.util.LinkedList;
import java.util.List;


/* O lista cu obiecte de tip abstract Player,
instantiate ca fiind Greedy, Bribed, sau Basic */

public final class PlayerTurns {

    private final int nrPlayers;
    private final List<Player> players;

    public PlayerTurns(final int nrPlayers, final List<String> playerNames) {

         this.nrPlayers = nrPlayers;
         players = new LinkedList<Player>();

        for (int i = 0; i < nrPlayers; i++) {

            if (playerNames.get(i).equals("basic")) {
                players.add(new Base(i, "BASIC"));
            }

            if (playerNames.get(i).equals("bribed")) {
                players.add(new Bribed(i, "BRIBED"));
            }

            if (playerNames.get(i).equals("greedy")) {
                players.add(new Greedy(i, "GREEDY"));
            }
        }
    }

    public int getNrPlayers() {
        return nrPlayers;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
