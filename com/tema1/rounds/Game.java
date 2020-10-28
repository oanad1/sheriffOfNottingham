package com.tema1.rounds;
import com.tema1.common.Constants;
import com.tema1.helpers.ProfitCalculator;
import com.tema1.players.PlayerTurns;
import java.util.List;


/*Simuleaza executia jocului utilizand obiecte de tip Round si Subround;
* Apeleaza metoda playRound() de nrRounds ori si actualizeaza numarul rundei
* in clasa de tip Subround*/

public final class Game {
    private final PlayerTurns playerTurns;
    private List<Integer> assets;
    private final int nrRounds;

    public Game(final PlayerTurns playerTurns, final List<Integer> assets, final int nrRounds) {
        this.playerTurns = playerTurns;
        this.assets = assets;

        if (nrRounds <= Constants.getMaxRounds()) {
            this.nrRounds = nrRounds;
        } else {
            this.nrRounds = Constants.getMaxRounds();
        }
    }

    public void playGame() {

        Subround subround = new Subround(assets, playerTurns);
        Round round = new Round(playerTurns, subround);

        for (int i = 1; i <= nrRounds; i++) {
            subround.setRoundNumber(i);
            round.playRound();
        }

        ProfitCalculator.calculateTotal(playerTurns);
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

    public int getNrRounds() {
        return nrRounds;
    }
}
