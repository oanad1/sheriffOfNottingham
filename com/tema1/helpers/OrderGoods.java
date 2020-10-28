package com.tema1.helpers;
import com.tema1.goods.GoodsFactory;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Comparator;

/*Metode folosite pentru a ordona bunurile legale/ilegale in functie de
criteriile fiecarei strategii
 */
public final class OrderGoods {

    private OrderGoods() {
    }

    public static void orderFreqProfitId(final Map<Integer, Integer> map) {

        // Creeaza o lista din entry-urile hashmap-ului
        List<Map.Entry<Integer, Integer>> list =
                new LinkedList<Map.Entry<Integer, Integer>>(map.entrySet());
        GoodsFactory goodsFactory = GoodsFactory.getInstance();

        //Ordoneaza lista dupa 3 criterii: 1.Frecventa    2.Profit    3.ID
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {

            public int compare(final Map.Entry<Integer, Integer> entry1,
                               final Map.Entry<Integer, Integer> entry2) {

                Integer freq1 = entry1.getValue();
                Integer freq2 = entry2.getValue();
                int comp1 = freq2.compareTo(freq1);

                if (comp1 != 0) {
                    return comp1;
                }

                Integer profit1 = goodsFactory.getGoodsById(entry1.getKey()).getProfit();
                Integer profit2 = goodsFactory.getGoodsById(entry2.getKey()).getProfit();
                int comp2 = profit2.compareTo(profit1);

                if (comp2 != 0) {
                    return comp2;
                }

                Integer id1 = entry1.getKey();
                Integer id2 = entry2.getKey();
                return id2.compareTo(id1);
            }
        });

        //Transforma lista inapoi in LinkedHashMap
        map.clear();
        for (Map.Entry<Integer, Integer> entry : list) {
            map.put(entry.getKey(), entry.getValue());
        }
    }

    public static void orderProfitId(final Map<Integer, Integer> map) {

        // Creeaza o lista din entry-urile hashmap-ului
        List<Map.Entry<Integer, Integer>> list =
                new LinkedList<Map.Entry<Integer, Integer>>(map.entrySet());
        GoodsFactory goodsFactory = GoodsFactory.getInstance();

        //Ordoneaza lista dupa 2 criterii: 1.Profit    2.ID
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {

            public int compare(final Map.Entry<Integer, Integer> entry1,
                               final Map.Entry<Integer, Integer> entry2) {

                Integer profit1 = goodsFactory.getGoodsById(entry1.getKey()).getProfit();
                Integer profit2 = goodsFactory.getGoodsById(entry2.getKey()).getProfit();
                int comp = profit2.compareTo(profit1);

                if (comp != 0) {
                    return comp;
                }

                Integer id1 = entry1.getKey();
                Integer id2 = entry2.getKey();
                return id2.compareTo(id1);
            }
        });

        //Transforma lista inapoi in LinkedHashMap
        map.clear();
        for (Map.Entry<Integer, Integer> entry : list) {
            map.put(entry.getKey(), entry.getValue());
        }
    }
}


