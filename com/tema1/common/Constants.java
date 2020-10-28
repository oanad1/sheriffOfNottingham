package com.tema1.common;

public final class Constants {
    static final int INITIAL_MONEY = 80;
    static final int FIRST_ILEGAL_ID = 20;
    static final int LAST_ILEGAL_ID = 24;
    static final int FIRST_LEGAL_ID = 0;
    static final int LAST_LEGAL_ID = 9;
    static final int NR_GOODS = 7;
    static final int MIN_BRIBE = 5;
    static final int MAX_BRIBE = 10;
    static final int NR_CARDS = 10;
    static final int MIN_SHERIFF_MONEY = 16;
    static final int MAX_ROUNDS = 5;

    private Constants() {

    }

    public static int getMaxRounds() {
        return MAX_ROUNDS;
    }

    public static int getMinSheriffMoney() {
        return MIN_SHERIFF_MONEY;
    }

    public static int getNrCards() {
        return NR_CARDS;
    }

    public static int getNrGoods() {
        return NR_GOODS;
    }

    public static int getMinBribe() {
        return MIN_BRIBE;
    }

    public static int getMaxBribe() {
        return MAX_BRIBE;
    }

    public static int getInitialMoney() {
        return INITIAL_MONEY;
    }

    public static int getFirstIlegalId() {
        return FIRST_ILEGAL_ID;
    }

    public static int getLastIlegalId() {
        return LAST_ILEGAL_ID;
    }

    public static int getFirstLegalId() {
        return FIRST_LEGAL_ID;
    }

    public static int getLastLegalId() {
        return LAST_LEGAL_ID;
    }
}
