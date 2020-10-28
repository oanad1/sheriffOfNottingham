package com.tema1.players;
import com.tema1.common.Constants;
import com.tema1.helpers.BagChecker;
import java.util.List;

//Strategiile jucatorului de tip Greedy
public class Greedy extends Player {

    private String type = "GREEDY";


    public Greedy(final int id, final String type) {
        super(id, type);
    }

    public final void fillBag() {
        getBag().getBagFiller().fillBaseBag(getBag(), getMoney());

        //Daca runda este para, alege un bun ilegal in plus
        if (getRoundNumber() % 2 == 0) {
            getBag().getBagFiller().fillGreedyBag(getBag(), getMoney());
        }
    }

    //Inspecteaza toti jucatorii care nu ii dau mita
    public final void pickCriminals(final PlayerTurns playerTurns, final List<Integer> assets) {

        for (Player player: playerTurns.getPlayers()) {

            if (player.getId() != this.getId() && player.getBag().getBribe() != 0) {

                this.setMoney(this.getMoney() + player.getBag().getBribe());
                player.setMoney(player.getMoney() - player.getBag().getBribe());
                player.getBag().setBribe(0);

            } else {

                if (player.getId() != this.getId()
                        && this.getMoney() >= Constants.getMinSheriffMoney()) {
                    BagChecker.inspect(this, player, assets);
                }

            }
        }
    }
}
