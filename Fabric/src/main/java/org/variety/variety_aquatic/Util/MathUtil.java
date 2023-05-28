package org.variety.variety_aquatic.Util;

import net.minecraft.util.math.MathHelper;

public class MathUtil {

        public static float lerp(float start, float end, float t) {
            return MathHelper.lerp(t, start, end);
        }

        public static float clamp(float min, float max, float value) {
            return MathHelper.clamp(value, min, max);
        }
    }