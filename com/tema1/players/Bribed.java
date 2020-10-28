package com.tema1.players;
import com.tema1.common.Constants;
import com.tema1.helpers.BagChecker;
import java.util.List;

//Strategiile jucatorului de tip bribed
public class Bribed extends Player {

    private String type = "BRIBED";


    public Bribed(final int id, final String type) {
        super(id, type);
    }

    public final void fillBag() {
        getBag().getBagFiller().fillBribeBag(getBag(), getMoney());
    }

    public final void pickCriminals(final PlayerTurns playerTurns, final List<Integer> assets) {
        Player left, right;

        //Alege jucatorul din stanga (exceptie: this este primul jucator din lista)
        if (playerTurns.getPlayers().indexOf(this) == 0) {
            left = playerTurns.getPlayers().get(playerTurns.getPlayers().size() - 1);
        } else {
            left = playerTurns.getPlayers().get(playerTurns.getPlayers().indexOf(this) - 1);
        }

        //Alege jucatorul din dreapta (exceptie: this este ultimul jucator din lista)
        if (playerTurns.getPlayers().indexOf(this) == (playerTurns.getPlayers().size() - 1)) {
            right = playerTurns.getPlayers().get(0);
        } else {
            right = playerTurns.getPlayers().get(playerTurns.getPlayers().indexOf(this) + 1);
        }


        //Inspecteaza cei doi jucatori vecini daca are suficienti bani
        if (this.getMoney() >= Constants.getMinSheriffMoney()) {
            BagChecker.inspect(this, left, assets);
            left.getBag().setBribe(0);
        }

        if (this.getMoney() >= Constants.getMinSheriffMoney() && left.getId() != right.getId()) {
            BagChecker.inspect(this, right, assets);
            right.getBag().setBribe(0);
        }

        //Accepta mita celorlati jucatori
        for (Player player: playerTurns.getPlayers()) {

            if (player != right && player != left && player != this) {

                if (player.getBag().getBribe() != 0) {
                    this.setMoney(this.getMoney() + player.getBag().getBribe());
                    player.setMoney(player.getMoney() - player.getBag().getBribe());
                    player.getBag().setBribe(0);
                }

            }
        }
    }
}
