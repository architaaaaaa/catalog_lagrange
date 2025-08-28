import java.util.*;
import java.math.BigInteger;

public class lagrange {

    private static BigInteger convertToBase10(String value, int base) {
        return new BigInteger(value, base);
    }

    private static BigInteger lagrangeInterpolation(List<BigInteger> xValues, List<BigInteger> yValues) {
        int n = xValues.size();
        BigInteger result = BigInteger.ZERO;

        for (int i = 0; i < n; i++) {
            BigInteger term = yValues.get(i);
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    BigInteger numerator = BigInteger.ZERO.subtract(xValues.get(j));
                    BigInteger denominator = xValues.get(i).subtract(xValues.get(j));
                    term = term.multiply(numerator).divide(denominator);
                }
            }
            result = result.add(term);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of roots (n):");
        int n = scanner.nextInt();

        System.out.println("Enter the minimum roots required (k):");
        int k = scanner.nextInt();
        scanner.nextLine(); // consume newline

        List<BigInteger> xValues = new ArrayList<>();
        List<BigInteger> yValues = new ArrayList<>();

        System.out.println("Enter the roots in format: base value");

        for (int i = 1; i <= n; i++) {
            System.out.println("Root " + i + ":");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            if (parts.length >= 2) {
                int base = Integer.parseInt(parts[0]);
                String value = parts[1];

                BigInteger x = BigInteger.valueOf(i);
                BigInteger y = convertToBase10(value, base);

                xValues.add(x);
                yValues.add(y);

                System.out.println("x=" + x + ", y=" + y);
            } else {
                System.out.println("Invalid input format. Skipping root " + i);
            }
        }

        // Use only the first k roots
        if (xValues.size() > k) {
            xValues = xValues.subList(0, k);
            yValues = yValues.subList(0, k);
            System.out.println("\nUsing first " + k + " roots for interpolation:");
        }

        BigInteger constantTerm = lagrangeInterpolation(xValues, yValues);
        System.out.println("\nConstant term 'c' = " + constantTerm);

        scanner.close();
    }
}