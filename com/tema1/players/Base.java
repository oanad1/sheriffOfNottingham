package com.tema1.players;
import com.tema1.common.Constants;
import com.tema1.helpers.BagChecker;
import java.util.List;

//Strategiile jucatorului de tip Basic
public class Base extends Player {

    private static String type = "BASIC";


    public Base(final int id, final String type) {
        super(id, type);
    }


    public final void fillBag() {
        getBag().getBagFiller().fillBaseBag(getBag(), getMoney());
    }


   public final void pickCriminals(final PlayerTurns playerTurns, final List<Integer> assets) {

        //Inspecteaza toti jucatorii daca are mai mult de 16 monede
        for (Player player: playerTurns.getPlayers()) {
            if (player.getId() != this.getId()
                    && this.getMoney() >= Constants.getMinSheriffMoney()) {
                BagChecker.inspect(this, player, assets);
            }
       }
   }
}
