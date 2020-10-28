package com.tema1.helpers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Simuleaza "sacul" fiecarui jucator si strategiile prin care
   acesta se mofifica
 */
public final class Bag {
    private final Map<Integer, Integer> goodsInBag;     //Bunurile din sac
    private final BagFiller bagFiller;                  //Utilitar de umplere a sacului
    private  int bribe;                                 //Mita oferita
    private  int nrGoods;                               //Numarul de bunuri din sac
    private  int declaredGood;                          //Bunul declarat


    public Bag() {
        goodsInBag = new HashMap<Integer, Integer>();
        declaredGood = -1;
        bagFiller = new BagFiller();
        nrGoods = 0;
        bribe = 0;
    }

    //Reinitializeaza sacul
    public void createNewBag(final List<Integer> goodsInHand) {
        goodsInBag.clear();
        bribe = 0;
        nrGoods = 0;
        declaredGood = -1;
        bagFiller.resetBagFiller(goodsInHand);
    }

    //Adauga o carte in sac
    public void placeGood(final int id) {
        int freq;

        if (goodsInBag.containsKey(id)) {
            freq = goodsInBag.get(id);

        } else {
            freq = 0;
        }

            goodsInBag.put(id, freq + 1);
            nrGoods++;
    }

    public Map<Integer, Integer> getGoodsInBag() {
        return goodsInBag;
    }

    public BagFiller getBagFiller() {
        return bagFiller;
    }

    public int getBribe() {
        return bribe;
    }

    public void setBribe(final int bribe) {
        this.bribe = bribe;
    }

    public int getNrGoods() {
        return nrGoods;
    }

    public void setNrGoods(final int nrGoods) {
        this.nrGoods = nrGoods;
    }

    public int getDeclaredGood() {
        return declaredGood;
    }

    public void setDeclaredGood(final int declaredGood) {
        this.declaredGood = declaredGood;
    }
}
