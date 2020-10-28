package com.tema1.rounds;

import com.tema1.players.Player;
import com.tema1.players.PlayerTurns;
import java.util.Collections;
import java.util.Comparator;

//Afiseaza clasamentul final al jucatorilor
public final class Rank {

    private Rank() {

    }

    public static void printRank(final PlayerTurns playerTurns) {
        sortByProfit(playerTurns);

        for (Player player: playerTurns.getPlayers()) {
            System.out.println(player.getId() + " " + player.getType() + " " + player.getMoney());
        }
    }

    public static void sortByProfit(final PlayerTurns playerTurns) {

        Collections.sort(playerTurns.getPlayers(), new Comparator<Player>() {
            @Override
            public int compare(final Player p1, final Player p2) {
                return p2.getMoney().compareTo(p1.getMoney());
            }
        });
    }

}
