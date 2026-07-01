import java.util.HashMap;
import java.util.Map;

public class Exercise7_FinancialForecasting {

    // Naive recursive: O(2^n) â€” recomputes sub-problems exponentially
    static double forecastRecursive(double presentValue, double growthRate, int years) {
        if (years == 0) return presentValue;
        return forecastRecursive(presentValue * (1 + growthRate), growthRate, years - 1);
    }

    // Memoized recursive: O(n) time, O(n) space
    static double forecastMemo(double presentValue, double growthRate, int years,
                                Map<Integer, Double> memo) {
        if (years == 0) return presentValue;
        if (memo.containsKey(years)) return memo.get(years);
        double result = forecastMemo(presentValue, growthRate, years - 1, memo) * (1 + growthRate);
        memo.put(years, result);
        return result;
    }

    // Iterative (best): O(n) time, O(1) space â€” no stack overflow risk
    static double forecastIterative(double presentValue, double growthRate, int years) {
        double value = presentValue;
        for (int i = 0; i < years; i++) value *= (1 + growthRate);
        return value;
    }

    // Closed-form (mathematical): O(1) â€” FV = PV * (1 + r)^n
    static double forecastClosedForm(double presentValue, double growthRate, int years) {
        return presentValue * Math.pow(1 + growthRate, years);
    }

    public static void main(String[] args) {
        double pv = 10_000.0;
        double rate = 0.08; // 8% annual growth
        int years = 10;

        System.out.printf("Present Value : $%.2f%n", pv);
        System.out.printf("Growth Rate   : %.0f%%%n", rate * 100);
        System.out.printf("Years         : %d%n%n", years);

        double r1 = forecastRecursive(pv, rate, years);
        double r2 = forecastMemo(pv, rate, years, new HashMap<>());
        double r3 = forecastIterative(pv, rate, years);
        double r4 = forecastClosedForm(pv, rate, years);

        System.out.printf("Recursive (naive)  : $%.2f%n", r1);
        System.out.printf("Recursive (memoized): $%.2f%n", r2);
        System.out.printf("Iterative           : $%.2f%n", r3);
        System.out.printf("Closed-form         : $%.2f%n", r4);

        /*
         * Time Complexity:
         *   Naive recursive  -> O(n) here (tail-recursive, linear chain)
         *                       [would be O(2^n) if sub-problems branched]
         *   Memoized         -> O(n) time, O(n) space (avoids re-computation)
         *   Iterative        -> O(n) time, O(1) space â€” preferred
         *   Closed-form      -> O(1) â€” optimal for this specific formula
         *
         * Recursion risk: deep stacks cause StackOverflowError for large n.
         * Always prefer iterative or closed-form for numeric series.
         */
    }
}
