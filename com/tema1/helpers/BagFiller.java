package com.tema1.helpers;
import com.tema1.common.Constants;
import com.tema1.goods.Goods;
import com.tema1.goods.GoodsFactory;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*Clasa cu metode ce implementeaza strategiile prin care fiecare jucator
* isi umple sacul*/
public final class BagFiller {
    private Map<Integer, Integer> legalGoods;          //Bunurile legale din mana
    private Map<Integer, Integer> illegalGoods;        //Bunurile ilegale din mana
    private int penaltyFaced;                          //Penalty-ul total asociat sacului

    public BagFiller() {
        legalGoods = new LinkedHashMap<Integer, Integer>();
        illegalGoods = new LinkedHashMap<Integer, Integer>();
        penaltyFaced = 0;
    }

    public void resetBagFiller(final List<Integer> goodsInHand) {

        legalGoods.clear();
        illegalGoods.clear();
        legalGoods = FilterGoods.filterLegal(goodsInHand);
        illegalGoods = FilterGoods.filterIllegal(goodsInHand);
        penaltyFaced = 0;
    }

   //Strategia Basic de umplere a sacului
    public void fillBaseBag(final Bag bag, final int money) {
        int id;

        //Daca are bunuri legale, le ordoneaza si alege cel mai bun tip
        if (!legalGoods.isEmpty()) {
            OrderGoods.orderFreqProfitId(legalGoods);

            int freq = getFirstFreq(legalGoods);
            id = FilterGoods.popFirstEntry(legalGoods);

            //Declara bunul ales
            bag.setDeclaredGood(id);

            //Pune in sac toate cartile de acel tip
            while (freq > 0) {
                bag.placeGood(id);
                freq--;
            }
        } else {
            //Daca are doar bunuri ilegale, il alege pe cel mai bun
            id = chooseIllegal();

            this.penaltyFaced = assesPenalty(id);

            if (money >= penaltyFaced) {
                bag.placeGood(id);

                //Declara bunul ca fiind mar
                bag.setDeclaredGood(0);
            }
        }
    }

    //Ordoneaza bunurile ilegale si intoarce id-ul celui mai bun
    public int chooseIllegal() {

        if (!illegalGoods.isEmpty()) {
            OrderGoods.orderProfitId(illegalGoods);
            return FilterGoods.popFirstEntry(illegalGoods);
        }
        return -1;
    }

    //Strategia Greedy de umplere a sacului
    //Hashmap-urile legal si ilegal sunt deja prelucrate in urma aplicarii basic
    //Metoda alege urmatorul bun ilegal si il adauga in sac
    public void fillGreedyBag(final Bag bag, final int money) {

        int id = chooseIllegal();

        penaltyFaced += assesPenalty(id);

        if (id != -1 && money >= penaltyFaced && bag.getNrGoods() <= Constants.getNrGoods()) {
            bag.placeGood(id);
        }
    }

    //Strategia Bribe de umplere a sacului
    public void fillBribeBag(final Bag bag, final int money) {
        int bribe = 0;
        int id;
        int nrIllegal = 0;

        //Nu are bunuri ilegale, joaca basic
        if (illegalGoods.isEmpty() || (money <= Constants.getMinBribe()
                && !illegalGoods.isEmpty())) {

            fillBaseBag(bag, money);

        } else {
            //Ordoneaza bunurile ilegale si declara mere orice ar adauga
            OrderGoods.orderProfitId(illegalGoods);
            bag.setDeclaredGood(0);

            id = FilterGoods.popFirstEntry(illegalGoods);
            penaltyFaced = 0;
            bribe = Constants.getMinBribe();

            //Adauga bunuri cat timp isi permite sa plateasca penalty sau sa dea mita
            //(sau cat timp nu ii se termina bunurile ilegale din mana)
            while (id != -1 && money > (penaltyFaced + assesPenalty(id))
                    && money > bribe && bag.getNrGoods() <= Constants.getNrGoods()) {
                 bag.placeGood(id);
                 nrIllegal++;

                penaltyFaced += assesPenalty(id);
                id = FilterGoods.popFirstEntry(illegalGoods);

                //Daca are mai mult de 2 bunuri ilegale creste mita
                if (nrIllegal > 2) {
                    bribe = Constants.getMaxBribe();
                }
            }

            /*Daca i s-au terminat bunurile ilegale sau nu mai poate plati pentru ele,
            incepe sa completeze cu  bunuri legale
             */
            if (money > (penaltyFaced) && bag.getNrGoods() <= Constants.getNrGoods()
                    && !legalGoods.isEmpty() && money > bribe) {

                OrderGoods.orderProfitId(legalGoods);
                id = FilterGoods.popFirstEntry(legalGoods);

                penaltyFaced += assesPenalty(id);

                while (id != -1 && money > penaltyFaced
                        && bag.getNrGoods() <= Constants.getNrGoods() && money > bribe) {
                    bag.placeGood(id);

                    id = FilterGoods.popFirstEntry(legalGoods);
                    penaltyFaced += assesPenalty(id);
                }
            }
        }

        //Mita o da simbolic - nu ii se scade din money pana cand nu o accepta seriful
        bag.setBribe(bribe);
    }

    //Intoarce penalty-ul pt bunul cu id 'id'
    public static int assesPenalty(final int id) {
        GoodsFactory factory = GoodsFactory.getInstance();
        int i = 0;

        Goods good = factory.getGoodsById(id);
        if (good != null) {
            i = good.getPenalty();
        }
        return i;
    }


    //Intoarce numarul de carti de tipul primului bun din hashmap
    public static int getFirstFreq(final Map<Integer, Integer> map) {
        Map.Entry<Integer, Integer> entry = map.entrySet().iterator().next();
        return entry.getValue();
    }


    public Map<Integer, Integer> getLegalGoods() {
        return legalGoods;
    }

    public void setLegalGoods(final Map<Integer, Integer> legalGoods) {
        this.legalGoods = legalGoods;
    }

    public Map<Integer, Integer> getIllegalGoods() {
        return illegalGoods;
    }

    public void setIllegalGoods(final Map<Integer, Integer> illegalGoods) {
        this.illegalGoods = illegalGoods;
    }

    public int getPenaltyFaced() {
        return penaltyFaced;
    }

    public void setPenaltyFaced(final int penaltyFaced) {
        this.penaltyFaced = penaltyFaced;
    }

}
