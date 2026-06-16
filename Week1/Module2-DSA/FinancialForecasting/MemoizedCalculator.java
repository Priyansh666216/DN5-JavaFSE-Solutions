package FinancialForecasting;

import java.util.HashMap;
import java.util.Map;

public class MemoizedCalculator {

    private static Map<Integer, Double> cache = new HashMap<>();

    public static double futureValue(double principal,
                                     double growthRate,
                                     int years) {

        if (years == 0) return principal;


        if (cache.containsKey(years)) {
            System.out.println("  [Cache hit] Year " + years
                    + " retrieved from memory");
            return cache.get(years);
        }
        double result = futureValue(principal, growthRate, years - 1)
                * (1 + growthRate);
        cache.put(years, result);
        return result;
    }

    public static void clearCache() {
        cache.clear();
    }

    public static double futureValueDP(double principal,
                                       double growthRate,
                                       int years) {
        double value = principal;
        for (int i = 1; i <= years; i++) {
            value *= (1 + growthRate);
        }
        return value;
    }
}