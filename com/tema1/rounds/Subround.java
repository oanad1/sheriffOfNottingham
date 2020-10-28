package com.tema1.rounds;
import com.tema1.players.Player;
import com.tema1.players.PlayerTurns;
import java.util.List;

/*Simuleaza executia unei subrunde, in urmatoarele etape:
* 1.pregatirea comerciantilor pentru inspectie
* 2.controlarea comerciantilor de catre serif
* 3.preluarea bunurilor ramase in urma inspectiei */

public final class Subround {
    private final PlayerTurns playerTurns;
    private List<Integer> assets;
    private int roundNumber;
    private Player sheriff;

    public Subround(final List<Integer> assets, final PlayerTurns playerTurns) {
        this.playerTurns = playerTurns;
        this.assets = assets;
    }


    /*Etapa 1: se imparte mana de carti si fiecare comerciant
               isi aplica strategia de umplere a sacului
     */
    public void goToInspection() {
        for (Player item: playerTurns.getPlayers()) {
            if (item != sheriff) {
                item.setPlayer(assets);
                item.setRoundNumber(roundNumber);
                item.fillBag();
            }
        }
    }


    /*Etapa 2: seriful isi aplica strategia de control a
               comerciantilor
    */
    public void sheriffControll() {
        if (sheriff != null) {
            sheriff.pickCriminals(playerTurns, assets);
        }
    }


    /*Etapa 3: fiecare comerciant isi verifica sacul si isi
               adauga bunurile ramase pe taraba
    */
    public void reachNottingham() {
        for (Player item: playerTurns.getPlayers()) {
            if (item != sheriff) {
                item.earnGoods();
            }
        }
    }


    public void playSubround() {

        goToInspection();
        sheriffControll();
        reachNottingham();

    }

    public PlayerTurns getPlayerTurns() {
        return playerTurns;
    }

    public List<Integer> getAssets() {
        return assets;
    }

    public void setAssets(final List<Integer> assets) {
        this.assets = assets;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(final int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public Player getSheriff() {
        return sheriff;
    }

    public void setSheriff(final Player sheriff) {
        this.sheriff = sheriff;
    }
}
