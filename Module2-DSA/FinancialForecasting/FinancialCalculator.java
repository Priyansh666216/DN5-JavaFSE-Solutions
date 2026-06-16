package FinancialForecasting;

public class FinancialCalculator {

    /**
     * RECURSIVE: Calculate future value after n years
     *
     * Formula: FV = PV × (1 + r)^n
     *
     * Base case:  n == 0 → return principal as-is
     * Recursive:  FV(n) = FV(n-1) × (1 + growthRate)
     *
     * @param principal  initial amount (PV)
     * @param growthRate annual growth rate e.g. 0.10 = 10%
     * @param years      number of years to project
     */
    public static double futureValue(double principal,
                                     double growthRate,
                                     int years) {

        if (years == 0) {
            return principal;
        }
        return futureValue(principal, growthRate, years - 1)
                * (1 + growthRate);
    }

    public static double futureValueVariable(double principal,
                                             double[] growthRates,
                                             int yearIndex) {

        if (yearIndex == growthRates.length) {
            return principal;
        }

        double grownValue = principal * (1 + growthRates[yearIndex]);
        return futureValueVariable(grownValue, growthRates, yearIndex + 1);
    }

    public static void printForecast(double principal,
                                     double growthRate,
                                     int currentYear,
                                     int totalYears) {

        if (currentYear > totalYears) return;

        double value = futureValue(principal, growthRate, currentYear);
        System.out.printf("  Year %2d : Rs. %,.2f%n", currentYear, value);


        printForecast(principal, growthRate, currentYear + 1, totalYears);
    }
}