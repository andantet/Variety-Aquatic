package org.variety.varietyaquatic.entity.Varient;

import java.util.Arrays;
import java.util.Comparator;

public enum SeahorseVariant {
        DEFAULT(0),
        ORANGE_SEAHORSE(1),
        YELLOW_SEAHORSE(2),
        GREEN_SEAHORSE(3),
        PURPLE_SEAHORSE(4),
        BLUE_SEAHORSE(5);




    private static final SeahorseVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(SeahorseVariant::getId)).toArray(SeahorseVariant[]::new);
        private final int id;

        SeahorseVariant(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public static SeahorseVariant byId(int id) {
            return BY_ID[id % BY_ID.length];
        }
    }

