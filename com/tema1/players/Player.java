package com.tema1.players;
import com.tema1.common.Constants;
import com.tema1.helpers.Bag;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Clasa abstracta care implementeaza metodele comune celor 3 tipuri de jucatori

public abstract class Player {

   //Se folosesc in tot jocul
   private Integer money;                           //Suma de bani curenta a jucatorului
   private int bonus;                               //Bonusul obtinut la finalul jocului
   private final String type;                       //Tipul jucatorului
   private Map<Integer, Integer> goodsOnTable;      //Bunurile de pe taraba
   private final int id;                            //Id-ul jucatorului

   //Se reinitializeaza la fiecare subrunda
   private Bag bag;                                 //"Sacul" jucatorului
   private List<Integer> goodsInHand;               //Mana de carti curenta
   private static int roundNumber;                  //Numarul rundei



    public Player(final int id, final String type) {
       this.id = id;
       this.money = Constants.getInitialMoney();
       this.bonus = 0;
       this.type = type;
       this.bag = new Bag();
       goodsInHand = new ArrayList<Integer>();
       goodsOnTable = new HashMap<Integer, Integer>();
    }


   public final void setPlayer(final List<Integer> assets) {

       //Isi arde cartile vechi
       goodsInHand.clear();

       //Primeste 10 carti noi
      for (int i = 0; i < Constants.getNrCards(); i++) {
         goodsInHand.add(assets.get(0));
         assets.remove(0);
      }
       //Isi reinitializeaza sacul
       bag.createNewBag(goodsInHand);
   }

   //Implementeaza strategia de umplere a sacului
   public abstract void fillBag();

    //Implementeaza strategia de "serif"
   public abstract void pickCriminals(PlayerTurns playerTurns, List<Integer> assets);

   //Adauga bunurile din sac pe taraba
   public final void earnGoods() {

      for (int bagGood : bag.getGoodsInBag().keySet()) {
         int bagGoodFreq = bag.getGoodsInBag().get(bagGood);

         if (goodsOnTable.containsKey(bagGood)) {

            int tableGoodFreq = goodsOnTable.get(bagGood);
            tableGoodFreq += bagGoodFreq;
            goodsOnTable.put(bagGood, tableGoodFreq);

         } else {
            goodsOnTable.put(bagGood, bagGoodFreq);
         }
      }
   }

   public final Integer getMoney() {
      return money;
   }

   public final void setMoney(final Integer money) {
      this.money = money;
   }

   public final int getBonus() {
      return bonus;
   }

   public final void setBonus(final int bonus) {
      this.bonus = bonus;
   }

   public final String getType() {
      return type;
   }

   public final Map<Integer, Integer> getGoodsOnTable() {
      return goodsOnTable;
   }

   public final void setGoodsOnTable(final Map<Integer, Integer> goodsOnTable) {
      this.goodsOnTable = goodsOnTable;
   }

   public final int getId() {
      return id;
   }

   public final Bag getBag() {
      return bag;
   }

   public final void setBag(final Bag bag) {
      this.bag = bag;
   }

   public final List<Integer> getGoodsInHand() {
      return goodsInHand;
   }

   public final void setGoodsInHand(final List<Integer> goodsInHand) {
      this.goodsInHand = goodsInHand;
   }

   public final int getRoundNumber() {
      return roundNumber;
   }

   public final void setRoundNumber(final int roundNumber) {
      this.roundNumber = roundNumber;
   }
}
