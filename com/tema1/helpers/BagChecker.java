package com.tema1.helpers;
import java.util.ArrayList;
import java.util.List;
import com.tema1.goods.Goods;
import com.tema1.goods.GoodsFactory;
import com.tema1.players.Player;

/*Primeste o referinta la comerciant si una la serif;
Inspecteaza sacul comerciantului si aplica penalty-ul corespunzator
 */
public final class BagChecker {

    private BagChecker() {

    }

    public static void inspect(final Player sheriff, final Player merchant,
                               final List<Integer> assets) {

        //Se calculeaza penalty-ul pentru bunurile ilegale din sacul comerciantului
        int illegalPenalty = enforcePenalty(merchant.getBag(), assets);

            //Comerciantul ii plateste penalty serifului
            merchant.setMoney(merchant.getMoney() - illegalPenalty);
            sheriff.setMoney(sheriff.getMoney() + illegalPenalty);

            //Daca nu are bunuri ilegale in sac, se calculeaza compensatia primita de comerciant
            if (illegalPenalty == 0) {
                int legalRefund = getRefund(merchant.getBag());

                //Seriful ii plateste penalty comerciantului
                merchant.setMoney(merchant.getMoney() + legalRefund);
                sheriff.setMoney(sheriff.getMoney() - legalRefund);
            }
    }

    /* Verifica daca fiecare bun din sac e ilegal; Daca este, ii calculeaza penalty si
    il scoate din sac, adaugand-ul la finalul vectorului assets
     */
    public static int enforcePenalty(final Bag bag, final List<Integer> assets) {
        int penalty = 0;
        int freq;
        List<Integer> idToRemove = new ArrayList<>();
        GoodsFactory goodsFactory = GoodsFactory.getInstance();

        for (int id : bag.getGoodsInBag().keySet()) {

            if (id != bag.getDeclaredGood()
                    || goodsFactory.getGoodsById(id).getType().equals("illegal")) {
                freq = bag.getGoodsInBag().get(id);
                Goods goods = goodsFactory.getGoodsById(id);

                if (goods != null) {
                    penalty += goods.getPenalty() * freq;
                }

                while (freq > 0) {
                    assets.add(id);
                    freq--;
                }
                idToRemove.add(id);
            }
        }

        for (int id: idToRemove) {
            bag.getGoodsInBag().remove(id);
        }

        return penalty;
    }

    //Intoarce un penalty total pentru bunurile declarate din sac
    public static int getRefund(final Bag bag) {
        int refund = 0;
        int freq;

        GoodsFactory goodsFactory = GoodsFactory.getInstance();

        for (int id : bag.getGoodsInBag().keySet()) {

            if (id == bag.getDeclaredGood()) {
                freq = bag.getGoodsInBag().get(id);
                refund += goodsFactory.getGoodsById(id).getPenalty() * freq;
            }
        }
        return refund;
    }
}
