package FinancialForecasting;

public class ForecastTest {

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("     FINANCIAL FORECASTING TOOL");
        System.out.println("==========================================\n");

        double principal  = 100000.0;  // Rs. 1,00,000
        double growthRate = 0.10;      // 10% annual growth
        int    years      = 5;
        System.out.println("1. RECURSIVE FORECAST");
        System.out.println("   Principal  : Rs. 1,00,000");
        System.out.println("   Growth Rate: 10% per year");
        System.out.println("   Years      : 5\n");

        double fv = FinancialCalculator.futureValue(principal, growthRate, years);
        System.out.printf("   Future Value after %d years: Rs. %,.2f%n%n",
                years, fv);
        System.out.println("2. YEAR-BY-YEAR FORECAST BREAKDOWN");
        FinancialCalculator.printForecast(principal, growthRate, 0, years);
        System.out.println();
        System.out.println("3. VARIABLE GROWTH RATE FORECAST");
        double[] rates = {0.12, 0.08, 0.15, 0.07, 0.10};
        System.out.println("   Growth rates per year: 12%, 8%, 15%, 7%, 10%");
        double varFV = FinancialCalculator.futureValueVariable(
                principal, rates, 0);
        System.out.printf("   Future Value after 5 years: Rs. %,.2f%n%n", varFV);

        System.out.println("4. MEMOIZED RECURSIVE (Optimized)");
        double memoFV = MemoizedCalculator.futureValue(
                principal, growthRate, years);
        System.out.printf("   Future Value (memoized): Rs. %,.2f%n%n", memoFV);
        System.out.println("5. BOTTOM-UP DP (Most Optimized)");
        double dpFV = MemoizedCalculator.futureValueDP(
                principal, growthRate, years);
        System.out.printf("   Future Value (DP): Rs. %,.2f%n%n", dpFV);
        System.out.println("==========================================");
        System.out.println("   COMPLEXITY ANALYSIS");
        System.out.println("==========================================");
        System.out.println("Approach              Time      Space");
        System.out.println("------------------------------------------");
        System.out.println("Plain Recursion        O(n)      O(n)  ← call stack");
        System.out.println("Memoized Recursion     O(n)      O(n)  ← cache + stack");
        System.out.println("Bottom-Up DP           O(n)      O(1)  ← best!");
        System.out.println("Math formula FV=PV(1+r)^n  O(1)  O(1)  ← mathematical");
    }
}