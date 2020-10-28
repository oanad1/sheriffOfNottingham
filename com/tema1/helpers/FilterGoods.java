package com.tema1.helpers;
import com.tema1.goods.GoodsFactory;
import com.tema1.goods.GoodsType;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*Metode de prelucrare a  bunurilor din "mana" unui comerciant */
public final class FilterGoods {

    private FilterGoods() {

    }
    //Creeaza un hashmap cu toate bunurile
    public static LinkedHashMap<Integer, Integer> createHashMap(final List<Integer> goodsInHand) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();

        for (int i = 0; i < goodsInHand.size(); i++) {
            int key = goodsInHand.get(i);

            if (map.containsKey(key)) {
                int freq = map.get(key);    //key e ID-ul bunului , value e FRECVENTA
                freq++;
                map.put(key, freq);
            } else {
                map.put(key, 1);
            }
        }
        return map;
    }


    //Intoarce un hashmap cu bunurile legale
    public static Map<Integer, Integer> filterLegal(final List<Integer> goodsInHand) {

        Map<Integer, Integer> legalGoods = createHashMap(goodsInHand);
        GoodsFactory goodsFactory = GoodsFactory.getInstance();

        legalGoods.entrySet().removeIf(entries -> goodsFactory.getGoodsById(
                entries.getKey()).getType() == GoodsType.Illegal);
        return legalGoods;
    }


    //Intoarce un hashmap cu bunurile ilegale
    public static Map<Integer, Integer> filterIllegal(final List<Integer> goodsInHand) {

        Map<Integer, Integer> illegalGoods = createHashMap(goodsInHand);
        GoodsFactory goodsFactory = GoodsFactory.getInstance();

        illegalGoods.entrySet().removeIf(entries -> goodsFactory.getGoodsById(
                entries.getKey()).getType() == GoodsType.Legal);
        return illegalGoods;
    }


    //Intoarce primul bun si il sterge din hashmap/ ii sterge o aparitie din hashmap
    public static int popFirstEntry(final Map<Integer, Integer> map) {
        Map.Entry<Integer, Integer> entry = null;

        if (!map.isEmpty()) {
            entry = map.entrySet().iterator().next();
        }

        if (entry == null) {
            return -1;
        }

        int firstKey = entry.getKey();
        int firstValue = entry.getValue();

        if (firstValue > 1) {
            map.put(firstKey, firstValue - 1);
        } else {
            map.remove(firstKey);
        }

        return firstKey;
    }
}
