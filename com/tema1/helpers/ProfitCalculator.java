package com.tema1.helpers;
import com.tema1.common.Constants;
import com.tema1.goods.Goods;
import com.tema1.goods.GoodsFactory;
import com.tema1.goods.IllegalGoods;
import com.tema1.goods.LegalGoods;
import com.tema1.players.Player;
import com.tema1.players.PlayerTurns;
import java.util.Map;

//Calculeaza profitul obtinut de jucatori la finalul jocului

public final class ProfitCalculator {

    private ProfitCalculator() {
    }

    public static void calculateTotal(final PlayerTurns playerTurns) {
        GoodsFactory goodsFactory = GoodsFactory.getInstance();

        //Se aplica bonusurile
        for (Player player: playerTurns.getPlayers()) {
            applyIllegalBonus(player);
        }

        applyRoyaltyBonus(playerTurns);

        //Se calculeaza profitul din bunurile de pe taraba
        for (Player player: playerTurns.getPlayers()) {
            player.setMoney(player.getMoney() + player.getBonus());

            for (int id : player.getGoodsOnTable().keySet()) {
                int freq = player.getGoodsOnTable().get(id);
                player.setMoney(player.getMoney()
                        + freq * goodsFactory.getGoodsById(id).getProfit());
            }
        }
    }

    //Transforma cartile ilegale de pe taraba in bonus de carti legale
    public static void applyIllegalBonus(final Player player) {
        GoodsFactory goodsFactory = GoodsFactory.getInstance();

        //Pentru fiecare carte ilegala id=20..24, se verifica daca exista pe taraba jucatorilor
        for (int id = Constants.getFirstIlegalId(); id <= Constants.getLastIlegalId(); id++) {

            if (player.getGoodsOnTable().containsKey(id)) {

                int nrIllegal = player.getGoodsOnTable().get(id);
                player.setMoney(player.getMoney()
                        + goodsFactory.getGoodsById(id).getProfit() * nrIllegal);

                IllegalGoods illegalGood = (IllegalGoods) goodsFactory.getGoodsById(id);
                Map<Goods, Integer> bonusMap = illegalGood.getIllegalBonus();

                for (Goods key : bonusMap.keySet()) {
                    int bonusGood = key.getId();                 //Id-ul ce trebuie adaugat
                    int freq = bonusMap.get(key) * nrIllegal;    //De cate ori se adauga

                    //Pune pe taraba bunurile legale corespunzatoare bonusului ilegal
                    if (player.getGoodsOnTable().containsKey(bonusGood)) {
                        freq += player.getGoodsOnTable().get(bonusGood);
                    }

                    player.getGoodsOnTable().put(bonusGood, freq);
                }
                //Scoate bunul ilegal de pe taraba
                player.getGoodsOnTable().remove(id);
            }
        }
    }

    public static void applyRoyaltyBonus(final PlayerTurns playerTurns) {
        GoodsFactory goodsFactory = GoodsFactory.getInstance();

        //Pentru fiecare carte legala id=0..9, se verifica daca exista pe taraba jucatorilor
        for (int id = Constants.getFirstLegalId(); id <= Constants.getLastLegalId(); id++) {

            int king = 0, queen = 0;
            Player kingPlayer = null, queenPlayer = null;

            // Se aleg cei 2 jucatori care au frecventele cele mai mari pt bunul cu id 'id'
            for (Player player: playerTurns.getPlayers()) {
                if (player.getGoodsOnTable().containsKey(id)
                        && player.getGoodsOnTable().get(id) > king) {
                    queen = king;
                    queenPlayer = kingPlayer;
                    king = player.getGoodsOnTable().get(id);
                    kingPlayer = player;

                } else if (player.getGoodsOnTable().containsKey(id)
                        && player.getGoodsOnTable().get(id) > queen) {
                    queen = player.getGoodsOnTable().get(id);
                    queenPlayer = player;
                }
            }

            //Se aplica bonusul de king si queen pentru jucatorii alesi anterior
            LegalGoods legalGoods = (LegalGoods) goodsFactory.getGoodsById(id);
            if (kingPlayer != null) {
                kingPlayer.setBonus(kingPlayer.getBonus() + legalGoods.getKingBonus());
            }

            if (queenPlayer != null) {
                queenPlayer.setBonus(queenPlayer.getBonus() + legalGoods.getQueenBonus());
            }

        }
    }
}
