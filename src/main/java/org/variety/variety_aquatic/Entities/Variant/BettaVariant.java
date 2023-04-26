package org.variety.variety_aquatic.Entities.Variant;

import java.util.Arrays;
import java.util.Comparator;

public enum BettaVariant {
    DEFAULT(0),
    PINK_BETTA(1),
    BLUE_BETTA(2),
    GREEN_BETTA(3),
    RED_BLUE_BETTA(4),
    BLUE_YELLOW_BETTA(5),
    WHITE_BETTA(6),
    BLACK_BETTA(7);





    private static final BettaVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(BettaVariant::getId)).toArray(BettaVariant[]::new);
    private final int id;

    BettaVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static BettaVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}


